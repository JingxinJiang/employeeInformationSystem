/**
My Name: Jingxin Jiang
My Student Number: 991657051
Final Project
Date: 2022.04.01
Modify: 2022.04.06 3:00pm
Modify: 2022.04.06 7:00pm
Modify: 2022.04.11 4:30pm
Modify: 2022.04.11 6:30pm
Modify: 2022.04.11 8:10pm
Finished: 2022.04.11 11:25pm
*/
package salesfx;
import content.Employee;
import content.EmployeeFile;
import content.SearchStage;
import javafx.event.ActionEvent;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static salesfx.SalesFX.employeeList;


public class SalesFX extends Application {
    //navigation
    private Label title1=new Label("\nBasic Navigation\n");
    private final Button btnFirst= new Button("First");
    private final Button btnNext= new Button("Next");
    private final Button btnPrevious= new Button("Previous");
    private final Button btnLast= new Button("Last");
    private Label id=new Label("ID: ");
    private final TextField idDisplay=new TextField();
    private Label name=new Label("Name: ");
    private final TextField nameDisplay=new TextField();
    private Label city=new Label("City: ");
    private final TextField cityDisplay=new TextField();
    private Label position=new Label("Position: ");
    private final TextField positionDisplay=new TextField();
    //modification region
    private Label title2=new Label("\nRecord Modification\n");
    private final Button btnAdd= new Button("Add");
    private final Button btnAddUpdate= new Button("AddUpdate");
    private final Button btnModify= new Button("Modify");
    private final Button btnModifyUpdate= new Button("ModifyUpdate");
    private final Button btnDelete= new Button("Delete");
    private final TextArea txtDisplay=new TextArea();     
    private final Button btnSearch= new Button("Search");
    public static ArrayList<Employee> employeeList=new ArrayList<Employee>();
    public static String[] oldInfo=new String[4];
    public static String[] newInfo=new String[4];

    @Override
    public void start(Stage stage) throws Exception {
        employeeList.clear();
        employeeList.addAll(EmployeeFile.getEmployeeList());
        VBox VBoxPane=getVBoxPane();
        Scene scene=new Scene(VBoxPane,900,600);
        btnFirst.setOnAction(new setFirst());
        btnLast.setOnAction(new setLast());
        btnNext.setOnAction(new setNext());
        btnPrevious.setOnAction(new setPrevious());
        btnSearch.setOnAction(new setSearch());    
        getRecord();
        btnAdd.setOnAction(new setAdd());
        btnAddUpdate.setOnAction(new setAddUpdate());
        btnModify.setOnAction(new setModify());
        btnModifyUpdate.setOnAction(new setModifyUpdate());
        btnDelete.setOnAction(new setDelete());
        stage.setOnCloseRequest(new EndProgram());
        stage.setScene(scene);
        scene.getStylesheets().add("/css/TheStyle.css");
        title1.getStyleClass().add("title");
        title2.getStyleClass().add("title");
        stage.setX(50);
        stage.setY(20);
        stage.show();
    }
// layout the pane start    
    private VBox part1(){
        HBox buttonGether=new HBox(btnFirst,btnNext,btnPrevious,btnLast);
        buttonGether.setSpacing(10);
        HBox inforGether=new HBox(id,idDisplay, name, nameDisplay, city, cityDisplay, position, positionDisplay);
        inforGether.setSpacing(10);
        VBox basic=new VBox(title1,buttonGether,inforGether);
        basic.setSpacing(10);
        return basic;
    }
    private VBox part2(){
        HBox buttonAddGether=new HBox(btnAdd,btnAddUpdate);
        btnAddUpdate.setDisable(true);        
        HBox buttonModifyGether=new HBox(btnModify,btnModifyUpdate);
        btnModifyUpdate.setDisable(true);
        HBox buttonDeleteGether=new HBox(btnDelete);        
        buttonAddGether.setSpacing(10);
        buttonModifyGether.setSpacing(10);
        buttonDeleteGether.setSpacing(10);        
        VBox basic=new VBox(title2,buttonAddGether,buttonModifyGether,buttonDeleteGether,txtDisplay,btnSearch);
        basic.setSpacing(10);
        return basic;
    }
    
