package kombucha.indicators.movingaverage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import kombucha.domain.OHLC;
import kombucha.indicators.Direction;
import kombucha.indicators.Indicator;
import kombucha.indicators.Signal;

import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;

public class MovingAverageIndicator implements Indicator {

	/**
	 * The time period that the average is calculated for (usually in days)
	 */
	private int period;
	
	/**
	 * The current direction of the security. Used to check if a direction change needs to be signaled.
	 */
	private HashMap<String, Direction> currentDirection;
	
	private HashMap<String, ArrayList<OHLC>> currentOHLCEvents;
	
	public MovingAverageIndicator(int period) {
		super();
		currentOHLCEvents = new HashMap<>();
		currentDirection = new HashMap<>();
		this.period = period;
	}
	
	@Override
	public Signal consume(OHLC ohlc) {
		addOHLCToCachedData(ohlc);
		if (notEnoughData(ohlc)) {
			return null;
		}
//		XYDataset movingAverage = createMovingAverage(ohlc);		
//		return determineSignal(ohlc, movingAverage);

		return determineSignal(ohlc, createMovingAverage(ohlc).doubleValue());
		//TODO Abstract-Indicator mit Symbol und Datenhaltung

	}

	private boolean notEnoughData(OHLC ohlc) {
		return currentOHLCEvents.get(ohlc.getSymbol()).size() < period;
	}

	private Signal determineSignal(OHLC ohlc, double currentMovingAverage) {
		System.out.println(ohlc.getClose() + ": " + currentMovingAverage);
		Signal determinedSignal = null;
//		double currentMovingAverage = movingAverage.getYValue(0, period-1);
		Direction currentDirectionForSymbol = currentDirection.get(ohlc.getSymbol());
		if (currentDirectionForSymbol == null) {
			currentDirectionForSymbol = newDirection(ohlc, currentMovingAverage);
			determinedSignal = new Signal(ohlc.getDate(), currentDirectionForSymbol, ohlc, new BigDecimal(currentMovingAverage));
		} else {
			Direction newDirection = newDirection(ohlc, currentMovingAverage);
			if (newDirection != currentDirectionForSymbol) {
				determinedSignal = new Signal(ohlc.getDate(), newDirection, ohlc, new BigDecimal(currentMovingAverage));
			} 
		}
		currentDirection.put(ohlc.getSymbol(), currentDirectionForSymbol);
		return determinedSignal;
		
	}
	
	private Direction newDirection(OHLC ohlc, double currentMovingAverage) {
		return ohlc.getClose().doubleValue() > currentMovingAverage ? Direction.UP : Direction.DOWN;
	}

//	private XYDataset createMovingAverage(OHLC ohlc) {
//		OHLCSeries ohlcSeries = new OHLCSeries(ohlc.getSymbol());
//		for (OHLC ohlcItem : currentOHLCEvents.get(ohlc.getSymbol())) {
//			System.out.println(ohlcItem.getDate());
//			ohlcSeries.add(new Day(ohlcItem.getDate()), 
//					ohlcItem.getOpen().doubleValue(), 
//					ohlcItem.getHigh().doubleValue(), 
//					ohlcItem.getLow().doubleValue(), 
//					ohlcItem.getClose().doubleValue());
//		}
//		OHLCSeriesCollection data = new OHLCSeriesCollection();
//		data.addSeries(ohlcSeries);
//		XYDataset movingAverage = MovingAverage.createMovingAverage(data, ohlc.getSymbol(), period, 0);
//		return movingAverage;
//	}

	private BigDecimal createMovingAverage(OHLC ohlc) {
		TimeSeries ohlcSeries = new TimeSeries(ohlc.getSymbol());
		for (OHLC ohlcItem : currentOHLCEvents.get(ohlc.getSymbol())) {
			ohlcSeries.add(new Day(ohlcItem.getDate()), 
				ohlcItem.getClose().doubleValue());
		}
		TimeSeries movingAverage = MovingAverage.createMovingAverage(ohlcSeries, "MovingAverage", period, 0);
		return new BigDecimal((Double)movingAverage.getDataItem(new Day(ohlc.getDate())).getValue());
}
	
	/**
	 * This method caches the required data for the indicator. If the list of events is bigger than the
	 * required number of events for the period the oldest element is removed. 
	 */
	private void addOHLCToCachedData(OHLC ohlc) {
		ArrayList<OHLC> ohlcEvents = currentOHLCEvents.get(ohlc.getSymbol()); 
		if (ohlcEvents == null) {
			ohlcEvents = new ArrayList<>();
			currentOHLCEvents.put(ohlc.getSymbol(), ohlcEvents);
		}
		ohlcEvents.add(ohlc);
		if (ohlcEvents.size() > period) {
			ohlcEvents.remove(0);
		}
	}

}
