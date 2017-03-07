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
import org.yesee.hinet_vcpe_provider.model.bean.Dhcp;
import org.yesee.hinet_vcpe_provider.model.bean.Ipsec;
import org.yesee.hinet_vcpe_provider.model.bean.Lan;
import org.yesee.hinet_vcpe_provider.model.bean.Port;
import org.yesee.hinet_vcpe_provider.model.bean.Wan;
import org.yesee.hinet_vcpe_provider.model.bean.Wan.DefaultSetting;
import org.yesee.hinet_vcpe_provider.model.service.DhcpService;
import org.yesee.hinet_vcpe_provider.model.service.IpsecService;
import org.yesee.hinet_vcpe_provider.model.service.LanService;
import org.yesee.hinet_vcpe_provider.model.service.PortService;
import org.yesee.hinet_vcpe_provider.model.service.WanService;
import org.yesee.hinet_vcpe_provider.rest.DhcpWithProperty;
import org.yesee.hinet_vcpe_provider.rest.IpsecWithProperty;
import org.yesee.hinet_vcpe_provider.rest.LanWithProperty;
import org.yesee.hinet_vcpe_provider.rest.PortWithProperty;
import org.yesee.hinet_vcpe_provider.rest.WanWithProperty;
import org.yesee.hinet_vcpe_provider.util.Utility;

