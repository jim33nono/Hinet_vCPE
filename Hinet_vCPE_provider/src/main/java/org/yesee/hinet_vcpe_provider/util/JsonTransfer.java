package org.yesee.hinet_vcpe_provider.util;

import com.google.gson.Gson;

public class JsonTransfer {
	
	public DataFromPython jsonToObject(String jsonStrong) {
		Gson gson = new Gson();
		DataFromPython dataFromPython = gson.fromJson(jsonStrong, DataFromPython.class);
		return dataFromPython;
	}

	public String objectToJson(DataFromPython dataFromPython) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(dataFromPython);
		return jsonString;
	}

}
