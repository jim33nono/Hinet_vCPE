package org.yesee.hinet_vcpe_for_client.util;

import java.io.IOException;

public class Utility {

	public static final String USER_ID_SESSION_KEY = "userId";

	public static PropertyValues properties = new PropertyValues();

	public static PropertyValues getPropValues() throws IOException {
		 properties.getPropValues();
		 return properties;
	}
	
}
