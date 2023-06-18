package com.dao;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.entities.Filiere;

public interface Idao<T> {
	void saveOrUpdate(T f);	
	void delete(T f);
	T getById(int id);
	List<T> getAll();
	List<T> getByExample(T o,MatchMode mode ,boolean detached);
}
