package org.yesee.hinet_vcpe_provider.model.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yesee.hinet_vcpe_provider.model.bean.UserInfo;

@Repository
@Transactional(readOnly = true)
public class UserInfoDao {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findAll() {
		return (List<UserInfo>) getSessionFactory().getCurrentSession().createQuery("from UserInfo").list();
	}

	@Transactional(readOnly = false)
	public void save(UserInfo userInfo) {
		getSessionFactory().getCurrentSession().save(userInfo);
	}

	public Optional<UserInfo> findByUserIdAndPassword(String userId, String password) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from UserInfo a where a.userId = ? and a.pwd = ?");
		query.setParameter(0, userId);
		query.setParameter(1, password);
		@SuppressWarnings("unchecked")
		List<UserInfo> userInfos = query.list();
		return userInfos.isEmpty() ? Optional.empty() : Optional.of(userInfos.get(0));
	}

	public Optional<UserInfo> findByUserId(String userId){
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("from UserInfo a where a.userId = ? ");
		query.setParameter(0, userId);
		
		@SuppressWarnings("unchecked")
		List<UserInfo> userInfos = query.list();
		return userInfos.isEmpty() ? Optional.empty() : Optional.of(userInfos.get(0));
	}
	
	

}
