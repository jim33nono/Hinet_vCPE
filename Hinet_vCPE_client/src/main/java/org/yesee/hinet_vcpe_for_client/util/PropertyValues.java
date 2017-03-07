package org.yesee.hinet_vcpe_for_client.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	
	String result = "";
	InputStream inputStream;
 
	public void getPropValues() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// get the property value and print it out
			String url = prop.getProperty("api.url");
			String account = prop.getProperty("api.account");
			String password = prop.getProperty("api.password");
			String macAddress = prop.getProperty("api.macAddress");
			this.setUrl(url);
			this.setAccount(account);
			this.setPassword(password);
			this.setMacAddress(macAddress);
 
		} catch (Exception e) {
			LOGGER.error("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
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
