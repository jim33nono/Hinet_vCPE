package org.yesee.hinet_vcpe_provider.web.controller;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yesee.hinet_vcpe_provider.model.service.AccountManagementService;
import org.yesee.hinet_vcpe_provider.util.PropertyValues;
import org.yesee.hinet_vcpe_provider.util.Utility;

@RequestMapping("accountManagement")
@RestController
public class AccountManagementRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagementRestController.class);

	@Autowired
	AccountManagementService accountManagementService;

	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	public ResponseEntity<Void> authorise(@RequestBody PropertyValues propertyValues, HttpServletRequest request) {
		LOGGER.info("Property values: " + propertyValues);
		LOGGER.info("Requesting IP: " + request.getRemoteAddr());

		if (accountManagementService
				.locateUser(propertyValues.getAccount(), propertyValues.getPassword(), propertyValues.getMacAddress())
				.isPresent()) {
			
			try {
				Utility.bootUpPython(request.getRemoteAddr(), propertyValues.getMacAddress());
				
			} catch (Exception e) {
				LOGGER.error("Error: " + e);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

		}

	}

}
