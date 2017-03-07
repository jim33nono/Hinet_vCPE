package org.yesee.hinet_vcpe_for_client.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_for_client.model.bean.GatewaySwitch;
import org.yesee.hinet_vcpe_for_client.model.dao.GatewaySwitchDao;

@Service
public class GatewaySwitchService {

	@Autowired
	private GatewaySwitchDao gatewaySwitchDao;
	
	public List<GatewaySwitch> findAll(){
		return gatewaySwitchDao.findAll();
	}
	
	public Optional<GatewaySwitch> findById(Integer id){
		return  gatewaySwitchDao.findById(id);
		
	}
	
	public void deleteGatewaySwitchById(GatewaySwitch gatewaySwitch){
		gatewaySwitchDao.deleteGatewaySwitchById(gatewaySwitch);
	}
	
	public void addOrEditGatewaySwitch(GatewaySwitch gatewaySwitch){
		gatewaySwitchDao.addOrEditGatewaySwitch(gatewaySwitch);
	}
	
}
