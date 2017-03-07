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
import org.yesee.hinet_vcpe_provider.model.bean.Lan;

@Repository
@Transactional(readOnly = true)
public class LanDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public List<Lan> findAll() {
		Query query = getSessionFactory().getCurrentSession().createQuery("from Lan");
		@SuppressWarnings("unchecked")
		List<Lan> results = query.list();
		return results;

	}

	public Optional<Lan> findByMacAddress(String macAddress) {
		Query query = getSessionFactory().getCurrentSession().createQuery("from Lan a where a.macAddress = ?");
		query.setParameter(0, macAddress);
		@SuppressWarnings("unchecked")
		List<Lan> result = query.list();
		return Optional.ofNullable(result.get(0));
	}

	public Optional<Lan> findById(Integer id) {
		Query query = getSessionFactory().getCurrentSession().createQuery("from Lan a where a.id = ?");
		query.setParameter(0, id);
		@SuppressWarnings("unchecked")
		List<Lan> result = query.list();
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));

	}

	@Transactional(readOnly = false)
	public void deleteLanById(Integer id) {
		Query query = getSessionFactory().getCurrentSession().createQuery("delete from Lan a where a.id = ?");
		query.setParameter(0, id);
		query.executeUpdate();

	}

	@Transactional(readOnly = false)
	public void addOrEditLan(Lan lan) {
		getSessionFactory().getCurrentSession().saveOrUpdate(lan);

	}

}