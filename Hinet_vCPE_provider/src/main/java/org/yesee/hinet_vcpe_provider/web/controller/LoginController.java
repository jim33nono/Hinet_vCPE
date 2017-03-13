package org.yesee.hinet_vcpe_provider.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.python.core.PyInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yesee.hinet_vcpe_provider.model.bean.UserInfo;
import org.yesee.hinet_vcpe_provider.model.service.UserInfoService;
import org.yesee.hinet_vcpe_provider.util.Utility;
import org.yesee.hinet_vcpe_provider.util.Interpreter;

@Controller
@RequestMapping("login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private @Autowired UserInfoService userInfoService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		LOGGER.info("Provider login page lauched");
		return "login";
	}

	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String login(String userId, String password,Model model,HttpSession session) {
	
		LOGGER.info("User access login function with userId:{},pwd:{}", userId, password);
		LOGGER.info("Logging user info:{}", userInfoService.findAll());
		Optional<UserInfo> userInfo = userInfoService.findByUserIdAndPassword(userId, password);
		if (userInfo.isPresent()) {
			session.setAttribute(Utility.USER_ID_SESSION_KEY, userInfo.get().getUserId());
				return "redirect:/accountManagement/index";

		} else {
			model.addAttribute("Failed", true);
			return "login";
		}

	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(Utility.USER_ID_SESSION_KEY);
		LOGGER.info("Client logs out");
		return "redirect:/login/index";
	}
	
}
