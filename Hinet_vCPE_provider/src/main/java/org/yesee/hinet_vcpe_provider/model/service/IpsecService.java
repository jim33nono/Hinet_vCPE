package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.Ipsec;
import org.yesee.hinet_vcpe_provider.model.dao.IpsecDao;

import com.google.common.collect.Lists;

@Service
public class IpsecService {

	@Autowired
	private IpsecDao ipsecDao;
	
	public List<Ipsec> findAll(){
		return ipsecDao.findAll();
	}
	
	public List<Ipsec> findAllByMacAddress(String macAddress){
		List<Ipsec> ipsecList = Lists.newArrayList();
		for (Ipsec ipsec : ipsecDao.findAll()){
			if (ipsec.getMacAddress().equals(macAddress)){
				ipsecList.add(ipsec);
			}
		}
		return ipsecList;
	}
	
	public Optional<Ipsec> findById(Integer id){
		return ipsecDao.findById(id);
	}
	
	public Optional<Ipsec> findByIpsecName(String ipsecName, String macAddress){
		return ipsecDao.findByIpsecName(ipsecName, macAddress);
	}
	
	public void deleteIpsecById(Integer id){
		ipsecDao.deleteIpsecById(id);
	}
	
	public void addOrEditIpsec(Ipsec ipsec){
		ipsecDao.addOrEditIpsec(ipsec);
	}
	
}
