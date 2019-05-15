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
    public Agent(int id, String fName, String mInitial, String lName,
                 String phone, String email, String position, int agency) {
        this.id = id;
        this.firstName = new SimpleStringProperty(fName);
        this.middleInitial = new SimpleStringProperty(mInitial);
        this.lastName = new SimpleStringProperty(lName);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.position = new SimpleStringProperty(position);
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
    public int getAgency(){ return agency; }

    //Set methods -- ID is auto set by database
    public void setFirstName(String fname){ firstName.set(fname); }
    public void setMiddleInitial(String mInit){ middleInitial.set(mInit); }
    public void setLastName(String lname){ lastName.set(lname); }
    public void setPhone(String phoneNew){ phone.set(phoneNew); }
    public void setEmail(String emailNew){ email.set(emailNew); }
    public void setPosition(String posit){ position.set(posit); }
    public void setAgency(int agcy){ agency = agcy; }
}
