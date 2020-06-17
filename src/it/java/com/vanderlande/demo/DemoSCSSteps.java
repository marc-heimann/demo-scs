package com.vanderlande.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.vanderlande.demo.businessobjects.AdvancedShippingNotice;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;

public class DemoSCSSteps {

	private static final String USER_AGENT = "Mozilla/5.0";
	
	private long searchTerm = -1;

	private String responseString;

	private String jsonInputString;
	private String apiVersion = "v1";
	private String serverUri = "http://asn.vi-application/"+apiVersion+"/asns/";	
	
	@Before
	public void before() {	
				
	}
	
	@After
	public void after() {
		
	}
	
	@Given("^The client searches for asn '(\\d+)'$")
	public void the_client_searches_for_asn(int arg1) throws Throwable 
	{
		//System.out.println("Client searches for ASN with ID: " + arg1);
		this.searchTerm = arg1;
	}

	@When("^Do GET request$")
	public void do_GET_request() throws Throwable 
	{		
		String navigationString = this.serverUri + this.searchTerm;		
		//System.out.println("Do request: " + navigationString);		
		
		URL obj = new URL(navigationString);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		//System.out.println("GET Response Code: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			this.responseString = response.toString();
		} else {
			System.out.println("GET request did not work");
		}				
	}

	@Then("^The client receives a JSON response with shipNoticeNumber: '(\\d+)'$")
	public void the_client_receives_a_JSON_response_with_shipNoticeNumber(int arg1) throws Throwable {
		System.out.println("the_client_receives_a_JSON_response_with_shipNoticeNumber: " + arg1);	
		Gson gson = new GsonBuilder().create();
			    
	    AdvancedShippingNotice asn = gson.fromJson(this.responseString, AdvancedShippingNotice.class);
	    	    
	    if (asn.getShipNoticeNumber() != arg1) {
	    	throw new IllegalStateException("Received shippingNoticeNumber: " + asn.getShipNoticeNumber() + ". But expected: " + arg1);
	    } else {
	    	//System.out.println("Received shippingNoticeNumber: " + asn.getShipNoticeNumber() + ". Requested was shippingNoticeNumber: " + arg1);
	    	//System.out.println("Full response: " + this.responseString);
	    }
	}
	
	@Given("^The client sends a new asn in JSON format '\"([^\"]*)\"'$")
	public void the_client_sends_a_new_asn_in_JSON_format(String jsonInputString) throws Throwable {
		JsonParser parser = new JsonParser();
		JsonElement elem = parser.parse(jsonInputString);
		jsonInputString = elem.toString();
		System.out.print(jsonInputString);
	    this.jsonInputString = jsonInputString;
	}

	@When("^Do POST request$")
	public void do_POST_request() throws Throwable {
		String navigationString = this.serverUri;		
		//System.out.println("Do request: " + navigationString);		
		
		URL obj = new URL(navigationString);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/hal+json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = jsonInputString.getBytes("utf-8");
		    os.write(input, 0, input.length);           
		}
		
		
		int responseCode = con.getResponseCode();
		//System.out.println("GET Response Code: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			this.responseString = response.toString();
		} else {
			System.out.println("GET request did not work");
		}
	}

	@Then("^The client receives a JSON response with the result of: '\"([^\"]*)\"'$")
	public void the_client_receives_a_JSON_response_with_the_result_of(String arg1) throws Throwable {
		System.out.print(arg1);
	}
	
	@Given("^The client requests the StockKeepingUnits for an asn with the id: '(\\d+)'$")
	public void the_client_requests_the_StockKeepingUnits_for_an_asn_with_the_id(int arg1) throws Throwable {
	    System.out.print("^The client requests the StockKeepingUnits for an asn with the id: '(\\\\d+)'$");	    
	}
}
