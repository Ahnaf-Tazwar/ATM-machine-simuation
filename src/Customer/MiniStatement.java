/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

/**
 *
 * @author AhNAF TAzWAR
 */
public class MiniStatement {
    String time, date, type, amount;

    public MiniStatement(String amount, String time, String date, String type) {
        this.amount = amount;
        this.time = time;
        this.date = date;
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }
    
    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
    
    
}
