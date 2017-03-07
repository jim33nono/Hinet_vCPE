package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.AccountManagement;
import org.yesee.hinet_vcpe_provider.model.dao.AccountManagementDao;

@Service
public class AccountManagementService {

	@Autowired
	private AccountManagementDao accountManagementDao;

	public List<AccountManagement> findAll() {
		return accountManagementDao.findAll();
	}

	public Optional<AccountManagement> findById(Integer id) {
		return accountManagementDao.findById(id);
	}
	
	public Optional<AccountManagement> findByMacAddress(String macAddress){
		for (AccountManagement accountManagement : accountManagementDao.findAll()){
			if (accountManagement.getMacAddress().equals(macAddress)){
				return Optional.of(accountManagement);
			}
		}
		return Optional.empty();
	}
	
	public Optional<AccountManagement> locateUser(String account, String password, String macAddress) {
		return accountManagementDao.locateUser(account, password, macAddress);
	}

	public void deleteAccountManagementById(AccountManagement accountManagement) {
		accountManagementDao.deleteAccountManagementById(accountManagement);
	}

	public void addOrEditAccountManagement(AccountManagement accountManagement) {
		accountManagementDao.addOrEditAccountManagement(accountManagement);
	}

	public boolean chechAccountRepeat(AccountManagement accountManagement) {
		boolean isRepeat = false;
		List<AccountManagement> accountInfoList = accountManagementDao.findAll();
		for (AccountManagement accountInfo : accountInfoList) {
			if (accountInfo.getAccount().equals(accountManagement.getAccount())) {
				isRepeat = true;
				return isRepeat;
			}
		}
		return isRepeat;

	}

}
