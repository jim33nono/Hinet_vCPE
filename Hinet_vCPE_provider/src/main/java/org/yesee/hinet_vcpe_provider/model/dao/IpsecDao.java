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
import org.yesee.hinet_vcpe_provider.model.bean.Ipsec;


@Repository
@Transactional(readOnly = true)
public class IpsecDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IpsecDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public List<Ipsec> findAll(){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Ipsec");
		@SuppressWarnings("unchecked")
		List<Ipsec> results = query.list();
		return results;
		
	}
	
	public Optional<Ipsec> findById(Integer id){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Ipsec a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<Ipsec> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	public Optional<Ipsec> findByIpsecName(String ipsecName , String macAddress){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Ipsec a where a.ipsecName = ? and a.macAddress = ?");
		query.setParameter(0, ipsecName);
		query.setParameter(1, macAddress);
		@SuppressWarnings("unchecked")
		List<Ipsec> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	@Transactional(readOnly = false)
	public void deleteIpsecById(Integer id){
		Query query = getSessionFactory().getCurrentSession().createQuery("delete from Ipsec a where a.id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
		
	}
	
	@Transactional(readOnly = false)
	public void addOrEditIpsec(Ipsec ipsec){
		getSessionFactory().getCurrentSession().saveOrUpdate(ipsec);
		
	}
	
	
}
