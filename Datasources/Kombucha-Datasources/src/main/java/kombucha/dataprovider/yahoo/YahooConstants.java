/**
 * 
 */
package kombucha.dataprovider.yahoo;

/**
 * @author Ronny Walter
 *
 * This interface defines some constants for the YahooProvider. These constants are
 * use to build the format-String for the Yahoo-Request.
 * See http://www.gummy-stuff.org/Yahoo-data.htm for details.
 * 
 * Example Url: 
 * 
 */
public interface YahooConstants {
	public static final String URL = "http://finance.yahoo.com/d/quotes.csv?";
	public static final String SYMBOLPARAM = "s=";
	public static final String FORMATPARAM = "f=";
	
	public static final String NAME = "n";
	public static final String EXCHANGE = "x";
	public static final String SYMBOL = "s";
	public static final String MOREINFO = "i";
	public static final String NOTES = "n4";
	public static final String ERRORINDICATION = "e1";
	
	public static final String QUOTEURL = "http://ichart.finance.yahoo.com/table.csv?";
	// http://ichart.finance.yahoo.com/table.csv?s=VTR&d=1&e=22&f=2007&g=d&a=4&b=5&c=1997
	// http://ichart.finance.yahoo.com/table.csv?s=AAPL&d=0&e=1&f=2000&g=d&a=1&b=22&c=2007
	public static final String STARTMONTH="a=";
	public static final String STARTDAY="b=";
	public static final String STARTYEAR="c=";
	public static final String ENDMONTH="d=";
	public static final String ENDDAY="e=";
	public static final String ENDYEAR="f=";
	public static final String QUOTETYPE="g=";
	public static final String DAILYQUOTE="d";
	public static final String WEEKLYQUOTE="w";	
	public static final String MONTHLYQUOTE="m";
	
	public static final String BID="b";
	public static final String ASK="a";
	public static final String LAST="l1";
	
}
