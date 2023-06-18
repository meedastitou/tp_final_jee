package com.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.entities.Filiere;
import com.service.impl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
public class FiliereConverter  implements Converter {
	
	@Autowired
    private FiliereService filiereService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value!=null && value.trim().length() >0)
			   return this.filiereService.getById(Integer.parseInt(value));
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value!=null)
			return ((Filiere)value).getIdFiliere().toString();
		return null;
	}


}
