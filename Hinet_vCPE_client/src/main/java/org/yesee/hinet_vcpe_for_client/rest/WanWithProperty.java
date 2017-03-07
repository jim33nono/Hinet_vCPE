package org.yesee.hinet_vcpe_for_client.rest;

import org.yesee.hinet_vcpe_for_client.model.bean.Wan;
import org.yesee.hinet_vcpe_for_client.util.PropertyValues;

public class WanWithProperty {
	
	public WanWithProperty() {
		
	}
	
	public WanWithProperty(Wan wan, PropertyValues propertyValues) {
		this.wan = wan;
		this.propertyValues = propertyValues;
	}
	
	private PropertyValues propertyValues;
	private Wan wan;
	
	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public Wan getWan() {
		return wan;
	}

	public void setWan(Wan wan) {
		this.wan = wan;
	}

	

}
