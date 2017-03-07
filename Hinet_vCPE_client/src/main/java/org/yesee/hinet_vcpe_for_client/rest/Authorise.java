package org.yesee.hinet_vcpe_for_client.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.yesee.hinet_vcpe_for_client.util.Utility;
import org.yesee.hinet_vcpe_for_client.util.PropertyValues;

public class Authorise {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Authorise.class);
	
	public static void authorise() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		Utility.getPropValues();
		LOGGER.info("Needed info for restful API: " + Utility.properties);
		restTemplate.postForLocation(Utility.properties.getUrl() + "accountManagement/rest", Utility.properties, PropertyValues.class);
	}

}
