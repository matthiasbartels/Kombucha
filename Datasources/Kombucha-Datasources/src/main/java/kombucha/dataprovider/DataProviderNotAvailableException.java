package kombucha.dataprovider;

public class DataProviderNotAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5646340319128824127L;
	
	public DataProviderNotAvailableException(String message, Throwable t) {
		super(message, t);
	}
	
	public DataProviderNotAvailableException(String message) {
		super(message);
	}
}
