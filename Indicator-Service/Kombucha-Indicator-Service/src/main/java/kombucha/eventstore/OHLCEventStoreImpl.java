package kombucha.eventstore;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kombucha.domain.OHLC;

@Service
public class OHLCEventStoreImpl implements OHLCEventStore {

	@Autowired
	private OHLCRepository ohlcRepo;
	
	@Override
	public void storeOHLCEvent(OHLC ohlc) {
		OHLCEntity entity = new OHLCEntity();
		BeanUtils.copyProperties(ohlc, entity);
		ohlcRepo.save(entity);
	}

}
