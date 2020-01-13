package com.swisslog.demo.entities;

import com.swisslog.demo.businessobjects.AdvancedShippingNotice;

public class PutAdvancedShippingNoticeReply {
    String updateStatus = "DONE";
    
    AdvancedShippingNotice updatedAsn;
    
    public AdvancedShippingNotice getPutAsn() {
		return updatedAsn;
	}

	public void setPostedAsn(AdvancedShippingNotice updatedAsn) {
		this.updatedAsn = updatedAsn;
	}

	public String getRegistrationStatus() {
        return updateStatus;
    }
    
    public void setRegistrationStatus(String updateStatus) {
    	this.updateStatus = updateStatus;
    }
}
