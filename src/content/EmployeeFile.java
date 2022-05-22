/**
My Name: Jingxin Jiang
My Student Number: 991657051
Final Project
Date: 2022.04.01
Modify: 2022.04.11 8:45pm
*/
package content;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import static salesfx.SalesFX.newInfo;
import static salesfx.SalesFX.oldInfo;


/**
 *
 * @author seanz
 */
public class EmployeeFile {
    private static ArrayList<Employee> employeeList= new ArrayList<Employee>();

    public static ArrayList<Employee> getEmployeeList() {
        try{
         employeeList.clear();   
         reading("Employee.dat");   
        }catch(Exception e){
            System.out.println(e);
        }
        
        return employeeList;
    }
    
    private static ArrayList<Employee> reading(String one) throws FileNotFoundException, IOException{
        FileReader fr=new FileReader(one);
        BufferedReader br=new BufferedReader(fr);
        String line=br.readLine(); 
        while(line!=null){
            
            StringTokenizer word=new StringTokenizer(line,","); 
            int ID=Integer.parseInt(word.nextToken());
            String Name=word.nextToken();
            String City=word.nextToken();
            String Position=word.nextToken();
            Employee employee1=new Employee(ID, Name, City,Position);
            employeeList.add(employee1);
            line=br.readLine();
        }
        br.close();
        fr.close();
        return employeeList;
    }

    //modify content with origian employee file content write in tem.dat create new modify file. 
    //old Write to temp.dat, new write to temp, then old write to temp, realize add, modity or delete
    public static void modifywriting(String one,String[] oldInfo,String[] newInfo, String flag){
        try{
         
            File oldFile=new File(one); 
            File newFile=new File("temp.dat");
            FileWriter fw=new FileWriter(newFile);
            BufferedWriter bw= new BufferedWriter(fw);
            
            FileReader fr=new FileReader(oldFile);
            BufferedReader br=new BufferedReader(fr);            
            String line=br.readLine();           
            while(line!=null){
                StringTokenizer word=new StringTokenizer(line,",");
                if(flag.equalsIgnoreCase("modify")){
                    if(!(word.nextToken().equals(oldInfo[0]))){
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                    }else{
                        bw.write(newInfo[0]+","+newInfo[1]+","+newInfo[2]+","+newInfo[3]);
                        bw.newLine();
                    }
                }else if(flag.equalsIgnoreCase("delete")) {
                    if(!(word.nextToken().equals(oldInfo[0]))){
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                    }
                }else if(flag.equalsIgnoreCase("add")){//add at defined location adjust part else if add
                    
                    if(!(word.nextToken().equals(oldInfo[0]))){
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                    }else{
                        bw.write(line);
                        bw.newLine();
                        bw.flush();
                        bw.write(newInfo[0]+","+newInfo[1]+","+newInfo[2]+","+newInfo[3]);
                        bw.newLine();
                   }
                }    
                
                line=br.readLine();
                
            }
            //no defined position add at the end of file
            if(oldInfo[0].equals("")&&oldInfo[1].equals("")&&oldInfo[2].equals("")&&oldInfo[3].equals("")&&flag.equalsIgnoreCase("add")){
                bw.write(newInfo[0]+","+newInfo[1]+","+newInfo[2]+","+newInfo[3]);
                bw.newLine();    
                }
            bw.close();
            fw.close();
            remodifywriting(newFile, oldFile);
            
            //refresh employeeList data
            employeeList.clear();
            getEmployeeList();
            //end
            
        }catch(IOException e){
        System.out.println(e);
        }
        catch(Exception e){
        System.out.println(e);
        }
            
            
    };
    
    //temp.dat content write back to Employee.dat
    public static void remodifywriting(File oldFile, File newFile){
        try{
           
            FileWriter fw=new FileWriter(newFile);
            BufferedWriter bw= new BufferedWriter(fw);
            
            FileReader fr=new FileReader(oldFile);
            BufferedReader br=new BufferedReader(fr);            
            String line=br.readLine();
            //System.out.println("line value check "+line);
            
            while(line!=null){
                StringTokenizer word=new StringTokenizer(line,",");
                bw.write(line);
                bw.newLine();
                bw.flush();                
                line=br.readLine();                
            }
            bw.close();
            fw.close();
            
            //refresh employeeList data   //can delete I feel try later
            employeeList.clear();
            getEmployeeList();
            //end            
        }catch(IOException e){
        System.out.println(e);
        }
        catch(Exception e){
        System.out.println(e);
        }                        
    };  
}
