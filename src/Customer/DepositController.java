/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import atm.Customer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DepositController implements Initializable {

    Customer c = null;

    public void setInfo(Customer c1) {
        c = c1;
    }

    @FXML
    TextField text;

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Customer/main.fxml"));
        Parent customer = loader.load();

        Scene customerScene = new Scene(customer);

        MainController controller = loader.getController();
        controller.setInfo(c);

        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }

    @FXML
    private void depositButtonOnClick() throws IOException {
        try {
            float money = Float.parseFloat(text.getText()) * 1.0f;
            ArrayList<Customer> list = new ArrayList<Customer>();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customer.bin"));
            Customer ct = null;
            try {
                while ((ct = (Customer) ois.readObject()) != null) {
                    list.add(ct);
                }
            } catch (Exception e) {
                ois.close();
            }
            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("Customer.bin"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss a");
            LocalDateTime now = LocalDateTime.now();
            String createDate = "D";
            createDate += dtf.format(now);
            for (Customer cIN : list) {
                if (cIN.getiD() == c.getiD()) {
                    cIN.setBalance(c.getBalance() + money);
                    c = cIN;
                    cIN.addLog(createDate, Float.toString(money));
                }
                ous.writeObject(cIN);
            }
            ous.flush();
            ous.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Amount has been successfully deposited!");
            alert.showAndWait();

        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid Input!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

