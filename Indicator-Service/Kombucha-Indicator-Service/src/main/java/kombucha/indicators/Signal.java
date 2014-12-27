package kombucha.indicators;

import java.math.BigDecimal;
import java.util.Date;

import kombucha.domain.OHLC;

public class Signal {
	
	private Direction direction;
	private Date date;
	private OHLC triggeringOHLC;
	private BigDecimal currentIndicatorValue;
	
	public Signal(Date date, Direction direction, OHLC triggeringOHLC, BigDecimal currentIndicatorValue) {
		this.direction = direction;
		this.date = date;
		this.currentIndicatorValue = currentIndicatorValue;
		this.triggeringOHLC = triggeringOHLC;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public OHLC getTriggeringOHLC() {
		return triggeringOHLC;
	}

	public void setTriggeringOHLC(OHLC triggeringOHLC) {
		this.triggeringOHLC = triggeringOHLC;
	}

	public BigDecimal getCurrentIndicatorValue() {
		return currentIndicatorValue;
	}

	public void setCurrentIndicatorValue(BigDecimal currentIndicatorValue) {
		this.currentIndicatorValue = currentIndicatorValue;
	}

}
