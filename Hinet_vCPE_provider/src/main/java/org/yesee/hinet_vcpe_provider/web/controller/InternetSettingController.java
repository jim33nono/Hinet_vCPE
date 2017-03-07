package org.yesee.hinet_vcpe_provider.web.controller;

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
import org.yesee.hinet_vcpe_provider.util.Utility;
import org.yesee.hinet_vcpe_provider.web.vo.DhcpInput;
import org.yesee.hinet_vcpe_provider.web.vo.IpsecVo;
import org.yesee.hinet_vcpe_provider.web.vo.LanInput;
import org.yesee.hinet_vcpe_provider.web.vo.SortedPortIdVo;
import org.yesee.hinet_vcpe_provider.web.vo.WanVo;

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
	public String index(HttpSession session, Model model) {
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		if (dhcpService.findByMacAddress(macAddress).isPresent()) {
			Dhcp dhcpInfo = dhcpService.findByMacAddress(macAddress).get();
			model.addAttribute("dhcpInfo", dhcpInfo);
		}
		
		if (lanService.findByMacAddress(macAddress).isPresent()) {
			Lan lanInfo = lanService.findByMacAddress(macAddress).get();
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

			List<Port> portInfoList = portService.findAllByMacAddress(macAddress);
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
		}

		return "internetSetting";
	}

	@RequestMapping(value = "submitInfo", method = RequestMethod.POST)
	@ResponseBody
	public boolean info(HttpSession session, DhcpInput dhcpInput, LanInput lanInput,
			@RequestParam(value = "checkboxPortIdArray[]") Integer[] checkboxPortIdArray) {
		String userId = (String) session.getAttribute(Utility.USER_ID_SESSION_KEY);
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		String portIdMuti = Arrays.toString(checkboxPortIdArray);
		portIdMuti = portIdMuti.replace("[", "").replace("]", "").replace(" ", "");
		LOGGER.info("Checkbox PortId Array for LAN as following: " + portIdMuti);
		boolean runOrStop = false;
		
		try {

			Dhcp dhcpInfo = dhcpService.findByMacAddress(macAddress).get();
			dhcpInfo.setUpdateDate(new Date());
			dhcpInfo.setUserId(userId);
			dhcpInfo.setDefaultGateway(dhcpInput.getDefaultGatewayForDhcp());
			dhcpInfo.setDns1(dhcpInput.getDhcpDns1());
			dhcpInfo.setStartIp(dhcpInput.getStartIP());
			dhcpInfo.setEndIp(dhcpInput.getEndIP());
			dhcpService.addOrEditDhcp(dhcpInfo);
			LOGGER.info("Updating DHCP as following: " + dhcpInfo);
			
			Lan lanInfo = lanService.findByMacAddress(macAddress).get();
			lanInfo.setUpdateDate(new Date());
			lanInfo.setUserId(userId);
			lanInfo.setIp(lanInput.getLanIp());
			lanInfo.setSubnetMask(lanInput.getLanSubnetMask());
			
			lanInfo.setPortMutiId(portIdMuti);
			lanService.addOrEditLan(lanInfo);
			LOGGER.info("Updating LAN as following: " + lanInfo);
			Utility.rebootPython();

			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "getAllPortName")
	@ResponseBody
	public List<Port> getAllPortName(HttpSession session) {
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		
		List<Port> allPort = portService.findAllByMacAddress(macAddress);
		LOGGER.info("All port name by this Mac address:{}" + allPort);
		List<Port> availablePort = Lists.newArrayList();
		List<Wan> wanListInfo = wanService.findAllByMacAddress(macAddress);
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
	public List<Port> getAllPortNameByWanEdit(String portName, HttpSession session) {
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);

		List<Port> allPort = portService.findAllByMacAddress(macAddress);
		List<Port> availablePort = Lists.newArrayList();
		LOGGER.info("Port name as following: " + portName);
		if (portName != null) {
			availablePort.add(portService.findByPortName(portName, macAddress).get());
		}
		List<Wan> wanListInfo = wanService.findAllByMacAddress(macAddress);
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
	public List<Port> showPortList(HttpSession session) {
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		List<Port> portList = portService.findAllByMacAddress(macAddress);
		return portList;

	}

	@RequestMapping(value = "editPortName", method = RequestMethod.POST)
	@ResponseBody
	public boolean editPortName(Port port, HttpSession session) {
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		boolean runOrStop = false;

		
		if (portService.findByPortName(port.getPortName(), macAddress).isPresent()) {
			if (!portService.findById(port.getId()).get().getPortName().equals(port.getPortName())) {
				return runOrStop;
			}
		}

		try {
			Port portInfo = portService.findById(port.getId()).get();
			portInfo.setPortName(port.getPortName());
			portService.addOrEditPort(portInfo);
			LOGGER.info("Updating port as following: " + portInfo);
			Utility.rebootPython();
			
			runOrStop = true;

		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}
		return runOrStop;

	}

	// WAN
	@RequestMapping(value = "showWanList", method = RequestMethod.GET)
	@ResponseBody
	public List<WanVo> showWanList(HttpSession session) {
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		List<Wan> wanList = wanService.findAllByMacAddress(macAddress);
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
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		boolean runOrStop = false;
		if (wanService.findByWanName(wan.getWanName(), macAddress).isPresent()
				|| ipsecService.findByIpsecName(wan.getWanName(), macAddress).isPresent()) {
			
			return runOrStop;
		}

		try {
			wan.setUpdateDate(new Date());
			wan.setUserId(userId);
			wan.setMacAddress(macAddress);
			wan.setDefaultSetting(DefaultSetting.No);
			wanService.addOrEditWan(wan);
			LOGGER.info("Creating WAN as following: " + wan);
			Utility.rebootPython();
			
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
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		boolean runOrStop = false;
		
		if (wanService.findByWanName(wan.getWanName(), macAddress).isPresent()
				|| ipsecService.findByIpsecName(wan.getWanName(), macAddress).isPresent()) {
			if(!wan.getWanName().equals(wanService.findById(wan.getId()).get().getWanName())) {
				return runOrStop;
			}
		}

		try {
			Wan wanUpdateInfo = wanService.findById(wan.getId()).get();
			wanUpdateInfo.setUserId(userId);
			wanUpdateInfo.setUpdateDate(new Date());
			wan.setMacAddress(macAddress);
			wanUpdateInfo.setWanName(wan.getWanName());
			wanUpdateInfo.setPortId(wan.getPortId());
			wanUpdateInfo.setWanIp(wan.getWanIp());
			wanUpdateInfo.setSubnet(wan.getSubnet());
			LOGGER.info("WAN update as following: " + wan.getDefaultSetting());
			if (wan.getDefaultSetting().equals(DefaultSetting.Yes)) {
				for (Wan wanInfo : wanService.findAllByMacAddress(macAddress)) {
					wanInfo.setDefaultSetting(DefaultSetting.No);
					wanService.addOrEditWan(wanInfo);
				}
				wanUpdateInfo.setDefaultSetting(DefaultSetting.Yes);

			}else{
				wanUpdateInfo.setDefaultSetting(DefaultSetting.No);
			}

			wanService.addOrEditWan(wanUpdateInfo);
			LOGGER.info("Updating WAN as following: " + wanUpdateInfo);
			Utility.rebootPython();
			
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
			wanService.deleteWanById(wan);
			LOGGER.info("Deleting WAN as following: " + wan);
			Utility.rebootPython();
			
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}
	
	@RequestMapping(value = "getAllWanNames")
	@ResponseBody
	public List<Wan> getAllWanNames(HttpSession session){
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		return wanService.findAllOrderByDefault(macAddress);
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
	public List<IpsecVo> showIpsecList(HttpSession session) {
		String macAddress = (String) session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		List<IpsecVo> ipsecList = Lists.newArrayList();
		for (Ipsec ipsec : ipsecService.findAllByMacAddress(macAddress)){
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
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		boolean runOrStop = false;
		
		if (wanService.findByWanName(ipsec.getIpsecName(), macAddress).isPresent()
				|| ipsecService.findByIpsecName(ipsec.getIpsecName(), macAddress).isPresent()) {
			
			return runOrStop;
		}
		
		try {
			ipsec.setUpdateDate(new Date());
			ipsec.setUserId(userId);
			ipsec.setMacAddress(macAddress);
			ipsecService.addOrEditIpsec(ipsec);
			LOGGER.info("Creating IPsec as following: " + ipsec);
			Utility.rebootPython();
			
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
		String macAddress = (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		boolean runOrStop = false;
		
		if (wanService.findByWanName(ipsec.getIpsecName(), macAddress).isPresent()
				|| ipsecService.findByIpsecName(ipsec.getIpsecName(), macAddress).isPresent()) {
			if (!ipsec.getIpsecName().equals(ipsecService.findById(ipsec.getId()).get().getIpsecName())){
				return runOrStop;
			}
		}
		
		try {
			Ipsec ipsecUpdateInfo = ipsecService.findById(ipsec.getId()).get();
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
			Utility.rebootPython();
			
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
			ipsecService.deleteIpsecById(id);
			LOGGER.info("Deleting IPsec as following: " + ipsecService.findById(id));
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
