package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Declares that this is a Feign Client and the url at which forex-service is present is localhost:8100
@FeignClient(name = "forex-service"/*, url = "localhost:8000"*/) //commented out as we are not hardcoding but instead using Ribbon to load balance on port 8000 and 8001
@RibbonClient(name="forex-service")	
//enable @RibbonClient(name="forex-service") when application.properties contain 
//forex-service.ribbon.listOfServers=localhost:8000,localhost:8001
public interface CurrencyExchangeServiceProxy {
	// URI of the service we would want to consume
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyConversionBean retrieveExchangeValue
    (@PathVariable("from") String from, @PathVariable("to") String to);
}

/*
 * If However, we are hardcoding the url for FS in CCS. That means when new
 * instances of FS are launched up we have no way to distribute load between
 * them.
 * 
 * In the next part, we will enable client side load distribution using Ribbon.
 * 
 */
