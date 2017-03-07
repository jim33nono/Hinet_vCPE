package org.yesee.hinet_vcpe_provider.util;


import org.python.core.PyInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

	private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

	public static final String USER_ID_SESSION_KEY = "userId";
	public static final String MAC_ADDRESS_SESSION_KEY = "macAddress";

	public static CatchPythonValue catchPythonValue = new CatchPythonValue();
	public static JsonTransfer jsonTransfer = new JsonTransfer();
	public static PropertyValues properties = new PropertyValues();

	public static void triggerPython() {
		Interpreter ie = new Interpreter();
		ie.execfile("pythonCode.py");
		PyInstance pyInstance = ie.createClass("PythonCode", "None");
		pyInstance.invoke("run");
	}

	public static void bootUpPython() {
		try {
			String cmd = "python /opt/bin/vyatta/api_set_config.py";
			Runtime.getRuntime().exec(cmd);
			LOGGER.info("Boot up python!");
		} catch (Exception e) {
			LOGGER.info("Error: " + e);
		}
	}
	
	public static void bootUpPython(String ip, String macAddress) {
		try {
			String cmd = "python /opt/bin/vyatta/api_set_config.py" + " " + ip + " " + macAddress;
			Runtime.getRuntime().exec(cmd);
			LOGGER.info("Boot up python!");
		} catch (Exception e) {
			LOGGER.info("Error: " + e);
		}
	}
	
	public static void rebootPython() {
		try {
			String cmd = "python /opt/bin/vyatta/api_set_reboot.py";
			Runtime.getRuntime().exec(cmd);
			LOGGER.info("Reboot python!");
		} catch (Exception e) {
			LOGGER.info("Error: " + e);
		}
	}

	public static void rebootPython(String ip, String macAddress) {
		try {
			String cmd = "python /opt/bin/vyatta/api_set_reboot.py" + " " + ip + " " + macAddress;
			Runtime.getRuntime().exec(cmd);
			LOGGER.info("Reboot python!");
		} catch (Exception e) {
			LOGGER.info("Error: " + e);
		}
	}

}
