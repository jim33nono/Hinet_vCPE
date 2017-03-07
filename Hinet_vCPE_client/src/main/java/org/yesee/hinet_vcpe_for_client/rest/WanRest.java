package org.yesee.hinet_vcpe_for_client.rest;

import org.springframework.web.client.RestTemplate;
import org.yesee.hinet_vcpe_for_client.util.Utility;

public class WanRest {
	
	public static void updateWan(Integer index, WanWithProperty wanWithProperty) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(Utility.properties.getUrl() + "internetSetting/wan/rest/" + index, wanWithProperty);
	}
	
	public static void createWan(WanWithProperty wanWithProperty) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(Utility.properties.getUrl() + "internetSetting/wan/rest/", wanWithProperty, WanWithProperty.class);
    }
	
	public static void deleteWan(Integer index, String macAddress) {
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(Utility.properties.getUrl() + "internetSetting/wan/rest/" + index + "/" + macAddress);
	}

}
