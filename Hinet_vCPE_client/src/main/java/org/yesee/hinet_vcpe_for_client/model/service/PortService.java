package org.yesee.hinet_vcpe_for_client.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_for_client.model.bean.Port;
import org.yesee.hinet_vcpe_for_client.model.dao.PortDao;

@Service
public class PortService {

	@Autowired
	private PortDao portDao;

	public List<Port> findAll() {
		return portDao.findAll();
	}

	public Optional<Port> findById(Integer id) {
		return portDao.findById(id);
	}
	
	public Optional<Port> findByPortName(String portName){
		return portDao.findByPortName(portName);
	}

	public void deletePortById(Integer id) {
		portDao.deletePortById(id);
	}

	public void addOrEditPort(Port port) {
		portDao.addOrEditPort(port);
	}
	

}
