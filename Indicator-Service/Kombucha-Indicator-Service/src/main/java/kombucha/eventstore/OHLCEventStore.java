package kombucha.eventstore;

import kombucha.domain.OHLC;

import org.springframework.stereotype.Service;

/**
 * Event-Store to save and retrieve historic OHLC-Events.
 * 
 * @author mbartels
 *
 */
@Service
public interface OHLCEventStore {

	public void storeOHLCEvent(OHLC ohlc);
	
}
