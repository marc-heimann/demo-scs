package com.vanderlande.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class ReadynessProbeController {

	@GetMapping(value = "/readynessProbe")
    public ResponseEntity<Object> getReadynessProbe() {
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }
	
}
