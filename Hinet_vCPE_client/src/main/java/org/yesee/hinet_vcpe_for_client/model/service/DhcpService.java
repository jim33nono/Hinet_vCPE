package org.yesee.hinet_vcpe_for_client.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_for_client.model.bean.Dhcp;
import org.yesee.hinet_vcpe_for_client.model.dao.DhcpDao;

@Service
public class DhcpService {

	@Autowired
	private DhcpDao dhcpDao;
	
	public List<Dhcp> findAll(){
		return dhcpDao.findAll();
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
