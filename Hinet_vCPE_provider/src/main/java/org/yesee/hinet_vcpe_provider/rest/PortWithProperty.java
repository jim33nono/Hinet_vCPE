package org.yesee.hinet_vcpe_provider.rest;

import org.yesee.hinet_vcpe_provider.model.bean.Port;
import org.yesee.hinet_vcpe_provider.util.PropertyValues;

public class PortWithProperty {

	public PortWithProperty() {

	}
	
	public PortWithProperty(Port port, PropertyValues propertyValues) {
		this.port = port;
		this.propertyValues = propertyValues;
	}

	private PropertyValues propertyValues;
	private Port port;

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

}
