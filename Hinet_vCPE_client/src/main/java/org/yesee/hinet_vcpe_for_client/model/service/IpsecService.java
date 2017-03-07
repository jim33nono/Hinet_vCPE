package org.yesee.hinet_vcpe_for_client.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_for_client.model.bean.Ipsec;
import org.yesee.hinet_vcpe_for_client.model.dao.IpsecDao;

@Service
public class IpsecService {

	@Autowired
	private IpsecDao ipsecDao;
	
	public List<Ipsec> findAll(){
		return ipsecDao.findAll();
	}
	
	public Optional<Ipsec> findById(Integer id){
		return ipsecDao.findById(id);
	}
	
	public Optional<Ipsec> findByIpsecName(String ipsecName){
		return ipsecDao.findByIpsecName(ipsecName);
	}
	
	public void deleteIpsecById(Integer id){
		ipsecDao.deleteIpsecById(id);
	}
	
	public void addOrEditIpsec(Ipsec ipsec){
		ipsecDao.addOrEditIpsec(ipsec);
	}
	
}
