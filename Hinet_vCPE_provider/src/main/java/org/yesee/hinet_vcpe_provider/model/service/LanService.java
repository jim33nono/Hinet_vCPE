package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.Lan;
import org.yesee.hinet_vcpe_provider.model.dao.LanDao;

@Service
public class LanService {

	
	@Autowired
	private LanDao lanDao;
	
	public List<Lan> findAll(){
		return lanDao.findAll();
	}
	
	public Optional<Lan> findByMacAddress(String macAddress) {
		return lanDao.findByMacAddress(macAddress);
	}
	
	public Optional<Lan> findById(Integer id){
		return lanDao.findById(id);
	}
	
	public void deleteLanById(Integer id){
		lanDao.deleteLanById(id);
	}
	
	public void addOrEditLan(Lan lan){
		lanDao.addOrEditLan(lan);
	}
}
