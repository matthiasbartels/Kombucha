package kombucha.dataprovider;

import java.util.Collection;
import java.util.Date;

public interface QuoteDataProviderServiceI {
	/**
	 * This method loads all Quotes from the dataprovider for the given Symbol. The rawdata is a 
	 * Collection of String-Arrays. The Arrays have the following structure:
	 * 
	 * {Date,Open,High,Low,Close,Volume}
	 *	
	 * 
	 * The value for the date is a string in this format: "yyyyMMdd HHmmss" 
	 * 
	 * Example:
	 * 
	 * {"20060102 000000","19.71", "20.62", "18.64", "19.95", "8772901"}
	 * 
	 * The method uses the configured start-Date for retrieving the Quotes.
	 * 
	 * @param the symbol of the commercialPaper
	 * @return a collection with String[] containing the rawdata
	 */	
	public Collection<String[]> loadQuotesForSymbol(String symbol);
	
	/**
	 * See method loadDetailsForSymbols(Collection<String> symbols);
	 * This method loads all Quotes starting from the given date.
	 */
	public Collection<String[]> loadQuotesForSymbol(String symbol, Date startDate);
}
