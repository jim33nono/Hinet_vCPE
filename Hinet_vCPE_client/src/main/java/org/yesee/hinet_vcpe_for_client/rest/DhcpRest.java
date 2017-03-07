package org.yesee.hinet_vcpe_for_client.rest;

import org.springframework.web.client.RestTemplate;
import org.yesee.hinet_vcpe_for_client.model.bean.Dhcp;
import org.yesee.hinet_vcpe_for_client.util.Utility;

public class DhcpRest {
	public static void updateDhcp(DhcpWithProperty dhcpWithProperty) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(Utility.properties.getUrl() + "internetSetting/dhcp/rest/", dhcpWithProperty);
	}
	
}
