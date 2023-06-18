package com.dao.impl;


import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FiliereDAO;

import com.entities.Filiere;
@Repository
public class FiliereDAOImpl extends HibernateDaoSupport implements FiliereDAO {
	
	@Autowired
	public void setUpSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveOrUpdate(Filiere f) {
		this.getHibernateTemplate().saveOrUpdate(f);
	}
	
	@Override
	@Transactional
	public void delete(Filiere f) {
		this.getHibernateTemplate().delete(f);
	}

	@Override
	public Filiere getById(int id) {
		
		return this.getHibernateTemplate().get(Filiere.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Filiere> getAll() { //HQL
		
		DetachedCriteria dc = DetachedCriteria.forClass(Filiere.class).addOrder(Order.asc("id"));
		return (List<Filiere>)this.getHibernateTemplate().findByCriteria(dc);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Filiere> getByExample(Filiere f, MatchMode mode, boolean detached) {
		Example example = Example.create(f);
		example.enableLike(mode);
		example.ignoreCase();
		example.excludeZeroes();

		DetachedCriteria dcri = DetachedCriteria.forClass(Filiere.class);
		dcri.add(example);

		List<Filiere> filieres = (List<Filiere>) getHibernateTemplate().findByCriteria(dcri);
		if (detached) {
			for (Filiere filiere : filieres)
				getHibernateTemplate().evict(filiere);
		}
		return filieres;
	}
	
	@Transactional
	@Override
	public List<Object[]> getDash() {
		return sessionFactory.getCurrentSession().createQuery("select f.code, count(e) " + "from Filiere f, Etudiants e "
				+ "where f.id = e.filiere group by f.code", Object[].class).getResultList();
	}
}
