package com.vanderlande.demo.businessobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.vanderlande.demo.entities.Location;
import com.vanderlande.demo.entities.Supplier;

public class AdvancedShippingNotice extends ResourceSupport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -292184466587480029L;
	
	long asnId = 0L;
	long purchaseOrderNumber;
	long shipNoticeNumber;
	Location destinationLocation;
	Supplier supplier;
	long shipDate;
	long deliveryDate;
	private List<Long> skuIds;
	
	public long getAsnId() {
		return asnId;
	}
	public void setAsnId(long asnId) {
		this.asnId = asnId;
	}
	public long getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(long purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public long getShipNoticeNumber() {
		return shipNoticeNumber;
	}
	public void setShipNoticeNumber(long shipNoticeNumber) {
		this.shipNoticeNumber = shipNoticeNumber;
	}
	public Location getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(Location destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public long getShipDate() {
		return shipDate;
	}
	public void setShipDate(long shipDate) {
		this.shipDate = shipDate;
	}
	public long getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}	
	public List<Long> getSkuIds() {
		return skuIds;
	}
	public void addSkuId(long skuId) {
		if (skuIds == null)
			skuIds = new ArrayList<>();		
		this.skuIds.add(skuId);
	}
	
	@Override
	public boolean equals(Object obj) {		
		if (obj == null)
			return false;
		
		if (obj == this) {
		  return true;
		}
		
		if (this.getClass() != obj.getClass()) {
		  return false;
		}		
		
		AdvancedShippingNotice fobj = (AdvancedShippingNotice) obj;
		
		return (this.getAsnId() == fobj.getAsnId());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
