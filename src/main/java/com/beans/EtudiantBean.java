package com.beans;
import com.dao.EtudiantDAO;
import com.dao.impl.EtudiantDAOImpl;
import com.entities.*;
import com.service.impl.EtudiantService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("session")
@ManagedBean //facultatif
public class EtudiantBean {
	
	@Autowired
	private EtudiantService etudiantService;
	
	@Autowired
	private GeneratePathBean gpb;
	
	private Etudiants etudiant;
	private boolean modifyContext;
	
	public EtudiantBean() {
		etudiant = new Etudiants();
		this.modifyContext = false;
	}
	
	
	public Etudiants getEtudiant() {
		return etudiant;
	}
	

	public void setEtudiant(Etudiants etudiant) {
		this.etudiant = etudiant;
	}


	public List<Etudiants> getEtudiants(){
		return this.etudiantService.getAll();
	}
	
	public boolean isModifyContext() {
		return modifyContext;
	}
	
	public String getLabel() {
		return isModifyContext() ? "Modification" : "Ajout";
	}
	
	public void setModifyContext(boolean modifyContext) {
		this.modifyContext = modifyContext;
	}
	
	public void save() {
		this.etudiantService.saveOrUpdate(this.etudiant);
		
		
		String msg = isModifyContext() ? "est Modifie" : "est Ajoute";
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.etudiant.getPrenom() + " " + msg, null);
		
        FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().update("mes");
		
		this.setEtudiant(new Etudiants());
		this.setModifyContext(false);

	}
	
	public void modify(Etudiants e) {
		
		gpb.setExactePage("add");
		
		this.etudiant=e;
		this.setModifyContext(true);
		
		PrimeFaces.current().ajax().update("etudiantPanel");

	}
	
	public void delete(Etudiants e) {
		this.etudiantService.delete(e);
		
	}
	public List<Filiere> completeFiliere(String s) {
		Filiere filiere = new Filiere();
		filiere.setCode(s);
		List<Filiere> lsf = new ArrayList<>();
		lsf = this.etudiantService.completeFiliere(filiere);
		
		Iterator<Filiere> iter = lsf.iterator();
		
		while(iter.hasNext()) {
			System.out.println("========  " + iter.next().getCode());
		}
		
		
		return lsf;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        
        byte[] file = event.getFile().getContent();
        System.out.println(file.length);
        System.out.println("med");
        if (file != null && file.length > 0) {
        	System.out.println("good choosed");
            this.etudiant.setPicture(file);
            FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
	public StreamedContent etudiantPicture() throws IOException {
		
		if (this.etudiant.getPicture() != null)
			return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
				return new ByteArrayInputStream(this.etudiant.getPicture());
			}).build();
		else {
			
			File file = new File("C:\\Users\\HP\\Desktop\\user.png");
			byte[] bytes = Files.readAllBytes(file.toPath());
			this.etudiant.setPicture(bytes);
			return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
				return new ByteArrayInputStream(this.etudiant.getPicture());
			}).build();
		}
	}
	
}
