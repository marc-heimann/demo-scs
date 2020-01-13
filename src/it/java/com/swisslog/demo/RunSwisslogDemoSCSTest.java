package com.swisslog.demo;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(		
		plugin ={
				"pretty", 
				"html:target/cucumber/demo_scs.html",
				"json:target/cucumber/demo_scs.json"
				},
		features = "src/it/resources",
		tags = {"~@ignored"}
)

public class RunSwisslogDemoSCSTest {
	
}