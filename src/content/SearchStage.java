/**
My Name: Jingxin Jiang
My Student Number: 991657051
Final Project
Date: 2022.04.01
*/
package content;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import salesfx.SalesFX;
import static salesfx.SalesFX.employeeList;

public class SearchStage extends Stage{
    private Label method=new Label("Search though: ");
    private RadioButton city=new RadioButton("City");
    private RadioButton position=new RadioButton("Position");
    private Label search=new Label("Input Search City or Position: ");
    private final TextField SerchField=new TextField();
    private final Button searchButton= new Button("Search");
    private Label results=new Label("Results: ");
    private final TextArea resultsDisplay=new TextArea();
    //private static ArrayList<Employee> employeeList=  EmployeeFile.getEmployeeList();
    public SearchStage(){
        Pane pane=getVBoxPane();
        Scene scene=new Scene(pane,600,350);
        searchButton.setOnAction(new setSearchButton());
        setScene(scene);
        scene.getStylesheets().add("/css/TheStyle.css");
        setTitle("Search Stage");
        setX(900);
        setY(200);        
    }
// layout the search stage start    
     private VBox getVBoxPane(){
        ToggleGroup group = new ToggleGroup();
        city.setToggleGroup(group);
        position.setToggleGroup(group);
        city.setSelected(true);
        HBox radioButtonGether=new HBox(method,city,position);
        radioButtonGether.setSpacing(10);
        HBox searchInfoGether=new HBox(search, SerchField,searchButton);        
        searchInfoGether.setSpacing(10);
        HBox resultsGether=new HBox(results, resultsDisplay);        
        resultsGether.setSpacing(10);
        VBox basic=new VBox(radioButtonGether,searchInfoGether,resultsGether);
        basic.setSpacing(10);
        return basic;
    }
// layout the search stage end 

//results display
    private String setResultsDisplay(String method){
        
        String s=new String();   
        try{
            ArrayList<Employee> employeeList=SalesFX.employeeList;                    
            System.out.println("SerchField display check:"+SerchField.getText());  //delete
            if(SerchField.getText()!=""){
                String inputSearch= SerchField.getText();
                System.out.println("SerchField display check in if:"+SerchField.getText());
                if(method.equals("City")){
                    System.out.println("list size in searchStage"+SalesFX.employeeList.size());
                    for(int j=0;j<employeeList.size();j++){
                    String cityArrayList=employeeList.get(j).getCity();
                        System.out.println("list city check"+cityArrayList);
                    if(cityArrayList.equalsIgnoreCase(inputSearch)){          
                       s+=employeeList.get(j).getID() +","+employeeList.get(j).getName()+","+employeeList.get(j).getCity()+","+employeeList.get(j).getPosition()+"\n";                
                    System.out.println("s string city check"+s);
                    } 
                    }                     
                }else if(method.equals("Position")){
                    for(int j=0;j<employeeList.size();j++){
                    String positionArrayList=employeeList.get(j).getPosition();
                        System.out.println("list position check"+positionArrayList);
                    if(positionArrayList.equalsIgnoreCase(inputSearch)){          
                       s+=employeeList.get(j).getID() +",  "+employeeList.get(j).getName()+", "+employeeList.get(j).getCity()+", "+employeeList.get(j).getPosition()+"\n";                
                    System.out.println("s string position check"+s);
                    } 
                    }  
                }
            }
        }catch( Exception e){
            System.out.println(e);
        }    
        System.out.println(s);
        return s;   
    }
//END of display
    
    //search event control
    private class setSearchButton implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent t) {            
            String s=new String();
            if(city.isSelected()){
               s= setResultsDisplay("City");
            }else if(position.isSelected()){
               s= setResultsDisplay(position.getText());
            }else{s="There is something wrong";}
            resultsDisplay.setText(s);
        }
        
    }               
}
