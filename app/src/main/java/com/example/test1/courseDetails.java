package com.example.test1;



//Class containing the course details
public class courseDetails {
    String cName;
    String cHours;
    String cPrice;

    //initialisation
    public courseDetails(String cName, String cHours, String cPrice) {
        this.cName = cName;
        this.cHours = cHours;
        this.cPrice = cPrice;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcHours() {
        return cHours;
    }

    public void setcHours(String cHours) {
        this.cHours = cHours;
    }

    public String getcPrice() {
        return cPrice;
    }

    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }
}
