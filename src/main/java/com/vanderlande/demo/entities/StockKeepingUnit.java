package com.vanderlande.demo.entities;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

public class StockKeepingUnit extends ResourceSupport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8390232267983133381L;

	long skuId;
	SKUType skuType;
	long quantity;
	
	public static enum SKUType {
		UPC, EAN, GTIN
	}
	
	public long getSkuId() {
		return skuId;
	}

	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}

	public SKUType getSkuType() {
		return skuType;
	}

	public void setSkuType(SKUType skuType) {
		this.skuType = skuType;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}	
	
}
