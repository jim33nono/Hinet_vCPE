package org.yesee.hinet_vcpe_provider.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

public class Interpreter {

	PythonInterpreter interpreter = null;

	public Interpreter() {
		PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);

		this.interpreter = new PythonInterpreter();
	}

	public void execfile(final String fileName) {
		String propFileName = fileName;
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		this.interpreter.execfile(inputStream);
	}
	
	public void execfileBootUp(final String fileName) throws FileNotFoundException {
		String propFileName = fileName;
		File file = new File("C:\\Users\\yesee\\workspace\\Hinet_vCPE\\Hinet_vCPE_provider\\src\\main\\resources\\vyatta\\" + propFileName);
		InputStream inputStream = new FileInputStream(file);
		this.interpreter.execfile(inputStream);
	}

	
	public void execfileReboot(final String fileName) throws FileNotFoundException {
		String propFileName = fileName;
		File file = new File("C:\\Users\\yesee\\workspace\\Hinet_vCPE\\Hinet_vCPE_provider\\src\\main\\resources\\vyatta\\" + propFileName);
		InputStream inputStream = new FileInputStream(file);
		this.interpreter.execfile(inputStream);
	}

	public PyInstance createClass(final String className, final String opts) {
		return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");
	}

}
