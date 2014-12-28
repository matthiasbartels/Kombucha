package kombucha.indicators;

import java.math.BigDecimal;
import java.util.Date;

import kombucha.domain.OHLC;

public class Signal {
	
	private Direction direction;
	private Date date;
	private OHLC triggeringOHLC;
	private BigDecimal currentIndicatorValue;
	private String triggeringIndicator; 
	
	public Signal(Date date, Direction direction, OHLC triggeringOHLC, String triggeringIndicator, BigDecimal currentIndicatorValue) {
		this.direction = direction;
		this.date = date;
		this.currentIndicatorValue = currentIndicatorValue;
		this.triggeringOHLC = triggeringOHLC;
		this.triggeringIndicator = triggeringIndicator;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public Date getDate() {
		return date;
	}
	public OHLC getTriggeringOHLC() {
		return triggeringOHLC;
	}
	public BigDecimal getCurrentIndicatorValue() {
		return currentIndicatorValue;
	}
	public String getIndicator() {
		return triggeringIndicator;
	}

	public String toString() {
		return "Symbol: " + getTriggeringOHLC().getSymbol() 
				+ " Direction: " + getDirection()
				+ " At Date: " + getDate()
				+ " From Indicator: " + getIndicator()
				+ " Security at: " + triggeringOHLC.getClose()
				+ " Indicator at: " + currentIndicatorValue;
	}
	
}
