package atm;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{

    private long iD;
    private String fName, lName, dob, email, perAddress, preAddress, city;
    private int pIN, zipCode;
    private float balance;
    private char gender;
    private ArrayList<String> logs;
    private ArrayList<String> amount;
    
    public Customer(long iD, String fName, String lName, String dob, String email, String perAddress, String preAddress,
            String city, int zipCode, char gender, String date) {

        this.iD = iD;
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.email = email;
        this.perAddress = perAddress;
        this.preAddress = preAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.gender = gender;
        this.pIN = 1234;
        this.balance = 500.0f;
        logs = new ArrayList<String>();
        amount = new ArrayList<String>();
        
        logs.add(date);
    }

    public char getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setpIN(int pIN) {
        this.pIN = pIN;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    public void addLog(String date, String money){
        logs.add(date);
        amount.add(money);
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    public ArrayList<String> getAmount() {
        return amount;
    }

    public long getiD() {
        return iD;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPerAddress() {
        return perAddress;
    }

    public String getPreAddress() {
        return preAddress;
    }

    public String getCity() {
        return city;
    }

    public int getpIN() {
        return pIN;
    }

    public int getZipCode() {
        return zipCode;
    }

    public float getBalance() {
        return balance;
    }
    

}
