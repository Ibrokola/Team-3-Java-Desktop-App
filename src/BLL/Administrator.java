package BLL;

import javafx.beans.property.SimpleStringProperty;

public class Administrator {
    /*
     * Purpose: Stores the components of a user login
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date May 24, 2019
     * */

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty colorMode;
    private SimpleStringProperty profilePicture;

    //Not storing username/password
    public Administrator(String firstName, String lastName){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }
    public Administrator(String firstName, String lastName, String colorMode, String profilePicture){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.colorMode = new SimpleStringProperty(colorMode);
        this.profilePicture = new SimpleStringProperty(profilePicture);
    }


    //getters
    public String getFirstName(){ return firstName.get(); }
    public String getLastName(){ return lastName.get(); }
    public String getColorMode() { return colorMode.get(); }
    public String getProfilePicture() { return profilePicture.get(); }

    //setters
    public void setColorMode(String mode) { colorMode.set(mode); }
    public void setProfilePicture(String path) { profilePicture.set(path); }

}
