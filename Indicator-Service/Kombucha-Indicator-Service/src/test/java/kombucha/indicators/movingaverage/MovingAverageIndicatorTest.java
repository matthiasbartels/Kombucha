package kombucha.indicators.movingaverage;

import java.math.BigDecimal;
import java.util.Calendar;

import kombucha.domain.OHLC;
import kombucha.indicators.Direction;
import kombucha.indicators.Signal;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovingAverageIndicatorTest {

	@Test
	public void assertThatNoSignalIsReturnedBeforeEnoughDataIsProvied() throws Exception {
		MovingAverageIndicator indicator = new MovingAverageIndicator(5);
		Calendar cal = Calendar.getInstance();
		upTrend(indicator, cal, 4);
		Signal receivedSignal = addOHLCForNextDay(indicator, cal, 5);
		assertNotNull(receivedSignal);
	}
	
	@Test
	public void assertThatSignalIsDown() throws Exception {
		MovingAverageIndicator indicator = new MovingAverageIndicator(5);
		Calendar cal = Calendar.getInstance();
		for (int i=10; i>10; i--) {
			Signal receivedSignal = addOHLCForNextDay(indicator, cal, i);
			if (i == 6) {
				assertEquals(Direction.DOWN, receivedSignal.getDirection());
			} else {
				assertNull(receivedSignal);
			}
		}		
	}
	
	@Test
	public void assertThatSignalIsUp() throws Exception {
		MovingAverageIndicator indicator = new MovingAverageIndicator(5);
		Calendar cal = Calendar.getInstance();
		upTrend(indicator, cal, 10);
	}

	private void upTrend(MovingAverageIndicator indicator, Calendar cal, int max) {
		for (int i=0; i<max; i++) {
			Signal receivedSignal = addOHLCForNextDay(indicator, cal, i);
			if (i == 4) {
				assertEquals(Direction.UP, receivedSignal.getDirection());
			} else {
				assertNull(receivedSignal);
			}
		}
	}

	@Test
	public void assertThatSignalFiresIfDirectionChanges() throws Exception {
		MovingAverageIndicator indicator = new MovingAverageIndicator(5);
		Calendar cal = Calendar.getInstance();
		upTrend(indicator, cal, 10);
		Signal receivedSignal = addOHLCForNextDay(indicator, cal, 2);
		assertEquals(Direction.DOWN, receivedSignal.getDirection());
	}
	
	private Signal addOHLCForNextDay(MovingAverageIndicator indicator,
			Calendar cal, int value) {
		cal.add(Calendar.DATE, 1);
		Signal receivedSignal = indicator.consume(new OHLC("SPX", cal.getTime(), new BigDecimal(value), new BigDecimal(value), new BigDecimal(value), new BigDecimal(value)));
		return receivedSignal;
	}
	
	//TODO - kein Mix von Symbolen
	
}
