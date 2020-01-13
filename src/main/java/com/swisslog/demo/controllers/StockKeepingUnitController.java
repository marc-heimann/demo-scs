package com.swisslog.demo.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swisslog.demo.entities.StockKeepingUnit;
import com.swisslog.demo.service.AdvancedShippingNoticeService;
import com.swisslog.demo.service.StockKeepingUnitService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(value = "/v1")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class StockKeepingUnitController {

	@Autowired
	AdvancedShippingNoticeService asnService;
	
	@Autowired 
	StockKeepingUnitService skuService;
		
	@GetMapping(value = "/skus/{skuId}", produces = {"application/hal+json"})
	public StockKeepingUnit getStockKeepingUnit(@PathVariable final String skuId) {

		
		StockKeepingUnit sku = skuService.getStockKeepingUnit(skuId);
		Link selfLink = linkTo(methodOn(StockKeepingUnitController.class).getStockKeepingUnit(skuId)).withSelfRel();
		if (!sku.hasLink(selfLink.getRel()))
			sku.add(selfLink);

		return sku;
	}
	
	@GetMapping(value = "/skus", produces = {"application/hal+json"})
	public Resources<StockKeepingUnit> getStockKeepingUnits() {		
			
		final List<StockKeepingUnit> skus = skuService.allStockKeepingUnits();
		    
		for (final StockKeepingUnit sku : skus) {
			Link selfLink = linkTo(methodOn(StockKeepingUnitController.class).getStockKeepingUnits()).withSelfRel();
			sku.add(selfLink);
		}
			  
		Link link = linkTo(methodOn(StockKeepingUnitController.class).getStockKeepingUnits()).withSelfRel();
		return new Resources<>(skus, link);
	}
	
}
