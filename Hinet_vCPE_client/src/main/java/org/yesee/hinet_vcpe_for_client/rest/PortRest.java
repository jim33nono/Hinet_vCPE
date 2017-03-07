package org.yesee.hinet_vcpe_for_client.rest;

import org.springframework.web.client.RestTemplate;
import org.yesee.hinet_vcpe_for_client.util.Utility;

public class PortRest {

	public static void updatePort(PortWithProperty portWithProperty) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(Utility.properties.getUrl() + "internetSetting/port/rest/" + portWithProperty.getPort().getId(), portWithProperty);
	}

}
