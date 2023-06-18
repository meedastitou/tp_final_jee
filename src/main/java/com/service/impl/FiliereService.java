package com.service.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FiliereDAO;
import com.dao.impl.FiliereDAOImpl;
import com.entities.Filiere;

@Component
public class FiliereService{
	@Autowired
	private FiliereDAO filiereDao;
	public FiliereService() {
		System.out.println("sssjd");
	}
	@Transactional
	public void saveOrUpdate(Filiere f) {
		this.filiereDao.saveOrUpdate(f);
	}

	@Transactional
	public void delete(Filiere f) {
		this.filiereDao.delete(f);
	}

	@Transactional
	public Filiere getById(int id) {
		return this.filiereDao.getById(id);
	}

	@Transactional
	public List<Filiere> getAll() {
		
		List<Filiere> fs = this.filiereDao.getAll();
				
		return fs;
	}
	@Transactional
	public List<Object[]> getDash() {
		return filiereDao.getDash();
	}
	@Transactional
	public List<Filiere> completeFiliere(Filiere f) {
		
		return this.filiereDao.getByExample(f, MatchMode.START, false);
	}
}
