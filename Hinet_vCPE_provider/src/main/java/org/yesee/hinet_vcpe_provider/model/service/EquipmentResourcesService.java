package org.yesee.hinet_vcpe_provider.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.EquipmentResources;
import org.yesee.hinet_vcpe_provider.model.dao.EquipmentResourcesDao;

@Service
public class EquipmentResourcesService {
	
	@Autowired
	private EquipmentResourcesDao equipmentResourcesDao;
	
	public List<EquipmentResources> findAll() {
		return equipmentResourcesDao.findAll();
	}

	public Optional<EquipmentResources> findById(Integer id) {
		return equipmentResourcesDao.findById(id);
	}
	
	public void deleteEquipmentResources (EquipmentResources equipmentResources) {
		equipmentResourcesDao.deleteEquipmentResourcesById(equipmentResources);
	}
	
	public void addOrEditEquipmentResources (EquipmentResources equipmentResources) {
		equipmentResourcesDao.addOrEditEquipmentResources(equipmentResources);
	}
	
}
