package com.swisslog.demo.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swisslog.demo.businessobjects.AdvancedShippingNotice;
import com.swisslog.demo.entities.DeleteAdvancedShippingNoticeReply;
import com.swisslog.demo.entities.PostAdvancedShippingNoticeReply;
import com.swisslog.demo.entities.PutAdvancedShippingNoticeReply;
import com.swisslog.demo.service.AdvancedShippingNoticeService;
import com.swisslog.demo.service.StockKeepingUnitService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(value = "/v1")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class AdvancedShippingNoticeController {

	@Autowired
	AdvancedShippingNoticeService asnService;
	
	@Autowired 
	StockKeepingUnitService skuService;
	
	@GetMapping(value = "/asns/{asnId}")
    public AdvancedShippingNotice getAdvancedShippingNoticeById(@PathVariable String asnId) {
        return asnService.getAdvancedShippingNoticeById(asnId);
    }
	
	@GetMapping(value = "/asns", produces = {"application/hal+json"})
	public Resources<AdvancedShippingNotice> getAdvancedShippingNotices() {		
			
		final List<AdvancedShippingNotice> asns = asnService.allAdvancedShippingNotices();
		    
		for (final AdvancedShippingNotice asn : asns) {
			Link selfLink = linkTo(methodOn(AdvancedShippingNoticeController.class).getAdvancedShippingNotices()).withSelfRel();
			if (!asn.hasLink(selfLink.getRel()))
				asn.add(selfLink);
		}
			  
		Link link = linkTo(methodOn(AdvancedShippingNoticeController.class).getAdvancedShippingNotices()).withSelfRel();
		return new Resources<>(asns, link);
	}
	
	@PostMapping("/asns")
	@ResponseBody
	public PostAdvancedShippingNoticeReply postAdvancedShippingNotice(@RequestBody AdvancedShippingNotice asn) {
		PostAdvancedShippingNoticeReply reply = new PostAdvancedShippingNoticeReply();
		if (asnService.addAdvaneShippingNotice(asn) != null) {			
			reply.setRegistrationStatus("CREATED");
			reply.setPostedAsn(asn);
		} else { 
			reply.setRegistrationStatus("NOT_CREATED");
		}
		return reply;
	}
	
	@PutMapping("/asns")
	@ResponseBody
	public PutAdvancedShippingNoticeReply putAdvancedShippingNotice(@RequestBody AdvancedShippingNotice asn) {
		PutAdvancedShippingNoticeReply reply = new PutAdvancedShippingNoticeReply();		
		if (asnService.addAdvaneShippingNotice(asn) != null) {			
			reply.setRegistrationStatus("UPDATED");
			reply.setPostedAsn(asn);
		} else { 
			reply.setRegistrationStatus("NOT_UPDATED");
		}
		return reply;
	}
	
	@DeleteMapping("/asns")
	@ResponseBody
	public DeleteAdvancedShippingNoticeReply deleteAdvancedShippingNotice(@RequestBody AdvancedShippingNotice asn) {
		DeleteAdvancedShippingNoticeReply reply = new DeleteAdvancedShippingNoticeReply();
		if (asnService.addAdvaneShippingNotice(asn) != null) {			
			reply.setDeletionStatus("DELETED");
			reply.setDeletedAsn(asn);
		} else { 
			reply.setDeletionStatus("NOT_DELETED");
		}
		return reply;
	}
}
