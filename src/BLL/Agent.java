package BLL;

import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("unused")
public class Agent {
    //properties
    private int id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty middleInitial;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private SimpleStringProperty position;
    private int agency;

    //constructor
    public Agent(int id, SimpleStringProperty fName, SimpleStringProperty mInitial, SimpleStringProperty lName,
                 SimpleStringProperty phone, SimpleStringProperty email, SimpleStringProperty position, int agency) {
        this.id = id;
        this.firstName = fName;
        this.middleInitial = mInitial;
        this.lastName = lName;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.agency = agency;
    }

    //Get methods
    public int getID(){ return id; }
    public String getFirstName(){ return firstName.get(); }
    public String getMiddleInitial(){ return middleInitial.get(); }
    public String getLastName(){ return lastName.get(); }
    public String getPhone(){ return phone.get(); }
    public String getEmail(){ return email.get(); }
    public String getPosition(){ return position.get(); }

    //Set methods -- ID is auto set by database
    public void setFirstName(String fname){ firstName.set(fname); }
    public void setMiddleInitial(String mInit){ middleInitial.set(mInit); }
    public void setLastName(String lname){ lastName.set(lname); }
    public void setPhone(String phoneNew){ phone.set(phoneNew); }
    public void setEmail(String emailNew){ email.set(emailNew); }
    public void setPosition(String posit){ position.set(posit); }
    public void setAgency(int agcy){ agency = agcy; }
}
