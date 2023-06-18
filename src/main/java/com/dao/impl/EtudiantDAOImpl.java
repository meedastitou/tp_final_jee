package com.dao.impl;

import com.entities.*;
import com.dao.*;

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

@Repository
public class EtudiantDAOImpl extends HibernateDaoSupport implements EtudiantDAO{

	@Autowired
	public void setUpSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}		
	@Override
	@Transactional
	public void saveOrUpdate(Etudiants e) {
//		this.getHibernateTemplate().saveOrUpdate(e.getFiliere());
		this.getHibernateTemplate().saveOrUpdate(e);
	}
	
	
	@Override
	@Transactional
	public void delete(Etudiants e) {
		this.getHibernateTemplate().delete(e);
	}

	@Override
	public Etudiants getById(int id) {
		
		return this.getHibernateTemplate().get(Etudiants.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Etudiants> getAll() { //HQL

		DetachedCriteria dc = DetachedCriteria.forClass(Etudiants.class).addOrder(Order.asc("id"));
		return (List<Etudiants>)this.getHibernateTemplate().findByCriteria(dc);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Etudiants> getByExample(Etudiants e, MatchMode mode, boolean detached) {
		Example example =Example.create(e);
		example.enableLike(mode);
		example.ignoreCase();
		example.excludeZeroes();
		
		
		
		DetachedCriteria dcri=DetachedCriteria.forClass(Etudiants.class);
		dcri.add(example);
		List<Etudiants> etudiants =(List<Etudiants>)getHibernateTemplate().findByCriteria(dcri);
		
		if (detached) {
			for (Etudiants etudiant :etudiants) 
				  getHibernateTemplate().evict(etudiant);
		}
		
		return etudiants;
		
	}
		
}
