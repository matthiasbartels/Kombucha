package kombucha.indicators;

import kombucha.domain.OHLC;

public interface Indicator {
	
	/**
	 * Each Indicator implements this method. The indicator consumes an OHLC-event and is allowed to cache it if necessary.
	 * 
	 * If the indicator produces a signal it returns it to the caller. If no relevant signal occurrs it returns null.
	 * 
	 */
	public Signal consume(OHLC ohlc);
	
	/**
	 * Each indicator has to describe itself.
	 * 
	 */
	public String description();
	
}
