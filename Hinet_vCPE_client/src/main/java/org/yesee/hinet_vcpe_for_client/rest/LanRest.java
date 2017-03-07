package org.yesee.hinet_vcpe_for_client.rest;

import org.springframework.web.client.RestTemplate;
import org.yesee.hinet_vcpe_for_client.util.Utility;

public class LanRest {

	public static void updateLan(LanWithProperty lanWithProperty) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(Utility.properties.getUrl() + "internetSetting/lan/rest/", lanWithProperty);
	}
}
