package kombucha;

import java.text.SimpleDateFormat;
import java.util.Collection;

import kombucha.dataprovider.yahoo.YahooQuoteDataProviderImpl;

public class YahooTest {
	
	public static void main(String[] args) throws Exception {
		YahooQuoteDataProviderImpl dp = new YahooQuoteDataProviderImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Collection<String[]> quotes = dp.loadQuotesForSymbol("^GSPC", sdf.parse("01.01.2014"));
		for (String[] quote : quotes) {
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<quote.length;i++) {
				sb.append(quote[i]);
				sb.append("|");
			}
			System.out.println(sb.toString());
		}
	}

}
