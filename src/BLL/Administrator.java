package BLL;

import javafx.beans.property.SimpleStringProperty;

public class Administrator {
    /*
     * Purpose: Stores the components of a user login
     * Author: Brent Ward
     * Module:
     * Date May 24, 2019
     * */

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;

    private boolean loginStatus;

    //Not storing username/password
    public Administrator(String firstName, String lastName){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        loginStatus = false;
    }

    //getters
    public String getFirstName(){ return firstName.get(); }
    public String getLastName(){ return lastName.get(); }

}
