/**
My Name: Jingxin Jiang
My Student Number: 991657051
Final Project
Date: 2022.04.01
*/
package content;

public class Employee {
    private int ID;
    private String Name;
    private String City;
    private String Position;
    public Employee(int ID, String Name, String City,String Position){
        this.ID=ID;
        this.Name=Name;
        this.City=City;
        this.Position=Position;
    } 

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getCity() {
        return City;
    }

    public String getPosition() {
        return Position;
    }
    
}
