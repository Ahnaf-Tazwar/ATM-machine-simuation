package Customer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import atm.Customer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ChangePINController implements Initializable {

    Customer c = null;

    public void setInfo(Customer cc) {
        c = cc;
    }

    @FXML
    private PasswordField pw;
    @FXML
    private PasswordField cpw;

    @FXML
    private void confirmButtonOnClick(ActionEvent event) throws FileNotFoundException, IOException {
        try {
            int password = Integer.parseInt(pw.getText());
            int confirmPassword = Integer.parseInt(cpw.getText());
            if (password == confirmPassword && pw.getText().length() >= 4) {
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
                for (Customer cIN : list) {
                    if (cIN.getiD() == c.getiD()) {
                        cIN.setpIN(password);
                        c = cIN;
                        //cIN.setBalance(money);
                    }
                    ous.writeObject(cIN);
                }
                ous.flush();
                ous.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Password has been successfully!");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Customer/main.fxml"));
                Parent customer = loader.load();

                Scene customerScene = new Scene(customer);

                MainController controller = loader.getController();
                controller.setInfo(c);

                Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                customerWindow.setScene(customerScene);
                customerWindow.show();
            } else if (pw.getText().length() < 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Password digit is less than 4!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Password does not match!");
                alert.showAndWait();
            }
            /*FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Customer/main.fxml"));
            Parent customer = loader.load();

            Scene customerScene = new Scene(customer);

            MainController controller = loader.getController();
            controller.setInfo(c);

            Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            customerWindow.setScene(customerScene);
            customerWindow.show();*/
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

