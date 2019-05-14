package BLL;

public class Agent {
    private int id;
    private simpleStringProperty firstName;
    private simpleStringProperty middleInitial;
    private simpleStringProperty lastName;
    private simpleStringProperty phone;
    private simpleStringProperty email;
    private simpleStringProperty position;
    private int agency;

    public Agent(int id, simpleStringProperty firstName, simpleStringProperty middleInitial, simpleStringProperty lastName,
                 simpleStringProperty phone, simpleStringProperty email, simpleStringProperty position, int agency) {
        id = id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
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


}
