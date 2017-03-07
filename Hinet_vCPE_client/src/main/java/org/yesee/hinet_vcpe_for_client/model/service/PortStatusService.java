package org.yesee.hinet_vcpe_for_client.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_for_client.model.bean.PortStatus;
import org.yesee.hinet_vcpe_for_client.model.dao.PortStatusDao;

@Service
public class PortStatusService {

	@Autowired
	private PortStatusDao portStatusDao;
	
	public List<PortStatus> findAll() {
		return portStatusDao.findAll();
	}
	
	public Optional<PortStatus> findById(Integer id) {
		return portStatusDao.findById(id);
	}
	
	public Optional<PortStatus> findByPortId(Integer portId) {
		return portStatusDao.findByPortId(portId);
	}
	
}