    //layout the pane
    private VBox getVBoxPane(){
        VBox pane=new VBox();        
        pane.getChildren().addAll(part1(),part2());   
        return pane;}
// layout the pane end
    
    /*part1 start basic navigation button control */    
//    First button
    public class setFirst implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            setPart1Display(1);
            }        
    }
    //last button
    public class setLast implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            setPart1Display(2);
            }        
    }
    //Next button
    public class setNext implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            setPart1Display(3);
            }        
    }
    //Previous button
    public class setPrevious implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            setPart1Display(4);
            }        
    }
    //basic navigation textfield display 
    private void setPart1Display(int num){
       try{
            int i=0;
            if(idDisplay.getText()!=""){
                int id= Integer.parseInt(idDisplay.getText());                                   
            for(int j=0;j<employeeList.size();j++){
            int idArrayList=employeeList.get(j).getID();
            if(idArrayList==id){
               i=j;                
                break;
            }
            }            
            }
            if (num==1){
               i=0; 
            }else if(num==2){
                i=employeeList.size()-1;
            }
            else if(num==3){  
                if(i<=(employeeList.size()-2)&&i>=0){
                   i++;                          
            }
            }else if(num==4){
               if(i<=employeeList.size()-1&&i>=1){                   
                   i--; 
                } 
            }
            String idString=Integer.toString(employeeList.get(i).getID());
            String name=employeeList.get(i).getName();
            String city=employeeList.get(i).getCity();
            String position=employeeList.get(i).getPosition();            
            idDisplay.setText(idString);
            nameDisplay.setText(name);
            cityDisplay.setText(city);
            positionDisplay.setText(position);
                        
       }catch(NumberFormatException e){
            alertID(e);
       }
       catch(Exception e){
           System.out.println(e);
       }        
    }
//end of part1
    
