package kombucha.dataprovider.yahoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import kombucha.dataprovider.QuoteDataProviderServiceI;
import kombucha.util.LogHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class YahooQuoteDataProviderImpl implements QuoteDataProviderServiceI {

	private Date defaultStartDate = null;
	private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat outputFormat = new SimpleDateFormat(
			"yyyyMMdd HHmmss");

	public YahooQuoteDataProviderImpl() {
		try {
			defaultStartDate = inputFormat.parse("1970-01-01");
		} catch (ParseException e) {
			LogHandler.logSevereError(YahooQuoteDataProviderImpl.class,
					"The given Date cannot be parsed. Please provide it in the following format: "
							+ inputFormat.toPattern(), e);
		}
	}

	public Collection<String[]> loadQuotesForSymbol(String symbol) {
		return loadQuotesForSymbol(symbol, defaultStartDate);
	}

	public Collection<String[]> loadQuotesForSymbol(String symbol,
			Date startDate) {
		LogHandler.logDebug(this.getClass(), "Reading quotes for symbol " + symbol);
		Collection<String[]> result = new ArrayList<String[]>();
		
		try {
			String url = createUrl(symbol, startDate);
			LogHandler.logInfo(this.getClass(), "URL: " + url, null);
			processRequest(symbol, result, url);

		} catch (HttpException e) {
			LogHandler.logError(YahooQuoteDataProviderImpl.class,
					"Error retrieving Quotes for " + symbol, e);
		} catch (IOException e) {
			LogHandler.logError(YahooQuoteDataProviderImpl.class,
					"Error retrieving Quotes for " + symbol, e);
		} catch (ParseException e) {
			LogHandler.logError(YahooQuoteDataProviderImpl.class,
					"Error parsing Dates for " + symbol, e);
		} catch (Exception e) {
			LogHandler.logError(YahooQuoteDataProviderImpl.class,
					"Unknown Exception " + symbol, e);
		}

		return result;
	}

	private void parseData(String symbol, Collection<String[]> result,
			InputStream answer) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(answer));
		// first line is the header, so we skip it
		String line = br.readLine();
		line = br.readLine();
		while (line != null) {
			//System.out.println(line);

			// Date,Open,High,Low,Close,Volume,Adj Close
			// 2007-02-01,46.40,47.97,45.41,47.10,979500,47.10

			StringTokenizer tokenizer = new StringTokenizer(line, ",");
			if (tokenizer.countTokens() != 7) {
				LogHandler.logError(this.getClass(),
						symbol + ": The response does not have the expected format: "
								+ line);
				break;
			}
			String date = tokenizer.nextToken();
			BigDecimal open = new BigDecimal(tokenizer.nextToken()
					.replaceAll("\"", ""));
			BigDecimal high = new BigDecimal(tokenizer.nextToken()
					.replaceAll("\"", ""));
			BigDecimal low = new BigDecimal(tokenizer.nextToken()
					.replaceAll("\"", ""));
			BigDecimal close = new BigDecimal(tokenizer.nextToken()
					.replaceAll("\"", ""));
			String volume = tokenizer.nextToken().replaceAll("\"", "");
			BigDecimal adjclose = new BigDecimal(tokenizer.nextToken()
					.replaceAll("\"", ""));
			
			BigDecimal splitFactor;
			if(close.compareTo(new BigDecimal(0)) != 0) {
				splitFactor = adjclose.divide(close, 2,
						BigDecimal.ROUND_HALF_UP);
			} else {
				splitFactor = new BigDecimal(1);
			}
			
			if (splitFactor.doubleValue() != 1) {
				open = open.multiply(splitFactor);
				close = close.multiply(splitFactor);
				high = high.multiply(splitFactor);
				low = low.multiply(splitFactor);
			}

			date = outputFormat.format(inputFormat.parse(date));

			String[] rawData = new String[] { date, open.toString(),
					high.toString(), low.toString(), close.toString(),
					volume };
			result.add(rawData);

			line = br.readLine();

		}
	}

	private void processRequest(String symbol, Collection<String[]> result, String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try {
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    parseData(symbol, result, entity.getContent());
		} finally {
		    response.close();
		}
	}

	private String createUrl(String symbol, Date startDate) throws Exception {
		// http://ichart.finance.yahoo.com/table.csv?s=VTR&d=1&e=22&f=2007&g=d&a=4&b=5&c=1997
		// month counting starts at 0

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(startDate.getTime());
		GregorianCalendar calNow = new GregorianCalendar();
		StringBuffer sb = new StringBuffer();
		sb.append(YahooConstants.QUOTEURL);
		sb.append(YahooConstants.SYMBOLPARAM);
		sb.append(URLEncoder.encode(symbol, "UTF-8"));
		sb.append("&");
		sb.append(YahooConstants.STARTMONTH);
		sb.append(cal.get(Calendar.MONTH));
		sb.append("&");
		sb.append(YahooConstants.STARTDAY);
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append("&");
		sb.append(YahooConstants.STARTYEAR);
		sb.append(cal.get(Calendar.YEAR));
		sb.append("&");
		sb.append(YahooConstants.QUOTETYPE);
		sb.append(YahooConstants.DAILYQUOTE);
		sb.append("&");
		sb.append(YahooConstants.ENDMONTH);
		sb.append(calNow.get(Calendar.MONTH));
		sb.append("&");
		sb.append(YahooConstants.ENDDAY);
		sb.append(calNow.get(Calendar.DAY_OF_MONTH));
		sb.append("&");
		sb.append(YahooConstants.ENDYEAR);
		sb.append(calNow.get(Calendar.YEAR));
		return sb.toString();
	}

	public String getDefaultStartDate() {
		return inputFormat.format(defaultStartDate);
	}

	public void setDefaultStartDate(String defaultStartDate) {
		try {
			this.defaultStartDate = inputFormat.parse(defaultStartDate);
		} catch (ParseException e) {
			LogHandler.logSevereError(YahooQuoteDataProviderImpl.class,
					"The given Date cannot be parsed. Please provide it in the following format: "
							+ inputFormat.toPattern(), e);
		}
	}
	
}
