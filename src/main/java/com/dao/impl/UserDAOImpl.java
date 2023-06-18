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

import com.dao.UserDAO;
import com.entities.User;

@Repository
public class UserDAOImpl extends HibernateDaoSupport  implements UserDAO{

	@Autowired
	public void setUpSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(User u) {
		this.getHibernateTemplate().saveOrUpdate(u);
		
	}

	@Override
	@Transactional
	public void delete(User u) {
		this.getHibernateTemplate().delete(u);
		
	}

	@Override
	@Transactional
	public User getById(int id) {
		return this.getHibernateTemplate().get(User.class, id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAll() { //HQL
		
		DetachedCriteria dc = DetachedCriteria.forClass(User.class).addOrder(Order.asc("id"));
		return (List<User>)this.getHibernateTemplate().findByCriteria(dc);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getByExample(User u, MatchMode mode, boolean detached) {

		Example example = Example.create(u);
		example.enableLike(mode);
		example.ignoreCase();
		example.excludeZeroes();

		DetachedCriteria dcri = DetachedCriteria.forClass(User.class);
		dcri.add(example);
		List<User> users = (List<User>) getHibernateTemplate().findByCriteria(dcri);

		if (detached) {
			for (User user : users)
				getHibernateTemplate().evict(user);
		}

		return users;
	}
}
