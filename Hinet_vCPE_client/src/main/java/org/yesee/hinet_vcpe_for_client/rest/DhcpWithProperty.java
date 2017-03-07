package org.yesee.hinet_vcpe_for_client.rest;

import org.yesee.hinet_vcpe_for_client.model.bean.Dhcp;
import org.yesee.hinet_vcpe_for_client.util.PropertyValues;

public class DhcpWithProperty {
	
	public DhcpWithProperty() {
		
	}
	
	public DhcpWithProperty(Dhcp dhcp, PropertyValues propertyValues) {
		this.dhcp = dhcp;
		this.propertyValues = propertyValues;
	}
	
	private PropertyValues propertyValues;
	private Dhcp dhcp;
	
	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public Dhcp getDhcp() {
		return dhcp;
	}

	public void setDhcp(Dhcp dhcp) {
		this.dhcp = dhcp;
	}
	
	

}
