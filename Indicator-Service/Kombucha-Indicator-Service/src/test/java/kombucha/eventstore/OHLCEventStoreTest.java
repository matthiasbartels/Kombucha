package kombucha.eventstore;

import java.math.BigDecimal;
import java.util.Date;

import kombucha.KombuchaIndicatorServiceApplication;
import kombucha.domain.OHLC;
import kombucha.testutils.KombuchaIndicatorServiceWithHistoricalData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=KombuchaIndicatorServiceWithHistoricalData.class)
public class OHLCEventStoreTest {
	
	@Autowired
	private OHLCEventStore eventStore;
	
	@Test
	public void thatOHLCIsSavedInEventStore() {
		eventStore.storeOHLCEvent(ohlc());
	}
	
	private OHLC ohlc() {
		return new OHLC("SPX", new Date(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
	}

}
