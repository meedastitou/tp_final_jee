package com.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.service.impl.FiliereService;



@ManagedBean  
@Component
@Scope("session")
public class ChartJsView {

	
	@Autowired
	private FiliereService filiereService;
	

	public PieChartModel getPie() {

		// getEtudiantsByFilieres
		PieChartModel pieModel1 = new PieChartModel();

		for (Object[] obj : filiereService.getDash()) {
			
			// obj[0]== code // obj[1]==size
			 pieModel1.set((String) obj[0],  (Long) obj[1]);
			 
//			pieModel1.getData().put((String) obj[0], (Long) obj[1]);
		}
		


        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
        
		return pieModel1;

	}
	
	public PolarAreaChartModel getPolarAreaModel() {
		 PolarAreaChartModel polarAreaModel = new PolarAreaChartModel();
	        ChartData data = new ChartData();

	        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
	        List<Number> values = new ArrayList<>();
	        List<String> labels = new ArrayList<>();
	        List<String> bgColors = new ArrayList<>();
	        bgColors.add("rgb(255, 99, 132)");
	        bgColors.add("rgb(75, 192, 192)");
	        bgColors.add("rgb(255, 205, 86)");
	        bgColors.add("rgb(201, 203, 207)");
	        bgColors.add("rgb(54, 162, 235)");
	        for (Object[] obj : filiereService.getDash()) {
				
				 values.add((Long) obj[1]);
				 labels.add((String) obj[0]);
				 bgColors.add("rgb(255, 231, 23)");
			}
	      
	        dataSet.setData(values);

	        dataSet.setBackgroundColor(bgColors);

	        data.addChartDataSet(dataSet);
	       
	        data.setLabels(labels);

	        polarAreaModel.setData(data);
			return polarAreaModel;
	        
	    }
		
	public HorizontalBarChartModel getHbarModel() {
		
		HorizontalBarChartModel hbarModel = new HorizontalBarChartModel();
	    ChartData data = new ChartData();
	
	    HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
	    hbarDataSet.setLabel("My First Dataset");
	    List<Number> values = new ArrayList<>();
	    List<String> labels = new ArrayList<>();
	    for (Object[] obj : filiereService.getDash()) {
			
			 values.add((Long) obj[1]);
			 labels.add((String) obj[0]);

		}
	    
	    hbarDataSet.setData(values);
	
	    List<String> bgColor = new ArrayList<>();
	    bgColor.add("rgba(255, 99, 132, 0.2)");
	    bgColor.add("rgba(255, 159, 64, 0.2)");
	    bgColor.add("rgba(255, 205, 86, 0.2)");
	    bgColor.add("rgba(75, 192, 192, 0.2)");
	    bgColor.add("rgba(54, 162, 235, 0.2)");
	    bgColor.add("rgba(153, 102, 255, 0.2)");
	    bgColor.add("rgba(201, 203, 207, 0.2)");
	    hbarDataSet.setBackgroundColor(bgColor);
	
	    List<String> borderColor = new ArrayList<>();
	    borderColor.add("rgb(255, 99, 132)");
	    borderColor.add("rgb(255, 159, 64)");
	    borderColor.add("rgb(255, 205, 86)");
	    borderColor.add("rgb(75, 192, 192)");
	    borderColor.add("rgb(54, 162, 235)");
	    borderColor.add("rgb(153, 102, 255)");
	    borderColor.add("rgb(201, 203, 207)");
	    hbarDataSet.setBorderColor(borderColor);
	    hbarDataSet.setBorderWidth(1);
	
	    data.addChartDataSet(hbarDataSet);
	
	    
	    data.setLabels(labels);
	    hbarModel.setData(data);
	
	    //Options
	    BarChartOptions options = new BarChartOptions();
	    CartesianScales cScales = new CartesianScales();
	    CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	    linearAxes.setOffset(true);
	    CartesianLinearTicks ticks = new CartesianLinearTicks();
	    ticks.setBeginAtZero(true);
	    linearAxes.setTicks(ticks);
	    cScales.addXAxesData(linearAxes);
	    options.setScales(cScales);
	
	    Title title = new Title();
	    title.setDisplay(true);
	    title.setText("Horizontal Bar Chart");
	    options.setTitle(title);
	
	    hbarModel.setOptions(options);
	    
	    
	    return hbarModel;
	}

	// 		public void itemSelect(ItemSelectEvent event) {
//	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
//	                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());
//
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//	    }

   


} 