package org.yesee.hinet_vcpe_for_client.rest;

import org.yesee.hinet_vcpe_for_client.model.bean.GatewaySwitch;
import org.yesee.hinet_vcpe_for_client.util.PropertyValues;

public class GatewaySwitchWithProperty {
	
	public GatewaySwitchWithProperty() {
		
	}
	
	public GatewaySwitchWithProperty(GatewaySwitch gatewaySwitch, PropertyValues propertyValues) {
		this.gatewaySwitch = gatewaySwitch;
		this.propertyValues = propertyValues;
	}
	
	private PropertyValues propertyValues;
	private GatewaySwitch gatewaySwitch;
	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public GatewaySwitch getGatewaySwitch() {
		return gatewaySwitch;
	}

	public void setGatewaySwitch(GatewaySwitch gatewaySwitch) {
		this.gatewaySwitch = gatewaySwitch;
	}

	
	
}
