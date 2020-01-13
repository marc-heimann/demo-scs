package com.swisslog.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.swisslog.demo.entities.StockKeepingUnit;
import com.swisslog.demo.misc.DemoSCSHelper;

@Service
public class StockKeepingUnitServiceImpl implements StockKeepingUnitService {
	
	@Override
	public List<StockKeepingUnit> allStockKeepingUnits() {		
		return new ArrayList<StockKeepingUnit>(DemoSCSHelper.getInstance().getAllStockKeepingUnits().values());
	}

	@Override
	public StockKeepingUnit getStockKeepingUnit(String stockKeepingUnitId) {
		StockKeepingUnit retval = null;	
		
		try {
			long skuId = Long.parseLong(stockKeepingUnitId);
			retval = DemoSCSHelper.getInstance().getStockKeepingUnitById(skuId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return retval;
	}
	
}
