package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.Port;
import org.yesee.hinet_vcpe_provider.model.dao.PortDao;

import com.google.common.collect.Lists;

@Service
public class PortService {

	@Autowired
	private PortDao portDao;

	public List<Port> findAll() {
		return portDao.findAll();
	}
	
	public List<Port> findAllByMacAddress(String macAddress){
		List<Port> portList = Lists.newArrayList();
		for (Port port : portDao.findAll()){
			if (port.getMacAddress().equals(macAddress)){
				portList.add(port);
			}
		}
		return portList;
	}

	public Optional<Port> findById(Integer id) {
		return portDao.findById(id);
	}
	
	public Optional<Port> findByPortName(String portName, String macAddress){
		return portDao.findByPortName(portName, macAddress);
	}

	public void deletePortById(Integer id) {
		portDao.deletePortById(id);
	}

	public void addOrEditPort(Port port) {
		portDao.addOrEditPort(port);
	}
	

}
