package com.beans;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

import com.entities.Filiere;
import com.entities.User;
import com.service.impl.FiliereService;
import com.service.impl.UserServiceImpl;


@Component
@Scope("session")
@ManagedBean
public class UserBean {

	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private GeneratePathBean gpb;
	
	@Autowired
	private GlobalBean globalBean;
	
	
	private User user;
	private boolean modifyContext;
	
	public UserBean() {
		System.out.println("lskpkp");
		this.user = new User();
		this.modifyContext = false;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public boolean isModifyContext() {
		return modifyContext;
	}

	public void setModifyContext(boolean modifyContext) {
		this.modifyContext = modifyContext;
	}

	public List<User> getUsers() {
		
		return this.userService.getAll();
	}
	
	public String getLabel() {
		return isModifyContext() ? "Modification" : "Ajout";
	}
	
	public void save() {
		this.userService.saveOrUpdate(this.user);
		
		String msg = isModifyContext() ? "est Modifie" : "est Ajoute";
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.user.getNom()+" " + msg, null);
		
        FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().update("mes");

		this.setModifyContext(false);
		this.setUser(new User());
	}
	
	public void modify(User u) {
		
		gpb.setExactePage("add");
		this.user=u;
		this.setModifyContext(true);
		PrimeFaces.current().ajax().update("userPanel");
	}
	
	public void delete(User u) {
		
		this.userService.delete(u);
	}
	public void chemin(User u) throws IOException {
		gpb.setExactePage("add");
		this.user=u;
		this.setModifyContext(true);
		PrimeFaces.current().ajax().update("userPanel");
		
	}
	public void handleFileUpload(FileUploadEvent event) {
        
        byte[] file = event.getFile().getContent();
        System.out.println(file.length);
        System.out.println("med");
        if (file != null && file.length > 0) {
        	System.out.println("good choosed");
            this.user.setPicture(file);
            FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	public StreamedContent etudiantPicture() throws IOException {
		
		if (this.user.getPicture() != null)
			return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
				return new ByteArrayInputStream(this.user.getPicture());
			}).build();
		else {
			
			File file = new File("C:\\Users\\HP\\Desktop\\user.png");
			byte[] bytes = Files.readAllBytes(file.toPath());
			this.user.setPicture(bytes);
			return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
				return new ByteArrayInputStream(this.user.getPicture());
			}).build();
		}
	}
}
