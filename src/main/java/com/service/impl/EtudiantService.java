package com.service.impl;
 
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dao.EtudiantDAO;
import com.dao.FiliereDAO;
import com.entities.Etudiants;
import com.entities.Filiere;

//package com.service;
//import java.util.List;
//
//import com.dao.EtudiantDAO;
//import com.dao.impl.EtudiantDAOImpl;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//
//import com.entities.Etudiants;
@Component
public class EtudiantService{

	@Autowired
	private EtudiantDAO etudiantDao;
	public EtudiantService() {
		System.out.println("ssfeeasjd");
	}
	@Autowired
	private FiliereDAO filiereDao;
	@Transactional
	public void saveOrUpdate(Etudiants e) {
		this.etudiantDao.saveOrUpdate(e);
	}

	@Transactional
	public void delete(Etudiants e) {
		this.etudiantDao.delete(e);
	}

	@Transactional
	public Etudiants getById(int id) {
		return this.etudiantDao.getById(id);
	}

	@Transactional
	public List<Etudiants> getAll() {
		List<Etudiants> fs = this.etudiantDao.getAll();
		return fs;
	}
	@Transactional
	public List<Filiere> completeFiliere(Filiere f) {
		
		return this.filiereDao.getByExample(f, MatchMode.START, false);
	}
}
