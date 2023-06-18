package com.dao;
import java.util.List;

import com.entities.Filiere;
public interface FiliereDAO extends Idao<Filiere>{
	List<Object[]> getDash();
}
