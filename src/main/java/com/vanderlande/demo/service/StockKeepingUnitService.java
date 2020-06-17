package com.vanderlande.demo.service;

import java.util.List;

import com.vanderlande.demo.entities.StockKeepingUnit;

public interface StockKeepingUnitService {

	List<StockKeepingUnit> allStockKeepingUnits();

	StockKeepingUnit getStockKeepingUnit(final String stockKeepingUnitId);
	
}