//part2 modify start
//small function to reduce duplicate coding start
    //old information save
    private void saveOldInfo(){        
        oldInfo[0]=idDisplay.getText();
        oldInfo[1]=nameDisplay.getText();
        oldInfo[2]=cityDisplay.getText();
        oldInfo[3]=positionDisplay.getText();                
    }
    //new information save  
    private void saveNewInfo(){        
        newInfo[0]=idDisplay.getText();
        newInfo[1]=nameDisplay.getText();
        newInfo[2]=cityDisplay.getText();
        newInfo[3]=positionDisplay.getText(); 
        
    }
    //reload original cut data in the datafield    
    private void originalDisplay(){
        idDisplay.setText(oldInfo[0]);
        nameDisplay.setText(oldInfo[1]);
        cityDisplay.setText(oldInfo[2]);
        positionDisplay.setText(oldInfo[3]);
    }
    //show the first Element when wrong operation or after delete the last one
    private void FirstEleArrayList(){
        idDisplay.setText(Integer.toString(employeeList.get(0).getID()));
        nameDisplay.setText(employeeList.get(0).getName());
        cityDisplay.setText(employeeList.get(0).getCity());
        positionDisplay.setText(employeeList.get(0).getPosition());
    }
    
    //record list
    private void getRecord(){
        String s="Record list \n";
        for(int i=0;i<employeeList.size();i++){
            
            s+=employeeList.get(i).getID() +", "+employeeList.get(i).getName()+", "+employeeList.get(i).getCity()+", "+employeeList.get(i).getPosition()+"\n";
        }
        txtDisplay.setText(s);
    }
    //add function start
    public class setAdd implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            saveOldInfo();            
            idDisplay.clear();
            nameDisplay.clear();
            cityDisplay.clear();
            positionDisplay.clear();
            btnAddUpdate.setDisable(false);
            btnAdd.setDisable(true);
        }            
    }
    public class setAddUpdate implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
        try{
            boolean flag=true;
            int ID=Integer.parseInt(idDisplay.getText());     
            saveNewInfo();
            for(int j=0;j<employeeList.size();j++){
            int idArrayList=employeeList.get(j).getID();                    
            if(Integer.parseInt(newInfo[0])==idArrayList){
                Alert newInfoAddAlert=new Alert(Alert.AlertType.INFORMATION);
                newInfoAddAlert.setHeaderText("ID must be unique.");
                newInfoAddAlert.show();
                originalDisplay();
                flag=false;
                btnAddUpdate.setDisable(true);
                btnAdd.setDisable(false);
                break;
            }
        }
            if(flag){
                if(!newInfo[0].equals("")&&!newInfo[1].equals("")&&!newInfo[2].equals("")&&!newInfo[3].equals("")){           
                btnAdd.setDisable(true);
                btnAddUpdate.setDisable(true);
                String message;
                Alert dlgConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = dlgConfirmation.showAndWait();
                if(result.get() == ButtonType.OK)
                {
                    message = "OK Clicked";
                    EmployeeFile.modifywriting("Employee.dat",oldInfo, newInfo, "add");                     
                    btnAdd.setDisable(false);
                    btnAddUpdate.setDisable(true);
                
                //refresh employeeList data
                employeeList.clear();
                employeeList.addAll(EmployeeFile.getEmployeeList());
                getRecord();
                }
                else
                {
                    message = "Cancel Clicked";                    
                    btnAdd.setDisable(false);
                    btnAddUpdate.setDisable(true);
                    originalDisplay();
                }
                 Alert dlgInfo=new Alert(Alert.AlertType.INFORMATION);
                    dlgInfo.setHeaderText(message);
                    dlgInfo.show();
                }
            }
            
        }
        catch(NumberFormatException e){
                alertID(e);
                originalDisplay();
                btnAdd.setDisable(false);
                btnAddUpdate.setDisable(true);
            }
        catch(Exception e){
                System.out.println(e);
            }
            }        
    }
    //add function end
        //modify
    public class setModify implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            boolean flag=true;
            saveOldInfo();
            for(int j=0;j<employeeList.size();j++){
                    int idArrayList=employeeList.get(j).getID();                            
                    if(Integer.parseInt(oldInfo[0])==idArrayList){
                        oldInfo[0]=Integer.toString(employeeList.get(j).getID());
                        oldInfo[1]=employeeList.get(j).getName();
                        oldInfo[2]=employeeList.get(j).getCity();
                        oldInfo[3]=employeeList.get(j).getPosition(); 
                        flag=false;
                        break;
                    }
            } 
            
            if(flag){
                        Alert infoModify=new Alert(Alert.AlertType.INFORMATION);
                        infoModify.setHeaderText("Wrong step, click modify button first, then Edit");
                        infoModify.show();
                        btnModifyUpdate.setDisable(true);
                        btnModify.setDisable(false);
                        FirstEleArrayList();

                    }else{
                        btnModifyUpdate.setDisable(false);
                        btnModify.setDisable(true);
            }
            
                   
    }}
    public class setModifyUpdate implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            try{
            int IDnew=Integer.parseInt(idDisplay.getText());
            saveNewInfo();
                if(!newInfo[0].equals("")&&!newInfo[1].equals("")&&!newInfo[2].equals("")&&!newInfo[3].equals("")){                    
                    btnModify.setDisable(true);
                    btnModifyUpdate.setDisable(true);
                    String message;
                    Alert dlgConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    Optional<ButtonType> result=dlgConfirmation.showAndWait();
                    if(result.get()==ButtonType.OK){
                        message = "OK Clicked";
                        EmployeeFile.modifywriting("Employee.dat",oldInfo, newInfo, "modify"); 
                        btnModify.setDisable(false);
                        btnModifyUpdate.setDisable(true); 

                        //refresh employeeList data
                        employeeList.clear();
                        employeeList.addAll(EmployeeFile.getEmployeeList());
                        getRecord(); 
                        //end   
                    }else{
                        message = "Cancel Clicked";                        
                        btnModify.setDisable(false);
                        btnModifyUpdate.setDisable(true);
                        originalDisplay();
                    }
                    Alert dlgInfo=new Alert(Alert.AlertType.INFORMATION);
                    dlgInfo.setHeaderText(message);
                    dlgInfo.show();
                }                           
            }catch(NumberFormatException e){
                alertID(e);
                originalDisplay();
                btnModify.setDisable(false);
                btnModifyUpdate.setDisable(true);
            }
            
            catch(Exception e){
                System.out.println(e);
            }             
    }}
    //end of modify function
    //Delete function
    public class setDelete implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            saveOldInfo();
            btnDelete.setDisable(true);
            String message;
            Alert dlgConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result =
            dlgConfirmation.showAndWait();
            if(result.get() == ButtonType.OK)
            {message = "OK Clicked";
                EmployeeFile.modifywriting("Employee.dat",oldInfo, newInfo, "delete");                
                btnDelete.setDisable(false);
                int id=Integer.parseInt(oldInfo[0]);
                for(int j=0;j<employeeList.size();j++){
                    int idArrayList=employeeList.get(j).getID();
                    if((id==idArrayList)&&(j==employeeList.size()-1)){
                        FirstEleArrayList();
                        break;
                    }else if(id==idArrayList){
                        idDisplay.setText(Integer.toString(employeeList.get(j+1).getID()));
                        nameDisplay.setText(employeeList.get(j+1).getName());
                        cityDisplay.setText(employeeList.get(j+1).getCity());
                        positionDisplay.setText(employeeList.get(j+1).getPosition());
                        break;
                    }
                }
                               
                //refresh employeeList data
                employeeList.clear();
                employeeList.addAll(EmployeeFile.getEmployeeList());
                getRecord(); 
        
            }
            else
            {message = "Cancel Clicked";            
            btnDelete.setDisable(false);
            originalDisplay();
            }
            Alert dlgInfo=new Alert(Alert.AlertType.INFORMATION);
            dlgInfo.setHeaderText(message);
            dlgInfo.show();
            
            }        
    }
