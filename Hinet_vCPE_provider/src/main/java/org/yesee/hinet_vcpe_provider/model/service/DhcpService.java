package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.Dhcp;
import org.yesee.hinet_vcpe_provider.model.dao.DhcpDao;

import com.google.common.collect.Lists;

@Service
public class DhcpService {

	@Autowired
	private DhcpDao dhcpDao;
	
	public List<Dhcp> findAll(){
		return dhcpDao.findAll();
	}
	
	public Optional<Dhcp> findByMacAddress(String macAddress){
		for (Dhcp dhcp : dhcpDao.findAll()){
			if (macAddress.equals(dhcp.getMacAddress())){
				return Optional.of(dhcp);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Dhcp> findById(Integer id){
		return dhcpDao.findById(id);
	}
	
	public void deleteDhcpById(Integer id){
		dhcpDao.deleteDhcpById(id);
	}
	
	public void addOrEditDhcp(Dhcp dhcp){
		dhcpDao.addOrEditDhcp(dhcp);
	}
	
}
