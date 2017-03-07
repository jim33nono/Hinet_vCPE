package org.yesee.hinet_vcpe_for_client.rest;

import org.springframework.web.client.RestTemplate;
import org.yesee.hinet_vcpe_for_client.util.Utility;

public class GatewaySwitchRest {
	
	public static void updateGatewaySwitch(Integer index, GatewaySwitchWithProperty gatewaySwitchWithProperty, String primaryCircuitName, String secondaryCircuitName) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(Utility.properties.getUrl() + "gatewaySwitch/rest/" + index + "/" + primaryCircuitName + "/" + secondaryCircuitName, gatewaySwitchWithProperty);
	}
	
	public static void createGatewaySwitch(GatewaySwitchWithProperty gatewaySwitchWithProperty) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(Utility.properties.getUrl() + "gatewaySwitch/rest/", gatewaySwitchWithProperty, GatewaySwitchWithProperty.class);
    }
	
	public static void deleteGatewaySwitch(Integer index, String macAddress) {
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(Utility.properties.getUrl() + "gatewaySwitch/rest/" + index + "/" + macAddress);
	}


}
