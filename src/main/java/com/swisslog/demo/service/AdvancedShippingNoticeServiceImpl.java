package com.swisslog.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.swisslog.demo.businessobjects.AdvancedShippingNotice;
import com.swisslog.demo.entities.StockKeepingUnit;
import com.swisslog.demo.misc.DemoSCSHelper;

@Service
public class AdvancedShippingNoticeServiceImpl implements AdvancedShippingNoticeService {

	public AdvancedShippingNoticeServiceImpl() {
		
		if (asns != null)
			return;
		
		asns = new ArrayList<AdvancedShippingNotice>();
		
				
		for (int i=0; i<100; i++) {
			AdvancedShippingNotice asn = new AdvancedShippingNotice();
			asn.setAsnId(i);
			asn.setDeliveryDate(0L);
			asn.setDestinationLocation(DemoSCSHelper.getInstance().createAndReturnLocation());
			asn.setPurchaseOrderNumber(i+i);
			asn.setShipDate(System.currentTimeMillis());
			asn.setShipNoticeNumber(i+i+i*2);
			StockKeepingUnit sku = DemoSCSHelper.getInstance().createAndReturnStockKeepingUnit();
			asn.addSkuId(sku.getSkuId());
			asn.setSupplier(DemoSCSHelper.getInstance().createAndReturnSupplier(i));
			asns.add(asn);
		}
	}
	
	List<AdvancedShippingNotice> asns = null;
	
	@Override
	public List<AdvancedShippingNotice> allAdvancedShippingNotices() {
		return asns;
	}

	@Override
	public AdvancedShippingNotice getAdvancedShippingNoticeById(String advancedShippingNoticeId) {
		AdvancedShippingNotice retval = null;
		
		try {
			long id = Long.parseLong(advancedShippingNoticeId);
			
			for (AdvancedShippingNotice asn : asns) {
				if (asn.getAsnId() == id) {
					retval = asn;
					return retval; 
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return retval;
	}

	@Override
	public AdvancedShippingNotice addAdvaneShippingNotice(AdvancedShippingNotice asn) {
		if (asn.getAsnId() != 0L) 
		{
			asns.set((int)asn.getAsnId(), asn);
			return asn;
		}
		
		asn.setAsnId(asns.size());
		if (asns.add(asn))
		{
			return asn;
		}
		else 
		{
			return null;
		}
	}

	
	
}