//end of delete    
//end of part2

//part3 start
//search
    public class setSearch implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent t) {
            SearchStage scene1=new SearchStage();
            scene1.show();
        }        
    }

//part3 end
    public static void main(String[] args) {
        try{File one= new File("Employee.dat");
        if(!one.exists()){
            one.createNewFile();
        }}
        catch(Exception e){
            System.out.println(e);
        }
        Application.launch(args);
    }
//Alert message for ID
    public static void alertID(NumberFormatException e){
        System.out.println(e);
        System.out.println("ID should be an integer.");
        Alert dlgErr=new Alert (Alert.AlertType.ERROR);
        dlgErr.setHeaderText("ID should be an integer.");
        dlgErr.setContentText("Do it again. ");
//        dlgErr.setContentText(e.toString());
        dlgErr.show();
    }
    
// the Endprogram save data
public class EndProgram implements EventHandler<WindowEvent>{

        @Override
        public void handle(WindowEvent t) {
            try {
                //refresh employeeList data
                employeeList.clear();
                employeeList.addAll(EmployeeFile.getEmployeeList());
                getRecord(); 
                //end 
                Alert dlgInfo=new Alert(Alert.AlertType.INFORMATION);
                dlgInfo.setHeaderText("program ended - data saved");
                dlgInfo.show();
            }catch(Exception e) {
                System.out.println(e);
                Alert dlgErr=new Alert (Alert.AlertType.ERROR);
                 dlgErr.setHeaderText("Data not saved - program ended");
                 dlgErr.setContentText(e.toString());
                 dlgErr.show();
            }
        }
        
    }
}