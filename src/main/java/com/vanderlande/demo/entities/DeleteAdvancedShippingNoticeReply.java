package com.vanderlande.demo.entities;

import com.vanderlande.demo.businessobjects.AdvancedShippingNotice;

public class DeleteAdvancedShippingNoticeReply {
	 String deletionStatus = "DONE";
    
    AdvancedShippingNotice deletedAsn;
    
    public AdvancedShippingNotice getDeletedAsn() {
		return deletedAsn;
	}

	public void setDeletedAsn(AdvancedShippingNotice deletedAsn) {
		this.deletedAsn = deletedAsn;
	}

	public String getDeletionStatus() {
        return deletionStatus;
    }
    
    public void setDeletionStatus(String deletionStatus) {
    	this.deletionStatus = deletionStatus;
    }
}
