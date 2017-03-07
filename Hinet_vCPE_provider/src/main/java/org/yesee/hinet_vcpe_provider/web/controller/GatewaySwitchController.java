package org.yesee.hinet_vcpe_provider.web.controller;

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
import org.yesee.hinet_vcpe_provider.model.bean.GatewaySwitch;
import org.yesee.hinet_vcpe_provider.model.bean.Ipsec;
import org.yesee.hinet_vcpe_provider.model.bean.Wan;
import org.yesee.hinet_vcpe_provider.model.service.GatewaySwitchService;
import org.yesee.hinet_vcpe_provider.model.service.IpsecService;
import org.yesee.hinet_vcpe_provider.model.service.WanService;
import org.yesee.hinet_vcpe_provider.util.Utility;
import org.yesee.hinet_vcpe_provider.web.vo.GatewaySwitchVo;

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
	public List<GatewaySwitchVo> showGatewaySwitchList(HttpSession session) {
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		List<GatewaySwitch> gatewaySwitchList = gatewaySwitchService.findAllByMacAddress(macAddress);
		
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
		GatewaySwitch gatewaySwitch = new GatewaySwitch();
		gatewaySwitch.setId(id);
		try {
			gatewaySwitchService.deleteGatewaySwitchById(gatewaySwitch);
			LOGGER.info("Deleting gateway switch as following: " + gatewaySwitch);
			Utility.rebootPython();
			
			runOrStop = true;
			return runOrStop;

		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "getPrimaryAndSecondaryCircuitName", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getPrimaryAndSecondaryCircuitName(HttpSession session) {
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		List<Wan> wanListInfo = wanService.findAllByMacAddress(macAddress);
		List<Ipsec> ipsecListInfo = ipsecService.findAllByMacAddress(macAddress);
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
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		boolean runOrStop = false;
		LOGGER.info("Primary circuit name as following: " + primaryCircuitName
				+ " ,Secondary circuit name as following: " + secondaryCircuitName);
		
		
		if (wanService.findByWanName(primaryCircuitName, macAddress).isPresent()) {
			gatewaySwitch.setPrimaryCircuitWan(wanService.findByWanName(primaryCircuitName, macAddress).get().getId());
		}
		if (wanService.findByWanName(secondaryCircuitName, macAddress).isPresent()) {
			gatewaySwitch.setSecondaryCircuitWan(wanService.findByWanName(secondaryCircuitName, macAddress).get().getId());
		}
		if (ipsecService.findByIpsecName(primaryCircuitName, macAddress).isPresent()) {
			gatewaySwitch.setPrimaryCircuitIpsec(ipsecService.findByIpsecName(primaryCircuitName, macAddress).get().getId());
		}
		if (ipsecService.findByIpsecName(secondaryCircuitName, macAddress).isPresent()) {
			gatewaySwitch.setSecondaryCircuitIpsec(ipsecService.findByIpsecName(secondaryCircuitName, macAddress).get().getId());
		}
		gatewaySwitch.setMacAddress((String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY));
		gatewaySwitch.setUpdateDate(new Date());
		gatewaySwitch.setUserId(userId);

		try {
			gatewaySwitchService.addOrEditGatewaySwitch(gatewaySwitch);
			LOGGER.info("Creating gateway switch as following: " + gatewaySwitch);
			Utility.rebootPython();
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "editGatewaySwitch", method = RequestMethod.POST)
	@ResponseBody
	public boolean editGatewaySwitch(GatewaySwitch gatewaySwitch, String primaryCircuitName,
			String secondaryCircuitName, HttpSession session) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		boolean runOrStop = false;
		LOGGER.info(
				"Primary circuit name as following: " + primaryCircuitName + " ,Secondary circuit name as following: "
						+ secondaryCircuitName + "\n Gateway Switch ID as following: " + gatewaySwitch.getId());
		
		GatewaySwitch gatewaySwitchUpdate = gatewaySwitchService.findById(gatewaySwitch.getId()).get();
		gatewaySwitchUpdate.setPrimaryCircuitWan(null);
		gatewaySwitchUpdate.setPrimaryCircuitIpsec(null);
		gatewaySwitchUpdate.setSecondaryCircuitWan(null);
		gatewaySwitchUpdate.setSecondaryCircuitIpsec(null);
		
		if (wanService.findByWanName(primaryCircuitName, macAddress).isPresent()) {
			gatewaySwitchUpdate.setPrimaryCircuitWan(wanService.findByWanName(primaryCircuitName, macAddress).get().getId());
		}
		if (wanService.findByWanName(secondaryCircuitName, macAddress).isPresent()) {
			gatewaySwitchUpdate.setSecondaryCircuitWan(wanService.findByWanName(secondaryCircuitName, macAddress).get().getId());
		}
		if (ipsecService.findByIpsecName(primaryCircuitName, macAddress).isPresent()) {
			gatewaySwitchUpdate.setPrimaryCircuitIpsec(ipsecService.findByIpsecName(primaryCircuitName, macAddress).get().getId());
		}
		if (ipsecService.findByIpsecName(secondaryCircuitName, macAddress).isPresent()) {
			gatewaySwitchUpdate
					.setSecondaryCircuitIpsec(ipsecService.findByIpsecName(secondaryCircuitName, macAddress).get().getId());
		}
		gatewaySwitchUpdate.setDestinationIp(gatewaySwitch.getDestinationIp());
		gatewaySwitchUpdate.setSubnet(gatewaySwitch.getSubnet());
		gatewaySwitchUpdate.setMsDelay(gatewaySwitch.getMsDelay());
		gatewaySwitchUpdate.setUpdateDate(new Date());
		gatewaySwitchUpdate.setUserId(userId);

		try {
			gatewaySwitchService.addOrEditGatewaySwitch(gatewaySwitchUpdate);
			LOGGER.info("Updating gateway switch as following: " + gatewaySwitchUpdate);
			Utility.rebootPython();
			
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}
	
	@RequestMapping(value = "back")
	public String back(){
		
		return "redirect:/accountManagement/index";
	}

}
