/**
 * 
 */
package com.vanderlande.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vanderlande.demo.businessobjects.AdvancedShippingNotice;
import com.vanderlande.demo.entities.Location;
import com.vanderlande.demo.entities.Supplier;
import com.vanderlande.demo.misc.DemoSCSHelper;

/**
 * @author g7heimm
 *
 */
public class AdvancedShippingNoticeTest {

	AdvancedShippingNotice asn;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.asn = new AdvancedShippingNotice();
		this.asn.setAsnId(815);
		this.asn.setDeliveryDate(0);		
		this.asn.setDestinationLocation(DemoSCSHelper.getInstance().createAndReturnLocation());
		this.asn.setPurchaseOrderNumber(1);
		this.asn.setShipDate(0);
		this.asn.setShipNoticeNumber(11);
		this.asn.setSupplier(DemoSCSHelper.getInstance().createAndReturnSupplier(1));		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.asn = null;
	}
	

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#getAsnId()}.
	 */
	@Test
	public void testGetAsnId() {
		assertEquals(815, this.asn.getAsnId());
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#setAsnId(long)}.
	 */
	@Test
	public void testSetAsnId() {
		this.asn.setAsnId(3);
		assertEquals(3, this.asn.getAsnId());		
		this.asn.setAsnId(815);
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#getPurchaseOrderNumber()}.
	 */
	@Test
	public void testGetPurchaseOrderNumber() {
		assertEquals(1, this.asn.getPurchaseOrderNumber());
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#setPurchaseOrderNumber(long)}.
	 */
	@Test
	public void testSetPurchaseOrderNumber() {
		this.asn.setPurchaseOrderNumber(4);
		assertEquals(4, this.asn.getPurchaseOrderNumber());		
		this.asn.setAsnId(1);
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#getShipNoticeNumber()}.
	 */
	@Test
	public void testGetShipNoticeNumber() {
		assertEquals(11, this.asn.getShipNoticeNumber());	
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#setShipNoticeNumber(long)}.
	 */
	@Test
	public void testSetShipNoticeNumber() {
		this.asn.setShipNoticeNumber(5);
		assertEquals(5, this.asn.getShipNoticeNumber());
		this.asn.setShipNoticeNumber(11);
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#getDestinationLocation()}.
	 */
	@Test
	public void testGetDestinationLocation() {
		assertNotNull(this.asn.getDestinationLocation());		
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#setDestinationLocation(com.vanderlande.demo.entities.Location)}.
	 */
	@Test
	public void testSetDestinationLocation() {
		Location l = this.asn.getDestinationLocation();
		this.asn.setDestinationLocation(null);
		assertNull(this.asn.getDestinationLocation());
		this.asn.setDestinationLocation(l);
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#getSupplier()}.
	 */
	@Test
	public void testGetSupplier() {
		assertNotNull(this.asn.getSupplier());
		
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#setSupplier(com.vanderlande.demo.entities.Supplier)}.
	 */
	@Test
	public void testSetSupplier() {
		Supplier s = this.asn.getSupplier();
		this.asn.setSupplier(null);
		assertNull(this.asn.getSupplier());
		this.asn.setSupplier(s);
		
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#getShipDate()}.
	 */
	@Test
	public void testGetShipDate() {
		assertEquals(0, this.asn.getShipDate());
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#setShipDate(long)}.
	 */
	@Test
	public void testSetShipDate() {
		this.asn.setShipDate(12);
		assertEquals(12, this.asn.getShipDate());
		this.asn.setShipDate(0);
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#getDeliveryDate()}.
	 */
	@Test
	public void testGetDeliveryDate() {
		assertEquals(0, this.asn.getDeliveryDate());
	}

	/**
	 * Test method for {@link com.vanderlande.demo.businessobjects.AdvancedShippingNotice#setDeliveryDate(long)}.
	 */
	@Test
	public void testSetDeliveryDate() {
		this.asn.setDeliveryDate(22);
		assertEquals(22, this.asn.getDeliveryDate());
		this.asn.setDeliveryDate(0);
	}

}
