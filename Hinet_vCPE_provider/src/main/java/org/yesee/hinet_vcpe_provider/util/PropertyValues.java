package org.yesee.hinet_vcpe_provider.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyValues {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyValues.class);
	
	private String url;
	private String account;
	private String password;
	private String macAddress;
	
	
	public PropertyValues() {
		
	}
	
	public PropertyValues(String account, String password, String macAddress) {
		this.account = account;
		this.password = password;
		this.macAddress = macAddress;
	}
	
	public PropertyValues(String url, String account, String password, String macAddress) {
		this.url = url;
		this.account = account;
		this.password = password;
		this.macAddress = macAddress;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Override
	public String toString() {
		return "PropertyValues [url=" + url + ", account=" + account + ", password=" + password + ", macAddress="
				+ macAddress + "]";
	}
	
	
	
}
