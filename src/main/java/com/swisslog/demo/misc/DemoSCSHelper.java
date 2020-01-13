package com.swisslog.demo.misc;

import java.util.concurrent.ConcurrentHashMap;

import com.swisslog.demo.entities.Location;
import com.swisslog.demo.entities.StockKeepingUnit;
import com.swisslog.demo.entities.StockKeepingUnit.SKUType;
import com.swisslog.demo.entities.Supplier;

public class DemoSCSHelper {

	private static DemoSCSHelper instance = null;
	
	ConcurrentHashMap<Long, StockKeepingUnit> skus = new ConcurrentHashMap<Long, StockKeepingUnit>();
	
	public static DemoSCSHelper getInstance() {
		if (instance == null) {
			instance = new DemoSCSHelper();
		}
		return instance;
	}
	
	public StockKeepingUnit createAndReturnStockKeepingUnit() {
		StockKeepingUnit sku = new StockKeepingUnit();
		sku.setSkuId(skus.size()+1L);		
		double rnd = Math.random();
		long quantity = (long)(100.0D * rnd);		
		sku.setQuantity(quantity);
		sku.setSkuType(SKUType.EAN);
		skus.put(sku.getSkuId(),sku);
		return sku;
	}


	public Location createAndReturnLocation() {
		Location location = new Location();
		location.setCountry("Germany");
		location.setCity("Dortmund");
		location.setNumber("13a");
		location.setState("NRW");
		location.setStreet("Martin-Schmeisser-Weg");
		location.setZipCode("44227");
		return location;
	}

	public Supplier createAndReturnSupplier(long i) {
		Supplier s = new Supplier();
		s.setId(i);
		s.setName("The Supplier");
		return s;
	}
	
	public ConcurrentHashMap<Long, StockKeepingUnit> getAllStockKeepingUnits() {
		return skus;
	}
	
	public StockKeepingUnit getStockKeepingUnitById(long id) {
		StockKeepingUnit retval = null;
		retval = skus.get(id);
		return retval;
	}
}
