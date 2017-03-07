package org.yesee.hinet_vcpe_provider.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatchPythonValue {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CatchPythonValue.class);
	public ByteArrayOutputStream baos = new ByteArrayOutputStream();

	public String getPythonData() {
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		Utility.triggerPython();
		System.setOut(old);
		String result = baos.toString();
		baos.reset();
		return result;
		
	}
	
//	public String getPythonDataVersion2() throws FileNotFoundException {
//		PrintStream ps = new PrintStream(baos);
//		PrintStream old = System.out;
//		System.setOut(ps);
//		Utility.rebootPython();
//		System.setOut(old);
//		String result = baos.toString();
//		baos.reset();
//		return result;
//		
//	}

}
