package org.yesee.hinet_vcpe_provider.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yesee.hinet_vcpe_provider.model.bean.EquipmentResources;
import org.yesee.hinet_vcpe_provider.model.service.EquipmentResourcesService;

@RequestMapping("equipmentResourcesService")
@RestController
public class EquipmentResourcesRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentResourcesRestController.class);

	@Autowired
	EquipmentResourcesService equipmentResourcesService;

	@RequestMapping(value = "/resourcesUpdate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<EquipmentResources> updateUser(@PathVariable("id") Integer id,
			@RequestBody EquipmentResources equipmentResources) {
		LOGGER.info("Updating User: " + id);

		EquipmentResources currentEquipmentResources = equipmentResourcesService.findById(id).get();

		if (currentEquipmentResources == null) {
			LOGGER.info("User with id " + id + " not found");
			return new ResponseEntity<EquipmentResources>(HttpStatus.NOT_FOUND);
		}

		currentEquipmentResources.setCpu(equipmentResources.getCpu());
		currentEquipmentResources.setRam(equipmentResources.getRam());
		currentEquipmentResources.setAccountId(equipmentResources.getAccountId());

		equipmentResourcesService.addOrEditEquipmentResources(equipmentResources);
		return new ResponseEntity<EquipmentResources>(currentEquipmentResources, HttpStatus.OK);
	}

}
