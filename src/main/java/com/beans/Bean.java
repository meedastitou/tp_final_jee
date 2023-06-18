package com.beans;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class Bean {

	private DonutChartModel donutModel;

	@PostConstruct
	public void init() {
		donutModel = new DonutChartModel();
		ChartData data = new ChartData();
		DonutChartDataSet dataSet = new DonutChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(300);
		values.add(50);
		values.add(100);
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(54, 162, 235)");
		bgColors.add("rgb(255, 205, 86)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add("Red");
		labels.add("Blue");
		labels.add("Yellow");
		data.setLabels(labels);

		donutModel.setData(data);
	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}
	
	private List<Car> carList;
    private Car selectedCar;

    public Bean() {
        carList = getCars();
    }

    public void submit() {
        // Handle the selected car
        System.out.println("Selected Car: " + selectedCar);
    }

    public List<Car> getCarList() {
        return carList;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }
    

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    private List<Car> getCars() {
        // Retrieve the list of cars from a managed bean or a data source
        // In this example, we'll use a hardcoded list
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Corolla"));
        cars.add(new Car("Honda", "Civic"));
        cars.add(new Car("Ford", "Mustang"));
        return cars;
    }
	
	
	
}