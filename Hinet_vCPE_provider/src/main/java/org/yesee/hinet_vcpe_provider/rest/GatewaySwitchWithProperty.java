package org.yesee.hinet_vcpe_provider.rest;

import org.yesee.hinet_vcpe_provider.model.bean.GatewaySwitch;
import org.yesee.hinet_vcpe_provider.util.PropertyValues;

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
