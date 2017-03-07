package org.yesee.hinet_vcpe_provider.rest;

import org.yesee.hinet_vcpe_provider.model.bean.Ipsec;
import org.yesee.hinet_vcpe_provider.util.PropertyValues;

public class IpsecWithProperty {
	
	public IpsecWithProperty() {
		
	}
	
	public IpsecWithProperty(Ipsec ipsec, PropertyValues propertyValues) {
		this.ipsec = ipsec;
		this.propertyValues = propertyValues;
	}
	
	private PropertyValues propertyValues;
	private Ipsec ipsec;
	
	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public Ipsec getIpsec() {
		return ipsec;
	}

	public void setIpsec(Ipsec ipsec) {
		this.ipsec = ipsec;
	}

	

}
