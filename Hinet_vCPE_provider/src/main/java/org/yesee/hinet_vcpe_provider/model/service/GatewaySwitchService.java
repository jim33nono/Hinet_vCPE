package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.GatewaySwitch;
import org.yesee.hinet_vcpe_provider.model.dao.GatewaySwitchDao;

import com.google.common.collect.Lists;


@Service
public class GatewaySwitchService {

	@Autowired
	private GatewaySwitchDao gatewaySwitchDao;

	public List<GatewaySwitch> findAll() {
		return gatewaySwitchDao.findAll();
	}

	public List<GatewaySwitch> findAllByMacAddress(String macAddress) {
		List<GatewaySwitch> gatewaySwitchInfo = Lists.newArrayList();
		for (GatewaySwitch gatewaySwitch : gatewaySwitchDao.findAll()) {
			if (macAddress.equals(gatewaySwitch.getMacAddress())) {
				gatewaySwitchInfo.add(gatewaySwitch);
			}
		}
		return gatewaySwitchInfo;
	}

	public Optional<GatewaySwitch> findById(Integer id) {
		return gatewaySwitchDao.findById(id);

	}

	public void deleteGatewaySwitchById(GatewaySwitch gatewaySwitch) {
		gatewaySwitchDao.deleteGatewaySwitchById(gatewaySwitch);
	}

	public void addOrEditGatewaySwitch(GatewaySwitch gatewaySwitch) {
		gatewaySwitchDao.addOrEditGatewaySwitch(gatewaySwitch);
	}

}
