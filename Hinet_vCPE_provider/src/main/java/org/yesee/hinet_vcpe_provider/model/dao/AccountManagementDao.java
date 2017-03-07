package org.yesee.hinet_vcpe_provider.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yesee.hinet_vcpe_provider.model.bean.AccountManagement;

@Repository
@Transactional(readOnly = true)
public class AccountManagementDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public List<AccountManagement> findAll(){
		Query query = getSessionFactory().getCurrentSession().createQuery("from AccountManagement");
		@SuppressWarnings("unchecked")
		List<AccountManagement> results = query.list();
		return results;
	}
	
	public Optional<AccountManagement> findById(Integer id){
		Query query = getSessionFactory().getCurrentSession().createQuery("from AccountManagement a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<AccountManagement> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
		
	}
	
	public Optional<AccountManagement> locateUser(String account, String password, String macAddress) {
		Query query = getSessionFactory().getCurrentSession().createQuery("from AccountManagement a where a.account = ? and a.password = ? and a.macAddress = ?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		query.setParameter(2, macAddress);
		@SuppressWarnings("unchecked")
		List<AccountManagement> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
				
	}
	
	@Transactional(readOnly = false)
	public void deleteAccountManagementById(AccountManagement accountManagement){
		getSessionFactory().getCurrentSession().delete(accountManagement);
	}
	
	@Transactional(readOnly = false)
	public void addOrEditAccountManagement(AccountManagement accountManagement){
		getSessionFactory().getCurrentSession().saveOrUpdate(accountManagement);
	}

}
