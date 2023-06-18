package com.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.entities.Filiere;
import com.service.impl.FiliereService;


@Component
@Scope("session")
@ManagedBean //Facultative
public class FiliereBean {

	@Autowired
	private FiliereService filiereService;
	
	@Autowired
	private GeneratePathBean gpb;
	
	@Autowired
	private GlobalBean globalBean;
	
	private Filiere filiere;
	private boolean modifyContext;
	
	public FiliereBean() {
		this.filiere = new Filiere();
		this.modifyContext = false;
	}
	
//	public Filiere getFiliere() {
//		return filiere;
//	}
//
//	public void setFiliere(Filiere filiere) {
//		this.filiere = filiere;
//	}
//	
//	
//	public boolean isModifyContext() {
//		return modifyContext;
//	}
//
//	public void setModifyContext(boolean modifyContext) {
//		this.modifyContext = modifyContext;
//	}
//
//	public List<Filiere> getFilieres() {
//		
//		return this.filiereService.getAll();
//	}
//	
//	public String getLabel() {
//		return isModifyContext() ? "Modification" : "Ajout";
//	}
//	
//	public void save() {
//		this.filiere.setUser(globalBean.getUser());
//		this.filiereService.saveOrUpdate(this.filiere);
//		
//		String msg = isModifyContext() ? "est Modifie" : "est Ajoute";
//		
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.filiere.getCode()+" " + msg, null);
//		
//        FacesContext.getCurrentInstance().addMessage(null, message);
//		PrimeFaces.current().ajax().update("form");
//
//		this.setModifyContext(false);
//		this.setFiliere(new Filiere());
//	}
//	
//	public void modify(Filiere f) {
//		
//		this.setModifyContext(true);
//		this.setFiliere(f);
//	}
//	
//	public void delete(Filiere f) {
//		
//		this.filiereService.delete(f);
//	}
//	public void chemin(Filiere f) throws IOException {
//		gpb.setExactePage("add");
//		this.filiere=f;
//		this.setModifyContext(true);
//		PrimeFaces.current().ajax().update("filirePanel");
//		
//	}
//	
//   

}
