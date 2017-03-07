package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.UserInfo;
import org.yesee.hinet_vcpe_provider.model.dao.UserInfoDao;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	public List<UserInfo> findAll() {
		return userInfoDao.findAll();
	}

	public void save(UserInfo userInfo) {
		userInfoDao.save(userInfo);
	}

	public Optional<UserInfo> findByUserIdAndPassword(String userId, String password) {
		return userInfoDao.findByUserIdAndPassword(userId, password);
	}

	public Optional<UserInfo> findByUserId(String userId){
		return userInfoDao.findByUserId(userId);
	}
}
