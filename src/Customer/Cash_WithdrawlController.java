/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import atm.Customer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Cash_WithdrawlController implements Initializable {

    @FXML
    private TextField text;

    Customer c = null;

    public void sendInfo(Customer c1) {
        c = c1;
    }

    @FXML
    private void withdrawMoney(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            float money = Float.parseFloat(text.getText()) * 1.0f;
            if (c.getBalance() > money) {
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
                String createDate = "W";
                createDate += dtf.format(now);
                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to withdraw?");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(yes, no);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {
                    System.out.println("ERROR 404!");
                } else if (option.get() == yes) {
                    for (Customer cIN : list) {
                    if (cIN.getiD() == c.getiD()) {
                        cIN.setBalance(c.getBalance() - money - 2.5f);
                        cIN.addLog(createDate, Float.toString(money));
                        c = cIN;
                        //cIN.setBalance(money);
                    }
                    ous.writeObject(cIN);
                }
                ous.flush();
                ous.close();
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText(null);
                alert2.setContentText("Amount has been successfully withdrawn.\nPlease collect your money!");
                alert2.showAndWait();
                }
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Customer/main.fxml"));
                Parent customer = loader.load();

                Scene customerScene = new Scene(customer);

                MainController controller = loader.getController();
                controller.setInfo(c);

                Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                customerWindow.setScene(customerScene);
                customerWindow.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Insufficient Fund!");
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid Input!");
            alert.showAndWait();
        }
    }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}


