package org.yesee.hinet_vcpe_provider.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yesee.hinet_vcpe_provider.model.bean.GatewaySwitch;

@Repository
@Transactional(readOnly = true)
public class GatewaySwitchDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewaySwitchDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}

	public List<GatewaySwitch> findAll(){
		Query query = getSessionFactory().getCurrentSession().createQuery("from GatewaySwitch");
		@SuppressWarnings("unchecked")
		List<GatewaySwitch> results = query.list();
		return results;
		
	}
	
	public Optional<GatewaySwitch> findById(Integer id){
		Query query =getSessionFactory().getCurrentSession().createQuery("from GatewaySwitch a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<GatewaySwitch> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	@Transactional(readOnly = false)
	public void deleteGatewaySwitchById(GatewaySwitch gatewaySwitch){
		getSessionFactory().getCurrentSession().delete(gatewaySwitch);
		
	}
	
	@Transactional(readOnly = false)
	public void addOrEditGatewaySwitch(GatewaySwitch gatewaySwitch){
		getSessionFactory().getCurrentSession().saveOrUpdate(gatewaySwitch);
	}
	
}
