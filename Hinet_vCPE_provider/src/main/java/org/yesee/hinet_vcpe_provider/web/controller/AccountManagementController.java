package org.yesee.hinet_vcpe_provider.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yesee.hinet_vcpe_provider.model.bean.AccountManagement;
import org.yesee.hinet_vcpe_provider.model.service.AccountManagementService;
import org.yesee.hinet_vcpe_provider.util.Utility;

@RequestMapping("accountManagement")
@Controller
public class AccountManagementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagementController.class);

	private @Autowired AccountManagementService accountManagementService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpSession session, ModelMap model) {
		session.removeAttribute("account");
		session.removeAttribute(Utility.MAC_ADDRESS_SESSION_KEY);
		LOGGER.info("remove Mac address: " + (String)session.getAttribute(Utility.MAC_ADDRESS_SESSION_KEY));
		return "accountManagement";
		
	}

	@RequestMapping(value = "showAccountManagementList", method = RequestMethod.GET)
	@ResponseBody
	public List<AccountManagement> showAccountManagementList() {
		List<AccountManagement> accountManagementList = accountManagementService.findAll();
		return accountManagementList;
	}

	@RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteAccount(Integer id) {
		boolean runOrStop = false;
		AccountManagement accountInfo = new AccountManagement(id);
		try {
			accountManagementService.deleteAccountManagementById(accountInfo);
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}
	}

	@RequestMapping(value = "createAccount", method = RequestMethod.POST)
	@ResponseBody
	public boolean createAccount(AccountManagement accountManagement, HttpSession session) {
		boolean runOrStop = false;
		
		if (accountManagementService.chechAccountRepeat(accountManagement)) {
			return runOrStop;
		}
		accountManagement.setUserId((String) (session.getAttribute(Utility.USER_ID_SESSION_KEY)));
		accountManagement.setUpdateDate(new Date());
		try {
			accountManagementService.addOrEditAccountManagement(accountManagement);
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}

	@RequestMapping(value = "editAccount", method = RequestMethod.POST)
	@ResponseBody
	public boolean editAccount(AccountManagement accountManagement, HttpSession session) {
		boolean runOrStop = false;
		AccountManagement accountInfoUpdate = accountManagementService.findById(accountManagement.getId()).get();
		if (accountManagementService.chechAccountRepeat(accountManagement)) {
			if (!accountInfoUpdate.getAccount().equals(accountManagement.getAccount())) {
				return runOrStop;
			}
		}
		accountInfoUpdate.setUserId((String) (session.getAttribute(Utility.USER_ID_SESSION_KEY)));
		accountInfoUpdate.setUpdateDate(new Date());
		accountInfoUpdate.setAccount(accountManagement.getAccount());
		accountInfoUpdate.setPassword(accountManagement.getPassword());
		accountInfoUpdate.setMacAddress(accountManagement.getMacAddress());
		try {
			accountManagementService.addOrEditAccountManagement(accountInfoUpdate);
			runOrStop = true;
			return runOrStop;
		} catch (Exception e) {
			LOGGER.error("Error: ",e);
			return runOrStop;
		}

	}
	
	@RequestMapping(value = "guideProcess", method = RequestMethod.POST)
	@ResponseBody
	public boolean guideProcess(String account, String macAddress, HttpSession session){
		boolean runOrStop = false;
		session.setAttribute("account",account);
		session.setAttribute(Utility.MAC_ADDRESS_SESSION_KEY, macAddress);
		runOrStop = true;
		return runOrStop;
		
	}
	
	@RequestMapping(value = "guideToGatewaySwitchIndex", method = RequestMethod.GET)
	public String guideToGatewaySwitchIndex() {
		return "redirect:/gatewaySwitch/index";
	}
	
	@RequestMapping(value = "guideToInternetSettingIndex", method = RequestMethod.GET)
	public String guideToInternetSettingIndex() {
		return "redirect:/internetSetting/index";
	}
	

}
