package com.service.impl;
import java.util.Comparator;

import com.entities.Etudiants;
public class comparator implements Comparator<Etudiants>{
	private String by=null;
	private String order=null;
	public comparator(String by, String order) {
		this.by = by;
		this.order = order;
	}
	@Override
	public int compare(Etudiants o1, Etudiants o2) {
		Etudiants a=new Etudiants();
		if(this.order.equals("desc")){
			a  = o1;
			o1 = o2;
			o2 = a;
		}		
		switch(by) {
		case "id":
			return o1.getId() - o2.getId();
		case "name":
			return o1.getNom().toLowerCase().compareTo(o2.getNom().toLowerCase());	
		case "prenom":
			return o1.getPrenom().toLowerCase().compareTo(o2.getPrenom().toLowerCase());
		case "cne":
			return o1.getCne().toLowerCase().compareTo(o2.getCne().toLowerCase());
		}
		return 0;
	}
	
}
