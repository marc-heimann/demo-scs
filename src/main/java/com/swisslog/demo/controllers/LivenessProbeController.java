package com.swisslog.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class LivenessProbeController {

	static LivenessProbeController instance = null;
	boolean live = false;
	public static LivenessProbeController getInstance() {
		if (instance == null)
			instance = new LivenessProbeController();
		
		return instance;
	}			
	
	@GetMapping(value = "/livenessProbe")
    public ResponseEntity<Object> getStatus() {
		if (live) {
			return new ResponseEntity<>(new Object(), HttpStatus.OK);	
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}        
    }
	
}
