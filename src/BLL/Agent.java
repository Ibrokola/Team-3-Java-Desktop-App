package BLL;

public class Agent {
    //properties
    private int id;
    private simpleStringProperty firstName;
    private simpleStringProperty middleInitial;
    private simpleStringProperty lastName;
    private simpleStringProperty phone;
    private simpleStringProperty email;
    private simpleStringProperty position;
    private int agency;

    //constructor
    public Agent(int id, simpleStringProperty fName, simpleStringProperty mInitial, simpleStringProperty lName,
                 simpleStringProperty phone, simpleStringProperty email, simpleStringProperty position, int agency) {
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
    public void setFirstName(string fname){ firstName.set(fname); }
    public void setMiddleInitial(string mInit){ middleInitial.set(mInit); }
    public void setLastName(string lname){ lastName.set(lname); }
    public void setPhone(string phoneNew){ phone.set(phoneNew); }
    public void setEmail(string emailNew){ email.set(emailNew); }
    public void setPosition(string posit){ position.set(posit); }
    public void setAgency(int agcy){ agency.set(agcy); }
}
