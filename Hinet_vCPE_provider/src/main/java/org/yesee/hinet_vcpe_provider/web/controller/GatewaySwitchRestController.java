package org.yesee.hinet_vcpe_provider.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yesee.hinet_vcpe_provider.model.bean.GatewaySwitch;
import org.yesee.hinet_vcpe_provider.model.service.GatewaySwitchService;
import org.yesee.hinet_vcpe_provider.model.service.IpsecService;
import org.yesee.hinet_vcpe_provider.model.service.WanService;
import org.yesee.hinet_vcpe_provider.rest.GatewaySwitchWithProperty;
import org.yesee.hinet_vcpe_provider.util.Utility;

@RequestMapping(value = "gatewaySwitch")
@RestController
public class GatewaySwitchRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewaySwitchRestController.class);

	private @Autowired GatewaySwitchService gatewaySwitchService;
	private @Autowired WanService wanService;
	private @Autowired IpsecService ipsecService;

	@RequestMapping(value = "/rest/{index}/{primaryCircuitName}/{secondaryCircuitName}", method = RequestMethod.PUT)
	public ResponseEntity<GatewaySwitch> updateGatewaySwitch(@PathVariable("index") Integer index,
			@PathVariable("primaryCircuitName") String primaryCircuitName,
			@PathVariable("secondaryCircuitName") String secondaryCircuitName,
			@RequestBody GatewaySwitchWithProperty gatewaySwitchWithProperty, HttpServletRequest request) {
		LOGGER.info("Updating GatewaySwitch: " + gatewaySwitchWithProperty.getGatewaySwitch() + primaryCircuitName + secondaryCircuitName);

		List<GatewaySwitch> currentGatewaySwitchList = gatewaySwitchService
				.findAllByMacAddress(gatewaySwitchWithProperty.getPropertyValues().getMacAddress());
		GatewaySwitch gatewaySwitchUpdate = currentGatewaySwitchList.get(index);
		if (currentGatewaySwitchList == null || gatewaySwitchUpdate == null) {
			GatewaySwitch newGatewaySwitch = new GatewaySwitch();
			newGatewaySwitch.setMacAddress(gatewaySwitchWithProperty.getPropertyValues().getMacAddress());
			gatewaySwitchService.addOrEditGatewaySwitch(newGatewaySwitch);
			return new ResponseEntity<GatewaySwitch>(HttpStatus.NOT_FOUND);
		}

		gatewaySwitchUpdate.setPrimaryCircuitWan(null);
		gatewaySwitchUpdate.setPrimaryCircuitIpsec(null);
		gatewaySwitchUpdate.setSecondaryCircuitWan(null);
		gatewaySwitchUpdate.setSecondaryCircuitIpsec(null);
		if (wanService.findByWanName(primaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).isPresent()) {
			gatewaySwitchUpdate.setPrimaryCircuitWan(
					wanService.findByWanName(primaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).get().getId());
		}
		if (wanService.findByWanName(secondaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).isPresent()) {
			gatewaySwitchUpdate.setSecondaryCircuitWan(
					wanService.findByWanName(secondaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).get().getId());
		}
		if (ipsecService.findByIpsecName(primaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).isPresent()) {
			gatewaySwitchUpdate.setPrimaryCircuitIpsec(
					ipsecService.findByIpsecName(primaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).get().getId());
		}
		if (ipsecService.findByIpsecName(secondaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).isPresent()) {
			gatewaySwitchUpdate.setSecondaryCircuitIpsec(
					ipsecService.findByIpsecName(secondaryCircuitName, gatewaySwitchWithProperty.getPropertyValues().getMacAddress()).get().getId());
		}

		gatewaySwitchUpdate.setDestinationIp(gatewaySwitchWithProperty.getGatewaySwitch().getDestinationIp());
		gatewaySwitchUpdate.setSubnet(gatewaySwitchWithProperty.getGatewaySwitch().getSubnet());
		gatewaySwitchUpdate.setMsDelay(gatewaySwitchWithProperty.getGatewaySwitch().getMsDelay());
		gatewaySwitchUpdate.setUpdateDate(gatewaySwitchWithProperty.getGatewaySwitch().getUpdateDate());
		gatewaySwitchUpdate.setUserId("Client:" + gatewaySwitchWithProperty.getGatewaySwitch().getUserId());
		
		try {
			gatewaySwitchService.addOrEditGatewaySwitch(gatewaySwitchUpdate);
			LOGGER.info("Updating gateway switch as following: " + gatewaySwitchUpdate);
			Utility.rebootPython(request.getRemoteAddr(), gatewaySwitchWithProperty.getPropertyValues().getMacAddress());
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}
		
		return new ResponseEntity<GatewaySwitch>(gatewaySwitchUpdate, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	public ResponseEntity<Void> createGatewaySwitch(@RequestBody GatewaySwitchWithProperty gatewaySwitchWithProperty, HttpServletRequest request) {
		LOGGER.info("Creating gateway switch as following: " + gatewaySwitchWithProperty.getGatewaySwitch());
		gatewaySwitchWithProperty.getGatewaySwitch().setMacAddress(gatewaySwitchWithProperty.getPropertyValues().getMacAddress());
		gatewaySwitchWithProperty.getGatewaySwitch().setId(null);
		gatewaySwitchWithProperty.getGatewaySwitch().setUserId("Client:" + gatewaySwitchWithProperty.getGatewaySwitch().getUserId());
	
		try {
			gatewaySwitchService.addOrEditGatewaySwitch(gatewaySwitchWithProperty.getGatewaySwitch());
			LOGGER.info("Creating gateway switch as following: " + gatewaySwitchWithProperty.getGatewaySwitch());
			Utility.rebootPython(request.getRemoteAddr(), gatewaySwitchWithProperty.getPropertyValues().getMacAddress());
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/{index}/{macAddress}", method = RequestMethod.DELETE)
	public ResponseEntity<GatewaySwitch> deleteGatewaySwitch(@PathVariable("index") Integer index, @PathVariable("macAddress")  String macAddress, HttpServletRequest request) {
		List<GatewaySwitch> currentGatewaySwitchList = gatewaySwitchService.findAllByMacAddress(macAddress);
		GatewaySwitch gatewaySwitch = currentGatewaySwitchList.get(index);
		if (currentGatewaySwitchList == null || gatewaySwitch == null) {
			LOGGER.info("Unable to delete gatewaySwitch with index: " + index + " not found");
			return new ResponseEntity<GatewaySwitch>(HttpStatus.NOT_FOUND);
		}
		
		try {
			gatewaySwitchService.deleteGatewaySwitchById(gatewaySwitch);
			LOGGER.info("Deleting gateway switch as following: " + gatewaySwitch);
			Utility.rebootPython(request.getRemoteAddr(), macAddress);
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}
		
		return new ResponseEntity<GatewaySwitch>(HttpStatus.NO_CONTENT);
	}
	
	
}
