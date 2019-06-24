package BLL;

import javafx.beans.property.SimpleStringProperty;

public class AgentSales {


    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private int sales;

    public AgentSales(String firstName, String lastName, int sales){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.sales = sales;
    }

    public String getFirstName() { return firstName.get(); }
    public String getLastName() { return lastName.get(); }
    public int getSales() { return sales; }

    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get();
    }
}
