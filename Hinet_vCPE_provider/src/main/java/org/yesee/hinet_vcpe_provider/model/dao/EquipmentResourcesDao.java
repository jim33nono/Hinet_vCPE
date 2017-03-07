package org.yesee.hinet_vcpe_provider.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yesee.hinet_vcpe_provider.model.bean.EquipmentResources;

@Repository
@Transactional(readOnly = true)
public class EquipmentResourcesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public List<EquipmentResources> findAll(){
		Query query = getSessionFactory().getCurrentSession().createQuery("from EquipmentResources");
		@SuppressWarnings("unchecked")
		List<EquipmentResources> results = query.list();
		return results;
	}
	
	public Optional<EquipmentResources> findById(Integer id){
		Query query = getSessionFactory().getCurrentSession().createQuery("from EquipmentResources a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<EquipmentResources> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	@Transactional(readOnly = false)
	public void deleteEquipmentResourcesById(EquipmentResources equipmentResources){
		getSessionFactory().getCurrentSession().delete(equipmentResources);
	}
	
	@Transactional(readOnly = false)
	public void addOrEditEquipmentResources(EquipmentResources equipmentResources){
		getSessionFactory().getCurrentSession().saveOrUpdate(equipmentResources);
	}
	
	
	
}
