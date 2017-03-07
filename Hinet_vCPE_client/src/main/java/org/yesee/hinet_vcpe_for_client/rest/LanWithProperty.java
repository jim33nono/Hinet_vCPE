package org.yesee.hinet_vcpe_for_client.rest;

import org.yesee.hinet_vcpe_for_client.model.bean.Lan;
import org.yesee.hinet_vcpe_for_client.util.PropertyValues;

public class LanWithProperty {

	public LanWithProperty() {
		
	}
	
	public LanWithProperty(Lan lan, PropertyValues propertyValues) {
		this.lan = lan;
		this.propertyValues = propertyValues;
	}
	
	private PropertyValues propertyValues;
	private Lan lan;
	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public Lan getLan() {
		return lan;
	}

	public void setLan(Lan lan) {
		this.lan = lan;
	}
	
	
	
}
