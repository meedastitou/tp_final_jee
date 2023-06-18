package com.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.NamedEvent;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@ManagedBean
@javax.annotation.ManagedBean
@NamedEvent
@Component
@FacesConverter("carConverter")
public class CarConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Car> cars = getCarsFromContext(context);
        for (Car car : cars) {
            if (car.toString().equals(value)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Car) {
            return value.toString();
        }
        return "";
    }

    private List<Car> getCarsFromContext(FacesContext context) {
        // Retrieve the list of cars from a managed bean or a data source
        // In this example, we'll use a hardcoded list
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Corolla"));
        cars.add(new Car("Honda", "Civic"));
        cars.add(new Car("Ford", "Mustang"));
        return cars;
    }
}