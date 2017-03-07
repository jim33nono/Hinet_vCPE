package org.yesee.hinet_vcpe_for_client.web.vo;

import java.util.List;
import java.util.stream.Collectors;

import org.yesee.hinet_vcpe_for_client.model.bean.UserInfo;

import com.google.common.collect.Lists;

public class UserInfoVo {

	private String userId;
	private String userName;
	private String pwd;
	private String pwd2;
	private String address;
	private String phone;
	
	public UserInfoVo(){
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public UserInfo asUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setUserId(userId);
		userInfo.setPhone(phone);
		userInfo.setPwd(pwd);
		userInfo.setAddress(address);
		return userInfo;
	}

	public static UserInfoVo valueOf(UserInfo userInfo) {
		UserInfoVo userInfoVo = new UserInfoVo();
		userInfoVo.setAddress(userInfo.getAddress());
		userInfoVo.setPhone(userInfo.getPhone());
		userInfoVo.setUserId(userInfo.getUserId());
		userInfoVo.setUserName(userInfo.getUserName());
		return userInfoVo;
	}

	
}
