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
import org.yesee.hinet_vcpe_for_client.model.bean.Dhcp;


@Repository
@Transactional(readOnly = true)
public class DhcpDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DhcpDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public List<Dhcp> findAll(){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Dhcp");
		@SuppressWarnings("unchecked")
		List<Dhcp> results = query.list();
		return results;
		
	}
	
	public Optional<Dhcp> findById(Integer id){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from Dhcp a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<Dhcp> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	@Transactional(readOnly = false)
	public void deleteDhcpById(Integer id){
		Query query = getSessionFactory().getCurrentSession().createQuery("delete from Dhcp a where a.id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}
	
	@Transactional(readOnly = false)
	public void addOrEditDhcp(Dhcp dhcp){
		getSessionFactory().getCurrentSession().saveOrUpdate(dhcp);
		
	}
	
	
}
