package com.vanderlande.demo.service;

import java.util.List;

import com.vanderlande.demo.businessobjects.AdvancedShippingNotice;

public interface AdvancedShippingNoticeService {
	
	List<AdvancedShippingNotice> allAdvancedShippingNotices();

	AdvancedShippingNotice getAdvancedShippingNoticeById(final String advancedShippingNoticeId);
	
	AdvancedShippingNotice addAdvaneShippingNotice(AdvancedShippingNotice asn);
	
}
