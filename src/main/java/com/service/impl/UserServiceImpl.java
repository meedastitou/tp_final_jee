package com.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.beans.GlobalBean;
import com.dao.UserDAO;
import com.entities.User;
@Component
public class UserServiceImpl{

	@Autowired
	private UserDAO userDao;
	
	public UserServiceImpl() {
		System.out.println("sjd");
	}
	@Transactional
	public void saveOrUpdate(User f) {
		this.userDao.saveOrUpdate(f);
	}

	@Transactional
	public void delete(User f) {
		this.userDao.delete(f);
	}

	@Transactional
	public User getById(int id) {
		return this.userDao.getById(id);
	}

	@Transactional
	public List<User> getAll() {

		List<User> fs = this.userDao.getAll();
		return fs;
	}

//	public List<User> getAllUsersOrdered() {
//		List<User> users = this.getAll();
//		Collections.sort(users, new Comparator<User>() {
//			public int compare(User o1, User o2) {
//				return o1.getNom().compareTo(o2.getNom());
//			}
//		});
//		return users;
//	}

	@Transactional
	public List<User> getByExample(User u) {
		return this.userDao.getByExample(u, MatchMode.EXACT, false);
	}

}
