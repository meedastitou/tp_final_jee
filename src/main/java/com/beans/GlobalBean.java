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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.entities.User;
import com.service.impl.UserServiceImpl;

@ManagedBean
@SessionScope
@Component
public class GlobalBean {
	
	@Autowired
	private UserServiceImpl userService;
	
	private boolean connected;
	private User user;
	
	
	public GlobalBean() {
		System.out.println("globalBean");
		this.user = new User();
		this.connected=false;
	}
	
	public void Login() throws Exception  {
		
		FacesMessage message = null;
        List<User> usersList = this.userService.getByExample(this.user);
        
        if(usersList != null &&  usersList.size() == 1) {
        	
        	message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Welcome " + user.getUserName(),null);
            this.connected=true;
            this.user=usersList.get(0);

        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", null);
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", connected); 
    }   
	public void Quit() {
		this.connected=false;
		this.user=new User();
		PrimeFaces.current().ajax().update("accueilPane");

		
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public StreamedContent getLogo() throws IOException {
	
		File file = new File("C:\\Users\\HP\\Desktop\\user.png");
		byte[] bytes = Files.readAllBytes(file.toPath());
		return DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
			return new ByteArrayInputStream(bytes);
		}).build();
	
	} 
}

