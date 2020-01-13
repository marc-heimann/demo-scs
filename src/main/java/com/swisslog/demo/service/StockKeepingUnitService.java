package com.swisslog.demo.service;

import java.util.List;

import com.swisslog.demo.entities.StockKeepingUnit;

public interface StockKeepingUnitService {

	List<StockKeepingUnit> allStockKeepingUnits();

	StockKeepingUnit getStockKeepingUnit(final String stockKeepingUnitId);
	
}
