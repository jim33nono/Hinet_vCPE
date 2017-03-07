package org.yesee.hinet_vcpe_for_client.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yesee.hinet_vcpe_for_client.model.bean.Port;
import org.yesee.hinet_vcpe_for_client.model.bean.PortStatus;
import org.yesee.hinet_vcpe_for_client.model.service.PortService;
import org.yesee.hinet_vcpe_for_client.model.service.PortStatusService;
import org.yesee.hinet_vcpe_for_client.web.vo.PortStatusVo;

import com.google.common.collect.Lists;

@RequestMapping("dashboard")
@Controller
public class DashboardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

	private @Autowired PortService portService;
	private @Autowired PortStatusService portStatusService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "dashboard";
	}
	
	public static PortStatusVo valueOf(PortStatus portStatus) {
		PortStatusVo portStatusVo = new PortStatusVo();
		portStatusVo.setId(portStatus.getId());
		portStatusVo.setOnlineStatus(portStatus.getOnlineStatus());
		portStatusVo.setUploadRate(portStatus.getUploadRate());
		portStatusVo.setDownloadRate(portStatus.getDownloadRate());
		portStatusVo.setPortId(portStatus.getPortId());
		return portStatusVo;
		
	}
	
	@RequestMapping(value = "showStatus")
	@ResponseBody
	public List<PortStatusVo> showStatus(){
		List<PortStatusVo> portVoList = Lists.newArrayList();
		for (PortStatus portStatus : portStatusService.findAll()) {
			PortStatusVo portStatusVo = valueOf(portStatus);
			portStatusVo.setPortName((portService.findById(portStatus.getPortId()).get().getPortName()));
			portVoList.add(portStatusVo);
		}
		
		return portVoList; 
		
	}
	
	
	
}
