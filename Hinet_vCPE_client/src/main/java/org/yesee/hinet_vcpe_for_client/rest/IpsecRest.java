package org.yesee.hinet_vcpe_for_client.rest;

import org.springframework.web.client.RestTemplate;
import org.yesee.hinet_vcpe_for_client.model.bean.Ipsec;
import org.yesee.hinet_vcpe_for_client.util.Utility;

public class IpsecRest {

	public static void updateIpsec(Integer index, IpsecWithProperty ipsecWithProperty) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(Utility.properties.getUrl() + "internetSetting/ipsec/rest/" + index, ipsecWithProperty);
	}

	public static void createIpsec(IpsecWithProperty ipsecWithProperty) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForLocation(Utility.properties.getUrl() + "internetSetting/ipsec/rest/", ipsecWithProperty, IpsecWithProperty.class);
	}

	public static void deleteIpsec(Integer index, String macAddress) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(Utility.properties.getUrl() + "internetSetting/ipsec/rest/" + index + "/" + macAddress);
	}

}
