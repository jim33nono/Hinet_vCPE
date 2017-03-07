package org.yesee.hinet_vcpe_for_client.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yesee.hinet_vcpe_for_client.model.bean.Port;

@Repository
@Transactional(readOnly = true)
public class PortDao {

private static final Logger LOGGER = LoggerFactory.getLogger(PortDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public List<Port> findAll(){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Port");
		@SuppressWarnings("unchecked")
		List<Port> results = query.list();
		return results;
		
	}
	
	public Optional<Port> findById(Integer id){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Port a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<Port> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	public Optional<Port> findByPortName(String portName){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Port a where a.portName = ?");
		query.setParameter(0, portName);
		@SuppressWarnings("unchecked")
		List<Port> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	@Transactional(readOnly = false)
	public void deletePortById(Integer id){
		Query query = getSessionFactory().getCurrentSession().createQuery("delete from Port a where a.id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
		
	}
	
	@Transactional(readOnly = false)
	public void addOrEditPort(Port port){
		getSessionFactory().getCurrentSession().saveOrUpdate(port);
		
	}
	
	public List<String> getAllPortName(){
		Query query = getSessionFactory().getCurrentSession().createQuery("SELECT portName from Port");
		@SuppressWarnings("unchecked")
		List<String> results = query.list();
		return results;
		
	}
	
}
