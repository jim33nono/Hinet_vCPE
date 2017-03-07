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
import org.yesee.hinet_vcpe_provider.model.bean.Wan;

@Repository
@Transactional(readOnly = true)
public class WanDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WanDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public List<Wan> findAll(){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Wan");
		@SuppressWarnings("unchecked")
		List<Wan> results = query.list();
		return results;
		
	}
	
	public List<Wan> findAllByMacAddressQuicker(String macAddress){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Wan a where a.macAddress = ?");
		query.setParameter(0, macAddress);
		@SuppressWarnings("unchecked")
		List<Wan> results = query.list();
		return results;
				
	}
	
	public Optional<Wan> findById(Integer id){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Wan a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<Wan> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	
	public Optional<Wan> findByWanName(String wanName, String macAddress){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Wan a where a.wanName = ? and a.macAddress = ?");
		query.setParameter(0, wanName);
		query.setParameter(1, macAddress);
		@SuppressWarnings("unchecked")
		List<Wan> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	@Transactional(readOnly = false)
	public void deleteWanById(Wan wan){
		getSessionFactory().getCurrentSession().delete(wan);
		
	}
	
	@Transactional(readOnly = false)
	public void addOrEditWan(Wan wan){
		getSessionFactory().getCurrentSession().saveOrUpdate(wan);
		
	}
	
	
}
