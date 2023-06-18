package com.beans;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.entities.Filiere;

@ManagedBean
@SessionScope
@Component
public class GeneratePathBean {
	
	@Autowired
	private FiliereBean filiereBean;
	private String currentPage;
	private String exactePage;
	public GeneratePathBean() {

		this.currentPage="Accueil";
		this.exactePage=null;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getExactePage() {
		return exactePage;
	}
	
	public void setExactePage(String exactePage) {
		this.exactePage = exactePage;
	}
	
	public void GoTo(String destination) throws IOException {
		this.exactePage=null;
		this.currentPage=destination;
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
	}
	public void GoTo(String entitie, String destenation) throws IOException {
		
		this.exactePage=destenation;
		this.currentPage=entitie;
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
	}
	
}