@RequestMapping("internetSetting")
@RestController
public class InternetSettingRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternetSettingRestController.class);

	private @Autowired PortService portService;
	private @Autowired DhcpService dhcpService;
	private @Autowired WanService wanService;
	private @Autowired IpsecService ipsecService;
	private @Autowired LanService lanService;

	@RequestMapping(value = "port/rest/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Port> updatePort(@PathVariable("id") Integer id,
			@RequestBody PortWithProperty portWithProperty, HttpServletRequest request) {
		LOGGER.info("Updating port index: " + id);

		List<Port> currentPortList = portService
				.findAllByMacAddress(portWithProperty.getPropertyValues().getMacAddress());

		if (currentPortList == null) {
			Port newPort = new Port();
			newPort.setMacAddress(portWithProperty.getPropertyValues().getMacAddress());
			for (int i = 0; i < 6; i++) {
				portService.addOrEditPort(newPort);
			}
			return new ResponseEntity<Port>(HttpStatus.NOT_FOUND);
		}
		Port portUpdate = currentPortList.get(id - 1);
		portUpdate.setPortName(portWithProperty.getPort().getPortName());
		
		try {
			portService.addOrEditPort(portUpdate);
			LOGGER.info("Updating port as following: " + portUpdate);
			Utility.rebootPython(request.getRemoteAddr(), portWithProperty.getPropertyValues().getMacAddress());
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Port>(portUpdate, HttpStatus.OK);
	}

	@RequestMapping(value = "dhcp/rest", method = RequestMethod.PUT)
	public ResponseEntity<Dhcp> updateDhcp(@RequestBody DhcpWithProperty dhcpWithProperty, HttpServletRequest request) {
		LOGGER.info("Updating DHCP: " + dhcpWithProperty.getDhcp());

		Dhcp currentDhcp = dhcpService.findByMacAddress(dhcpWithProperty.getPropertyValues().getMacAddress()).get();

		if (currentDhcp == null) {
			Dhcp newDhcp = new Dhcp();
			newDhcp.setMacAddress(dhcpWithProperty.getPropertyValues().getMacAddress());
			dhcpService.addOrEditDhcp(newDhcp);
			return new ResponseEntity<Dhcp>(HttpStatus.NOT_FOUND);
		}
		currentDhcp.setDefaultGateway(dhcpWithProperty.getDhcp().getDefaultGateway());
		currentDhcp.setDns1(dhcpWithProperty.getDhcp().getDns1());
		currentDhcp.setStartIp(dhcpWithProperty.getDhcp().getStartIp());
		currentDhcp.setEndIp(dhcpWithProperty.getDhcp().getEndIp());
		currentDhcp.setUserId("Client:" + dhcpWithProperty.getDhcp().getUserId());
		currentDhcp.setUpdateDate(dhcpWithProperty.getDhcp().getUpdateDate());
		
		try {
			dhcpService.addOrEditDhcp(currentDhcp);
			LOGGER.info("Updating DHCP as following: " + currentDhcp);
			Utility.rebootPython(request.getRemoteAddr(), dhcpWithProperty.getPropertyValues().getMacAddress());
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Dhcp>(currentDhcp, HttpStatus.OK);
	}

	@RequestMapping(value = "wan/rest/{index}", method = RequestMethod.PUT)
	public ResponseEntity<Wan> updateWan(@PathVariable("index") Integer index,
			@RequestBody WanWithProperty wanWithProperty, HttpServletRequest request) {
		LOGGER.info("Updating WAN: " + wanWithProperty.getWan());

		List<Wan> currentWanList = wanService.findAllByMacAddress(wanWithProperty.getPropertyValues().getMacAddress());
		Wan wanUpdate = currentWanList.get(index);

		if (currentWanList == null || wanUpdate == null) {
			Wan newWan = new Wan();
			newWan.setMacAddress(wanWithProperty.getPropertyValues().getMacAddress());
			wanService.addOrEditWan(newWan);
			return new ResponseEntity<Wan>(HttpStatus.NOT_FOUND);
		}

		wanUpdate.setWanName(wanWithProperty.getWan().getWanName());
		List<Port> portList = portService.findAllByMacAddress(wanWithProperty.getPropertyValues().getMacAddress());
		Port portLocated = portList.get(wanWithProperty.getWan().getPortId() - 1);
		wanUpdate.setPortId(portLocated.getId()); //
		wanUpdate.setWanIp(wanWithProperty.getWan().getWanIp());
		wanUpdate.setSubnet(wanWithProperty.getWan().getSubnet());
		wanUpdate.setUserId("Client:" + wanWithProperty.getWan().getUserId());
		wanUpdate.setUpdateDate(wanWithProperty.getWan().getUpdateDate());
		if (wanWithProperty.getWan().getDefaultSetting().equals(DefaultSetting.Yes)) {
			for (Wan wan : currentWanList) {
				wan.setDefaultSetting(DefaultSetting.No);
				wanService.addOrEditWan(wan);
			}
			wanUpdate.setDefaultSetting(DefaultSetting.Yes);

		} else {
			wanUpdate.setDefaultSetting(DefaultSetting.No);
		}
		try {
			wanService.addOrEditWan(wanUpdate);
			LOGGER.info("Updating WAN as following: " + wanUpdate);
			Utility.rebootPython(request.getRemoteAddr(), wanWithProperty.getPropertyValues().getMacAddress());
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Wan>(wanUpdate, HttpStatus.OK);
	}

	@RequestMapping(value = "/wan/rest", method = RequestMethod.POST)
	public ResponseEntity<Void> createWan(@RequestBody WanWithProperty wanWithProperty, HttpServletRequest request) {
		LOGGER.info("Creating WAN as following: " + wanWithProperty.getWan());
		wanWithProperty.getWan().setMacAddress(wanWithProperty.getPropertyValues().getMacAddress());
		wanWithProperty.getWan().setId(null);
		wanWithProperty.getWan().setUserId("Client:" + wanWithProperty.getWan().getUserId());
		
		try {
			wanService.addOrEditWan(wanWithProperty.getWan());
			LOGGER.info("Creating WAN as following: " + wanWithProperty.getWan());
			Utility.rebootPython(request.getRemoteAddr(), wanWithProperty.getPropertyValues().getMacAddress());
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/wan/rest/{index}/{macAddress}", method = RequestMethod.DELETE)
	public ResponseEntity<Wan> deleteWan(@PathVariable("index") Integer index,
			@PathVariable("macAddress") String macAddress, HttpServletRequest request) {
		List<Wan> currentWanList = wanService.findAllByMacAddress(macAddress);
		Wan wan = currentWanList.get(index);
		if (currentWanList == null || wan == null) {
			LOGGER.info("Unable to delete WAN with index: " + index + " not found");
			return new ResponseEntity<Wan>(HttpStatus.NOT_FOUND);
		}

		try {
			wanService.deleteWanById(wan);
			LOGGER.info("Deleting WAN as following: " + wan);
			Utility.rebootPython(request.getRemoteAddr(), macAddress);
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Wan>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/ipsec/rest/{index}", method = RequestMethod.PUT)
	public ResponseEntity<Ipsec> updateIpsec(@PathVariable("index") Integer index,
			@RequestBody IpsecWithProperty ipsecWithProperty, HttpServletRequest request) {
		LOGGER.info("Updating IPsec: " + ipsecWithProperty.getIpsec());

		List<Ipsec> currentIpsecList = ipsecService
				.findAllByMacAddress(ipsecWithProperty.getPropertyValues().getMacAddress());
		Ipsec ipsecUpdate = currentIpsecList.get(index);

		if (currentIpsecList == null || ipsecUpdate == null) {
			Ipsec newIpsec = new Ipsec();
			newIpsec.setMacAddress(ipsecWithProperty.getPropertyValues().getMacAddress());
			ipsecService.addOrEditIpsec(newIpsec);
			return new ResponseEntity<Ipsec>(HttpStatus.NOT_FOUND);
		}
		ipsecUpdate.setIpsecName(ipsecWithProperty.getIpsec().getIpsecName());
		ipsecUpdate.setRemoteLanIp(ipsecWithProperty.getIpsec().getRemoteLanIp());
		ipsecUpdate.setRemoteLanSubnet(ipsecWithProperty.getIpsec().getRemoteLanSubnet());
		ipsecUpdate.setRemoteWanIp(ipsecWithProperty.getIpsec().getRemoteWanIp());
		ipsecUpdate.setPreSharedKey(ipsecWithProperty.getIpsec().getPreSharedKey());
		ipsecUpdate.setUserId("Client:" + ipsecWithProperty.getIpsec().getUserId());
		ipsecUpdate.setUpdateDate(ipsecWithProperty.getIpsec().getUpdateDate());
		List<Wan> wanList = wanService.findAllByMacAddress(ipsecWithProperty.getPropertyValues().getMacAddress());
		Wan wanLocated = null;
		for (Wan wan : wanList) {
			if (wan.getId() == ipsecWithProperty.getIpsec().getWanId()) {
				wanLocated = wan;
			}
		}
		ipsecUpdate.setWanId(wanLocated.getId());

		try {
			ipsecService.addOrEditIpsec(ipsecUpdate);
			LOGGER.info("Updating IPsec as following: " + ipsecUpdate);
			Utility.rebootPython(request.getRemoteAddr(), ipsecWithProperty.getPropertyValues().getMacAddress());
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Ipsec>(ipsecUpdate, HttpStatus.OK);
	}

	@RequestMapping(value = "/ipsec/rest", method = RequestMethod.POST)
	public ResponseEntity<Void> createIpsec(@RequestBody IpsecWithProperty ipsecWithProperty, HttpServletRequest request) {
		LOGGER.info("Creating IPsec as following: " + ipsecWithProperty.getIpsec());
		ipsecWithProperty.getIpsec().setMacAddress(ipsecWithProperty.getPropertyValues().getMacAddress());
		ipsecWithProperty.getIpsec().setId(null);
		ipsecWithProperty.getIpsec().setUserId("Client:" + ipsecWithProperty.getIpsec().getUserId());
		
		try {
			ipsecService.addOrEditIpsec(ipsecWithProperty.getIpsec());
			LOGGER.info("Creating IPsec as following: " + ipsecWithProperty.getIpsec());
			Utility.rebootPython(request.getRemoteAddr(), ipsecWithProperty.getPropertyValues().getMacAddress());
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/ipsec/rest/{index}/{macAddress}", method = RequestMethod.DELETE)
	public ResponseEntity<Ipsec> deleteIpsec(@PathVariable("index") Integer index,
			@PathVariable("macAddress") String macAddress, HttpServletRequest request) {
		List<Ipsec> currentIpsecList = ipsecService.findAllByMacAddress(macAddress);
		Ipsec ipsec = currentIpsecList.get(index);
		if (currentIpsecList == null || ipsec == null) {
			LOGGER.info("Unable to delete IPsec with index: " + index + " not found");
			return new ResponseEntity<Ipsec>(HttpStatus.NOT_FOUND);
		}
		
		try {
			ipsecService.deleteIpsecById(ipsec.getId());
			LOGGER.info("Deleting IPsec as following: " + ipsec);
			Utility.rebootPython(request.getRemoteAddr(), macAddress);
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}

		return new ResponseEntity<Ipsec>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "lan/rest", method = RequestMethod.PUT)
	public ResponseEntity<Lan> updateLan(@RequestBody LanWithProperty lanWithProperty, HttpServletRequest request) {
		LOGGER.info("Updating LAN: " + lanWithProperty.getLan());

		Lan currentLan = lanService.findByMacAddress(lanWithProperty.getPropertyValues().getMacAddress()).get();

		if (currentLan == null) {
			Lan newLan = new Lan();
			newLan.setMacAddress(lanWithProperty.getPropertyValues().getMacAddress());
			lanService.addOrEditLan(newLan);
			return new ResponseEntity<Lan>(HttpStatus.NOT_FOUND);
		}
		currentLan.setIp(lanWithProperty.getLan().getIp());
		currentLan.setSubnetMask(lanWithProperty.getLan().getSubnetMask());
		currentLan.setPortMutiId(lanWithProperty.getLan().getPortMutiId());
		currentLan.setUserId("Client:" + lanWithProperty.getLan().getUserId());
		currentLan.setUpdateDate(lanWithProperty.getLan().getUpdateDate());
		try {
			lanService.addOrEditLan(currentLan);
			LOGGER.info("Updating LAN as following: " + currentLan);
			Utility.rebootPython(request.getRemoteAddr(), lanWithProperty.getPropertyValues().getMacAddress());
			
		} catch (Exception e) {
			LOGGER.error("Error: " + e);
		}
		return new ResponseEntity<Lan>(currentLan, HttpStatus.OK);
	}

}
