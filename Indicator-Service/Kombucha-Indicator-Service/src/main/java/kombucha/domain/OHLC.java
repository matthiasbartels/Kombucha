package kombucha.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OHLC {
	
	private String symbol;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private Date date;
	
	public OHLC(String symbol, Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close) {
		this.date = date;
		this.symbol = symbol;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(date) 
				+ "|" + open
				+ "|" + high
				+ "|" + low
				+ "|" + close;
	}

}
