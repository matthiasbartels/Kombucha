package kombucha.testutils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import kombucha.domain.OHLC;

public class TestDataOHLCTransformfer {
	
	public OHLC transform(String testData) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		String[] tokens = testData.split(";");
		OHLC ohlc = new OHLC("SPX", 
				sdf.parse(tokens[0]), 
				new BigDecimal(tokens[1]),
				new BigDecimal(tokens[2]),
				new BigDecimal(tokens[3]),
				new BigDecimal(tokens[4]));
		return ohlc;
	}

}
