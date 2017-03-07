package org.yesee.hinet_vcpe_for_client.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yesee.hinet_vcpe_for_client.model.bean.Dhcp;
import org.yesee.hinet_vcpe_for_client.model.bean.Ipsec;
import org.yesee.hinet_vcpe_for_client.model.bean.Lan;
import org.yesee.hinet_vcpe_for_client.model.bean.Port;
import org.yesee.hinet_vcpe_for_client.model.bean.Wan;
import org.yesee.hinet_vcpe_for_client.model.bean.Wan.DefaultSetting;
import org.yesee.hinet_vcpe_for_client.model.service.DhcpService;
import org.yesee.hinet_vcpe_for_client.model.service.IpsecService;
import org.yesee.hinet_vcpe_for_client.model.service.LanService;
import org.yesee.hinet_vcpe_for_client.model.service.PortService;
import org.yesee.hinet_vcpe_for_client.model.service.WanService;
import org.yesee.hinet_vcpe_for_client.rest.DhcpRest;
import org.yesee.hinet_vcpe_for_client.rest.DhcpWithProperty;
import org.yesee.hinet_vcpe_for_client.rest.IpsecRest;
import org.yesee.hinet_vcpe_for_client.rest.IpsecWithProperty;
import org.yesee.hinet_vcpe_for_client.rest.LanRest;
import org.yesee.hinet_vcpe_for_client.rest.LanWithProperty;
import org.yesee.hinet_vcpe_for_client.rest.PortRest;
import org.yesee.hinet_vcpe_for_client.rest.PortWithProperty;
import org.yesee.hinet_vcpe_for_client.rest.WanRest;
import org.yesee.hinet_vcpe_for_client.rest.WanWithProperty;
import org.yesee.hinet_vcpe_for_client.util.Utility;
import org.yesee.hinet_vcpe_for_client.web.vo.DhcpInput;
import org.yesee.hinet_vcpe_for_client.web.vo.IpsecVo;
import org.yesee.hinet_vcpe_for_client.web.vo.LanInput;
import org.yesee.hinet_vcpe_for_client.web.vo.SortedPortIdVo;
import org.yesee.hinet_vcpe_for_client.web.vo.WanVo;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("internetSetting")
public class InternetSettingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternetSettingController.class);

	private @Autowired PortService portService;
	private @Autowired DhcpService dhcpService;
	private @Autowired WanService wanService;
	private @Autowired IpsecService ipsecService;
	private @Autowired LanService lanService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpSession session, Model model) throws IOException {
		
		Dhcp dhcpInfo = dhcpService.findById(1).get();
		Lan lanInfo = lanService.findById(1).get();
		model.addAttribute("dhcpInfo", dhcpInfo);
		model.addAttribute("lanInfo", lanInfo);

		String str = lanInfo.getPortMutiId();
		List<String> portIdListForLan = Arrays.asList(str.split(","));

		List<Integer> portIdIntegerList = new ArrayList<Integer>();
		for (int i = 0; i < portIdListForLan.size(); i++) {
			portIdIntegerList.add(Integer.parseInt(portIdListForLan.get(i)));
		}

		for (int i = portIdIntegerList.size(); i < 7; i++) {
			portIdIntegerList.add(0);
		}
		List<Integer> sortedportIdList = new ArrayList<Integer>();

		for (int i = 1, n = 0; i < 7; i++) {
			if (portIdIntegerList.get(n) > i) {
				sortedportIdList.add(0);

			} else {
				sortedportIdList.add(portIdIntegerList.get(n));
				n++;
			}
		}

		LOGGER.info("Port id integer sorted list for LAN as following: " + sortedportIdList);

		List<Port> portInfoList = portService.findAll();
		List<String> portNameList = Lists.newArrayList();
		for (int i = 0; i < portInfoList.size(); i++) {
			portNameList.add(portInfoList.get(i).getPortName());

		}

		SortedPortIdVo sortedPortIdVo1 = new SortedPortIdVo();
		sortedPortIdVo1.setIndex(1);
		sortedPortIdVo1.setPortIdValue(sortedportIdList.get(0));
		sortedPortIdVo1.setPortName(portInfoList.get(0).getPortName());
		SortedPortIdVo sortedPortIdVo2 = new SortedPortIdVo();
		sortedPortIdVo2.setIndex(2);
		sortedPortIdVo2.setPortIdValue(sortedportIdList.get(1));
		sortedPortIdVo2.setPortName(portInfoList.get(1).getPortName());
		SortedPortIdVo sortedPortIdVo3 = new SortedPortIdVo();
		sortedPortIdVo3.setIndex(3);
		sortedPortIdVo3.setPortIdValue(sortedportIdList.get(2));
		sortedPortIdVo3.setPortName(portInfoList.get(2).getPortName());
		SortedPortIdVo sortedPortIdVo4 = new SortedPortIdVo();
		sortedPortIdVo4.setIndex(4);
		sortedPortIdVo4.setPortIdValue(sortedportIdList.get(3));
		sortedPortIdVo4.setPortName(portInfoList.get(3).getPortName());
		SortedPortIdVo sortedPortIdVo5 = new SortedPortIdVo();
		sortedPortIdVo5.setIndex(5);
		sortedPortIdVo5.setPortIdValue(sortedportIdList.get(4));
		sortedPortIdVo5.setPortName(portInfoList.get(4).getPortName());
		SortedPortIdVo sortedPortIdVo6 = new SortedPortIdVo();
		sortedPortIdVo6.setIndex(6);
		sortedPortIdVo6.setPortIdValue(sortedportIdList.get(5));
		sortedPortIdVo6.setPortName(portInfoList.get(5).getPortName());

		List<SortedPortIdVo> sortedPortIdVoList = Lists.newArrayList();
		sortedPortIdVoList.add(sortedPortIdVo1);
		sortedPortIdVoList.add(sortedPortIdVo2);
		sortedPortIdVoList.add(sortedPortIdVo3);
		sortedPortIdVoList.add(sortedPortIdVo4);
		sortedPortIdVoList.add(sortedPortIdVo5);
		sortedPortIdVoList.add(sortedPortIdVo6);

		model.addAttribute("sortedPortIdVoList", sortedPortIdVoList);

		return "internetSetting";
	}

	@RequestMapping(value = "submitInfo", method = RequestMethod.POST)
	@ResponseBody
	public boolean info(HttpSession session, DhcpInput dhcpInput, LanInput lanInput,
			@RequestParam(value = "checkboxPortIdArray[]") Integer[] checkboxPortIdArray) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		boolean runOrStop = false;

		String portIdMuti = Arrays.toString(checkboxPortIdArray);
		portIdMuti = portIdMuti.replace("[", "").replace("]", "").replace(" ", "");
		LOGGER.info("Checkbox PortId Array for LAN as following: " + portIdMuti);
		try {

			Dhcp dhcpInfo = dhcpService.findById(1).get();
			dhcpInfo.setUpdateDate(new Date());
			dhcpInfo.setUserId(userId);
			dhcpInfo.setDefaultGateway(dhcpInput.getDefaultGatewayForDhcp());
			dhcpInfo.setDns1(dhcpInput.getDhcpDns1());
			dhcpInfo.setStartIp(dhcpInput.getStartIP());
			dhcpInfo.setEndIp(dhcpInput.getEndIP());
			dhcpService.addOrEditDhcp(dhcpInfo);
			LOGGER.info("Updating DHCP as following: " + dhcpInfo);
			Utility.getPropValues();
			DhcpWithProperty dhcpWithProperty = new DhcpWithProperty(dhcpInfo, Utility.properties);
			DhcpRest.updateDhcp(dhcpWithProperty);
			
			Lan lanInfo = lanService.findById(1).get();
			lanInfo.setUpdateDate(new Date());
			lanInfo.setUserId(userId);
			lanInfo.setIp(lanInput.getLanIp());
			lanInfo.setSubnetMask(lanInput.getLanSubnetMask());
			lanInfo.setPortMutiId(portIdMuti);
			lanService.addOrEditLan(lanInfo);
			LOGGER.info("Updating LAN as following: " + lanInfo);
			LanWithProperty lanWithProperty = new LanWithProperty(lanInfo, Utility.properties);
			LanRest.updateLan(lanWithProperty);
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "getAllPortName")
	@ResponseBody
	public List<Port> getAllPortName() {

		List<Port> allPort = portService.findAll();
		List<Port> availablePort = Lists.newArrayList();
		List<Wan> wanListInfo = wanService.findAll();
		boolean find = false;
		for (Port port : allPort) {
			for (Wan wan : wanListInfo) {
				if (wan.getPortId() != null) {
					if (wan.getPortId().equals(port.getId())) {
						find = true;
					}
				}
			}
			if (!find) {
				availablePort.add(port);
			}
			find = false;

		}

		LOGGER.info("Available ports as following:" + availablePort);

		return availablePort;

	}

	@RequestMapping(value = "getAllPortNameByWanEdit", method = RequestMethod.POST)
	@ResponseBody
	public List<Port> getAllPortNameByWanEdit(String portName) {

		List<Port> allPort = portService.findAll();
		List<Port> availablePort = Lists.newArrayList();
		LOGGER.info("Port name as following: " + portName);
		if (portName != null) {
			availablePort.add(portService.findByPortName(portName).get());
		}
		List<Wan> wanListInfo = wanService.findAll();
		boolean find = false;
		for (Port port : allPort) {
			for (Wan wan : wanListInfo) {
				if (wan.getPortId() != null) {
					if (wan.getPortId().equals(port.getId())) {
						find = true;
					}
				}
			}
			if (!find) {
				availablePort.add(port);
			}
			find = false;

		}

		LOGGER.info("Available ports as following:" + availablePort);

		return availablePort;

	}

	// Port
	@RequestMapping(value = "showPortList", method = RequestMethod.GET)
	@ResponseBody
	public List<Port> showPortList() {
		List<Port> portList = portService.findAll();
		return portList;

	}

	@RequestMapping(value = "editPortName", method = RequestMethod.POST)
	@ResponseBody
	public boolean editPortName(Port port, HttpSession session) {
		boolean runOrStop = false;

		if (portService.findByPortName(port.getPortName()).isPresent()) {
			if (!portService.findById(port.getId()).get().getPortName().equals(port.getPortName())) {
				return runOrStop;
			}
		}
		try {
			Port portInfo = portService.findById(port.getId()).get();
			portInfo.setPortName(port.getPortName());
			portService.addOrEditPort(portInfo);
			LOGGER.info("Updating port as following: " + portInfo);
			Utility.getPropValues();
			LOGGER.info(Utility.properties.getAccount() + Utility.properties.getPassword() + Utility.properties.getMacAddress() + Utility.properties.getUrl());
			PortWithProperty portWithProperty = new PortWithProperty(portInfo, Utility.properties);
			PortRest.updatePort(portWithProperty);
			
			runOrStop = true;

		} catch (Exception e) {
			LOGGER.error("Error: ", e);
			return runOrStop;
		}
		return runOrStop;

	}

	// WAN
	@RequestMapping(value = "showWanList", method = RequestMethod.GET)
	@ResponseBody
	public List<WanVo> showWanList(HttpSession session) {

		List<Wan> wanList = wanService.findAll();
		List<WanVo> wanVoList = Lists.newArrayList();
		for (Wan wan : wanList) {
			WanVo wanVo = new WanVo();
			wanVo.setId(wan.getId());
			wanVo.setDefaultSetting(wan.getDefaultSetting());
			wanVo.setWanName(wan.getWanName());
			if (wan.getPortId() != null) {
				wanVo.setPortName(portService.findById(wan.getPortId()).get().getPortName());
			}
			wanVo.setWanIp(wan.getWanIp());
			wanVo.setSubnet(wan.getSubnet());
			wanVoList.add(wanVo);
		}

		return wanVoList;

	}

	@RequestMapping(value = "createNewWan", method = RequestMethod.POST)
	@ResponseBody
	public boolean createNewWan(Wan wan, HttpSession session) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		boolean runOrStop = false;

		if (wanService.findByWanName(wan.getWanName()).isPresent()
				|| ipsecService.findByIpsecName(wan.getWanName()).isPresent()) {

			return runOrStop;
		}

		try {
			wan.setUpdateDate(new Date());
			wan.setUserId(userId);
			wan.setDefaultSetting(DefaultSetting.No);
			wanService.addOrEditWan(wan);
			LOGGER.info("Creating WAN as following: " + wan);
			Utility.getPropValues();
			WanWithProperty wanWithProperty = new WanWithProperty(wan, Utility.properties);
			WanRest.createWan(wanWithProperty);
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "editWan", method = RequestMethod.POST)
	@ResponseBody
	public boolean editWan(Wan wan, HttpSession session) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		boolean runOrStop = false;

		if (wanService.findByWanName(wan.getWanName()).isPresent()
				|| ipsecService.findByIpsecName(wan.getWanName()).isPresent()) {
			if (!wan.getWanName().equals(wanService.findById(wan.getId()).get().getWanName())) {
				return runOrStop;
			}
		}

		try {
			List<Wan> wanList = wanService.findAll();
			Wan wanUpdateInfo = wanService.findById(wan.getId()).get();
			Integer index = wanList.indexOf(wanUpdateInfo);
			LOGGER.info("WAN index for Restful API: " + index);
			
			wanUpdateInfo.setUserId(userId);
			wanUpdateInfo.setUpdateDate(new Date());
			wanUpdateInfo.setWanName(wan.getWanName());
			wanUpdateInfo.setPortId(wan.getPortId());
			wanUpdateInfo.setWanIp(wan.getWanIp());
			wanUpdateInfo.setSubnet(wan.getSubnet());
			LOGGER.info("WAN update as following: " + wan.getDefaultSetting());
			if (wan.getDefaultSetting().equals(DefaultSetting.Yes)) {
				for (Wan wanInfo : wanService.findAll()) {
					wanInfo.setDefaultSetting(DefaultSetting.No);
					wanService.addOrEditWan(wanInfo);
				}
				wanUpdateInfo.setDefaultSetting(DefaultSetting.Yes);

			} else {
				wanUpdateInfo.setDefaultSetting(DefaultSetting.No);
			}
			
			wanService.addOrEditWan(wanUpdateInfo);
			LOGGER.info("Updating WAN as following: " + wanUpdateInfo);
			Utility.getPropValues();
			WanWithProperty wanWithProperty = new WanWithProperty(wanUpdateInfo, Utility.properties);
			WanRest.updateWan(index, wanWithProperty);
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "deleteWan", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteWan(Wan wan) {
		boolean runOrStop = false;
		try {
			List<Wan> wanList = wanService.findAll();
			Wan wanDeleteInfo = wanService.findById(wan.getId()).get();
			Integer index = wanList.indexOf(wanDeleteInfo);
			wanService.deleteWanById(wan);
			LOGGER.info("Deleting WAN as following: " + wan);
			Utility.getPropValues();
			WanRest.deleteWan(index, Utility.properties.getMacAddress());
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "getAllWanNames")
	@ResponseBody
	public List<Wan> getAllWanNames() {
		return wanService.findAllWithDefaultWanFirst();
	}

	public static IpsecVo valueOf(Ipsec ipsec){
		IpsecVo ipsecVo = new IpsecVo();
		ipsecVo.setId(ipsec.getId());
		ipsecVo.setIpsecName(ipsec.getIpsecName());
		ipsecVo.setRemoteLanIp(ipsec.getRemoteLanIp());
		ipsecVo.setRemoteLanSubnet(ipsec.getRemoteLanSubnet());
		ipsecVo.setRemoteWanIp(ipsec.getRemoteWanIp());
		ipsecVo.setPreSharedKey(ipsec.getPreSharedKey());
		ipsecVo.setWanId(ipsec.getWanId());
		return ipsecVo;

	}
	
	// IPsec
	@RequestMapping(value = "showIpsecList", method = RequestMethod.GET)
	@ResponseBody
	public List<IpsecVo> showIpsecList() {
		List<IpsecVo> ipsecList = Lists.newArrayList();
		for (Ipsec ipsec : ipsecService.findAll()){
			IpsecVo ipsecVo = valueOf(ipsec);
			ipsecVo.setWanName(wanService.findById(ipsec.getWanId()).get().getWanName());
			ipsecList.add(ipsecVo);
			
		}
		
		return ipsecList;

	}

	@RequestMapping(value = "createNewIpsec", method = RequestMethod.POST)
	@ResponseBody
	public boolean createNewIpsec(Ipsec ipsec, HttpSession session) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		boolean runOrStop = false;

		if (wanService.findByWanName(ipsec.getIpsecName()).isPresent()
				|| ipsecService.findByIpsecName(ipsec.getIpsecName()).isPresent()) {
			return runOrStop;
		}

		try {
			ipsec.setUpdateDate(new Date());
			ipsec.setUserId(userId);
			ipsecService.addOrEditIpsec(ipsec);
			LOGGER.info("Creating IPsec as following: " + ipsec);
			Utility.getPropValues();
			IpsecWithProperty ipsecWithProperty = new IpsecWithProperty(ipsec, Utility.properties);
			IpsecRest.createIpsec(ipsecWithProperty);
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "editIpsec", method = RequestMethod.POST)
	@ResponseBody
	public boolean editIpsec(Ipsec ipsec, HttpSession session) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		boolean runOrStop = false;

		if (wanService.findByWanName(ipsec.getIpsecName()).isPresent()
				|| ipsecService.findByIpsecName(ipsec.getIpsecName()).isPresent()) {
			if (!ipsec.getIpsecName().equals(ipsecService.findById(ipsec.getId()).get().getIpsecName())) {
				return runOrStop;
			}
		}

		try {
			List<Ipsec> ipsecList = ipsecService.findAll();
			Ipsec ipsecUpdateInfo = ipsecService.findById(ipsec.getId()).get();
			Integer index = ipsecList.indexOf(ipsecUpdateInfo);
			LOGGER.info("IPsec index for Restful API: " + index);
			
			ipsecUpdateInfo.setUpdateDate(new Date());
			ipsecUpdateInfo.setUserId(userId);
			ipsecUpdateInfo.setIpsecName(ipsec.getIpsecName());
			ipsecUpdateInfo.setRemoteLanIp(ipsec.getRemoteLanIp());
			ipsecUpdateInfo.setRemoteLanSubnet(ipsec.getRemoteLanSubnet());
			ipsecUpdateInfo.setRemoteWanIp(ipsec.getRemoteWanIp());
			ipsecUpdateInfo.setPreSharedKey(ipsec.getPreSharedKey());
			ipsecUpdateInfo.setWanId(ipsec.getWanId());
			ipsecService.addOrEditIpsec(ipsecUpdateInfo);
			LOGGER.info("Updating IPsec as following: " + ipsecUpdateInfo);
			Utility.getPropValues();
			IpsecWithProperty ipsecWithProperty = new IpsecWithProperty(ipsecUpdateInfo, Utility.properties);
			IpsecRest.updateIpsec(index, ipsecWithProperty);
			
			runOrStop = true;
			return runOrStop;

		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "deleteIpsec", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteIpsec(Integer id) {
		boolean runOrStop = false;
		try {
			
			List<Ipsec> ipsecList = ipsecService.findAll();
			Ipsec ipsecDeleteInfo = ipsecService.findById(id).get();
			Integer index = ipsecList.indexOf(ipsecDeleteInfo);
			
			ipsecService.deleteIpsecById(id);
			LOGGER.info("Deleting IPsec as following: " + ipsecDeleteInfo);
			
			Utility.getPropValues();
			IpsecRest.deleteIpsec(index, Utility.properties.getMacAddress());
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

}
