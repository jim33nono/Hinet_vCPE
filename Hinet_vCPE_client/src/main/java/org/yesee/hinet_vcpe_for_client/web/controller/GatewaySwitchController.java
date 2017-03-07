package org.yesee.hinet_vcpe_for_client.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yesee.hinet_vcpe_for_client.model.bean.GatewaySwitch;
import org.yesee.hinet_vcpe_for_client.model.bean.Ipsec;
import org.yesee.hinet_vcpe_for_client.model.bean.Wan;
import org.yesee.hinet_vcpe_for_client.model.service.GatewaySwitchService;
import org.yesee.hinet_vcpe_for_client.model.service.IpsecService;
import org.yesee.hinet_vcpe_for_client.model.service.WanService;
import org.yesee.hinet_vcpe_for_client.rest.GatewaySwitchRest;
import org.yesee.hinet_vcpe_for_client.rest.GatewaySwitchWithProperty;
import org.yesee.hinet_vcpe_for_client.util.Utility;
import org.yesee.hinet_vcpe_for_client.web.vo.GatewaySwitchVo;

import com.google.common.collect.Lists;

@RequestMapping("gatewaySwitch")
@Controller
public class GatewaySwitchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewaySwitchController.class);

	private @Autowired GatewaySwitchService gatewaySwitchService;
	private @Autowired WanService wanService;
	private @Autowired IpsecService ipsecService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "gatewaySwitch";
	}

	@RequestMapping(value = "showGatewaySwitchList", method = RequestMethod.GET)
	@ResponseBody
	public List<GatewaySwitchVo> showGatewaySwitchList() {
		List<GatewaySwitch> gatewaySwitchList = gatewaySwitchService.findAll();
		List<GatewaySwitchVo> gatewaySwitchVoList = Lists.newArrayList();
		for (GatewaySwitch gatewaySwitch : gatewaySwitchList) {
			GatewaySwitchVo gatewaySwitchVo = new GatewaySwitchVo();
			gatewaySwitchVo.setDestinationIp(gatewaySwitch.getDestinationIp());
			gatewaySwitchVo.setSubnet(gatewaySwitch.getSubnet());
			gatewaySwitchVo.setMsDelay(gatewaySwitch.getMsDelay());
			gatewaySwitchVo.setId(gatewaySwitch.getId());
			if ((gatewaySwitch.getPrimaryCircuitWan() != null || gatewaySwitch.getPrimaryCircuitIpsec() != null)
					&& (gatewaySwitch.getSecondaryCircuitWan() != null
							|| gatewaySwitch.getSecondaryCircuitIpsec() != null)) {

				if (gatewaySwitch.getPrimaryCircuitWan() != null) {
					if (wanService.findById(gatewaySwitch.getPrimaryCircuitWan()).isPresent()) {
						gatewaySwitchVo.setPrimaryCircuit(
								wanService.findById(gatewaySwitch.getPrimaryCircuitWan()).get().getWanName());
					}
				} else {
					if (ipsecService.findById(gatewaySwitch.getPrimaryCircuitIpsec()).isPresent()) {
						gatewaySwitchVo.setPrimaryCircuit(
								ipsecService.findById(gatewaySwitch.getPrimaryCircuitIpsec()).get().getIpsecName());
					}
				}
				if (gatewaySwitch.getSecondaryCircuitWan() != null) {
					if (wanService.findById(gatewaySwitch.getSecondaryCircuitWan()).isPresent()) {
						gatewaySwitchVo.setSecondaryCircuit(
								wanService.findById(gatewaySwitch.getSecondaryCircuitWan()).get().getWanName());
					}
				} else {
					if (ipsecService.findById(gatewaySwitch.getSecondaryCircuitIpsec()).isPresent()) {
						gatewaySwitchVo.setSecondaryCircuit(
								ipsecService.findById(gatewaySwitch.getSecondaryCircuitIpsec()).get().getIpsecName());
					}
				}

			}

			gatewaySwitchVoList.add(gatewaySwitchVo);

		}

		return gatewaySwitchVoList;

	}

	@RequestMapping(value = "deleteGatewaySwitch", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteGatewaySwitch(Integer id) {
		boolean runOrStop = false;
		LOGGER.info("Delete gateway switch id as follwinig: " + id);
		List<GatewaySwitch> gatewaySwitchList = gatewaySwitchService.findAll();
		GatewaySwitch gatewaySwitchUpdate = gatewaySwitchService.findById(id).get();
		Integer index = gatewaySwitchList.indexOf(gatewaySwitchUpdate);
		GatewaySwitch gatewaySwitch = new GatewaySwitch();
		gatewaySwitch.setId(id);
		try {
			gatewaySwitchService.deleteGatewaySwitchById(gatewaySwitch);
			LOGGER.info("Deleting gateway switch as following: " + gatewaySwitch);
			Utility.getPropValues();
			GatewaySwitchRest.deleteGatewaySwitch(index, Utility.properties.getMacAddress());
			
			
			runOrStop = true;
			return runOrStop;

		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "getPrimaryAndSecondaryCircuitName", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getPrimaryAndSecondaryCircuitName() {
		List<Wan> wanListInfo = wanService.findAll();
		List<Ipsec> ipsecListInfo = ipsecService.findAll();
		List<String> circuitNameList = Lists.newArrayList();
		for (Wan wan : wanListInfo) {
			circuitNameList.add(wan.getWanName());
		}
		for (Ipsec ipsec : ipsecListInfo) {
			circuitNameList.add(ipsec.getIpsecName());
		}

		return circuitNameList;

	}

	@RequestMapping(value = "createGatewaySwitch", method = RequestMethod.POST)
	@ResponseBody
	public boolean createGatewaySwitch(GatewaySwitch gatewaySwitch, String primaryCircuitName,
			String secondaryCircuitName, HttpSession session) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		boolean runOrStrop = false;
		LOGGER.info("Primary circuit name as following: " + primaryCircuitName
				+ " ,Secondary circuit name as following: " + secondaryCircuitName);

		if (wanService.findByWanName(primaryCircuitName).isPresent()) {
			gatewaySwitch.setPrimaryCircuitWan(wanService.findByWanName(primaryCircuitName).get().getId());
		}
		if (wanService.findByWanName(secondaryCircuitName).isPresent()) {
			gatewaySwitch.setSecondaryCircuitWan(wanService.findByWanName(secondaryCircuitName).get().getId());
		}
		if (ipsecService.findByIpsecName(primaryCircuitName).isPresent()) {
			gatewaySwitch.setPrimaryCircuitIpsec(ipsecService.findByIpsecName(primaryCircuitName).get().getId());
		}
		if (ipsecService.findByIpsecName(secondaryCircuitName).isPresent()) {
			gatewaySwitch.setSecondaryCircuitIpsec(ipsecService.findByIpsecName(secondaryCircuitName).get().getId());
		}

		gatewaySwitch.setUpdateDate(new Date());
		gatewaySwitch.setUserId(userId);

		try {
			gatewaySwitchService.addOrEditGatewaySwitch(gatewaySwitch);
			LOGGER.info("Creating gateway switch as following: " + gatewaySwitch);
			Utility.getPropValues();
			GatewaySwitchWithProperty gatewaySwitchWithProperty = new GatewaySwitchWithProperty(gatewaySwitch, Utility.properties);
			GatewaySwitchRest.createGatewaySwitch(gatewaySwitchWithProperty);
			
			runOrStrop = true;
			return runOrStrop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStrop;
		}

	}

	@RequestMapping(value = "editGatewaySwitch", method = RequestMethod.POST)
	@ResponseBody
	public boolean editGatewaySwitch(GatewaySwitch gatewaySwitch, String primaryCircuitName,
			String secondaryCircuitName, HttpSession session) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		boolean runOrStop = false;
		LOGGER.info(
				"Primary circuit name as following: " + primaryCircuitName + " ,Secondary circuit name as following: "
						+ secondaryCircuitName + "\n Gateway Switch ID as following: " + gatewaySwitch.getId());
		List<GatewaySwitch> gatewaySwitchList = gatewaySwitchService.findAll();
		GatewaySwitch gatewaySwitchUpdate = gatewaySwitchService.findById(gatewaySwitch.getId()).get();
		Integer index = gatewaySwitchList.indexOf(gatewaySwitchUpdate);
		gatewaySwitchUpdate.setPrimaryCircuitWan(null);
		gatewaySwitchUpdate.setPrimaryCircuitIpsec(null);
		gatewaySwitchUpdate.setSecondaryCircuitWan(null);
		gatewaySwitchUpdate.setSecondaryCircuitIpsec(null);
		
		if (wanService.findByWanName(primaryCircuitName).isPresent()) {
			gatewaySwitchUpdate.setPrimaryCircuitWan(wanService.findByWanName(primaryCircuitName).get().getId());
		}
		if (wanService.findByWanName(secondaryCircuitName).isPresent()) {
			gatewaySwitchUpdate.setSecondaryCircuitWan(wanService.findByWanName(secondaryCircuitName).get().getId());
		}
		if (ipsecService.findByIpsecName(primaryCircuitName).isPresent()) {
			gatewaySwitchUpdate.setPrimaryCircuitIpsec(ipsecService.findByIpsecName(primaryCircuitName).get().getId());
		}
		if (ipsecService.findByIpsecName(secondaryCircuitName).isPresent()) {
			gatewaySwitchUpdate
					.setSecondaryCircuitIpsec(ipsecService.findByIpsecName(secondaryCircuitName).get().getId());
		}
		gatewaySwitchUpdate.setDestinationIp(gatewaySwitch.getDestinationIp());
		gatewaySwitchUpdate.setSubnet(gatewaySwitch.getSubnet());
		gatewaySwitchUpdate.setMsDelay(gatewaySwitch.getMsDelay());
		gatewaySwitchUpdate.setUpdateDate(new Date());
		gatewaySwitchUpdate.setUserId(userId);

		try {
			gatewaySwitchService.addOrEditGatewaySwitch(gatewaySwitchUpdate);
			LOGGER.info("primaryCircuitName and secondaryCircuitName as following: " + primaryCircuitName + ", " + secondaryCircuitName);
			LOGGER.info("Updating gateway switch as following: " + gatewaySwitchUpdate);
			Utility.getPropValues();
			GatewaySwitchWithProperty gatewaySwitchWithProperty = new GatewaySwitchWithProperty(gatewaySwitchUpdate, Utility.properties);
			GatewaySwitchRest.updateGatewaySwitch(index, gatewaySwitchWithProperty, primaryCircuitName, secondaryCircuitName);
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

}
