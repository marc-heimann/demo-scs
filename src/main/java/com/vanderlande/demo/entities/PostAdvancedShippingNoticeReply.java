package com.vanderlande.demo.entities;

import com.vanderlande.demo.businessobjects.AdvancedShippingNotice;

public class PostAdvancedShippingNoticeReply {	
    String registrationStatus = "DONE";
    
    AdvancedShippingNotice postedAsn;
    
    public AdvancedShippingNotice getPostedAsn() {
		return postedAsn;
	}

	public void setPostedAsn(AdvancedShippingNotice postedAsn) {
		this.postedAsn = postedAsn;
	}

	public String getRegistrationStatus() {
        return registrationStatus;
    }
    
    public void setRegistrationStatus(String registrationStatus) {
    	this.registrationStatus = registrationStatus;
    }
    
}
