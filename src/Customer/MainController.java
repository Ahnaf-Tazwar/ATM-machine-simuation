/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import atm.Customer;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MainController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Label label2;

    Customer c2 = null;

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Do you want to Log Out?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {
            System.out.println("ERROR 404!");
        } else if (option.get() == yes) {
            Parent back_to_ATM = FXMLLoader.load(getClass().getResource("/atm/ATM_Main.fxml"));
            Scene back_to_ATMScene = new Scene(back_to_ATM);
            Stage back_to_ATMWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            back_to_ATMWindow.setScene(back_to_ATMScene);
            back_to_ATMWindow.show();
        }

        //PersonViewSceneController controller = loader.getController();
    }

    public void setInfo(Customer c1) {
        //label1.setText("Name: " + c1.getfName() + " " + c1.getlName());
        //label2.setText("Balance: " + c1.getBalance() + "Tk");
        c2 = c1;

    }

    
    @FXML
    private void seeInfoButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Customer/SeeInfo.fxml"));
        Parent customer = loader.load();

        Scene customerScene = new Scene(customer);

        SeeInfoController controller = loader.getController();
        controller.sendInfo(c2);

        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }
    
    @FXML
    private void cashWithdrawlButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Customer/Cash_Withdrawl.fxml"));
        Parent customer = loader.load();

        Scene customerScene = new Scene(customer);

        Cash_WithdrawlController controller = loader.getController();
        controller.sendInfo(c2);

        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }

    @FXML
    private void banlanceEnquiryButtonOnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Your account balance is: " + c2.getBalance() + "Tk");
        alert.showAndWait();
        //PersonViewSceneController controller = loader.getController();
    }

    @FXML
    private void depositlButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Customer/Deposit.fxml"));
        Parent customer = loader.load();

        Scene customerScene = new Scene(customer);

        DepositController controller = loader.getController();
        controller.setInfo(c2);

        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }

    @FXML
    private void fastCashButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Customer/FastCash.fxml"));
        Parent customer = loader.load();

        Scene customerScene = new Scene(customer);

        FastCashController controller = loader.getController();
        controller.setInfo(c2);

        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }

    @FXML
    private void changePINButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Customer/ChangePIN.fxml"));
        Parent customer = loader.load();

        Scene customerScene = new Scene(customer);

        ChangePINController controller = loader.getController();
        controller.setInfo(c2);

        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }
    
    @FXML
    private void miniStatementButtonOnClick(ActionEvent event)throws IOException{
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Customer/MiniStatement.fxml"));
        Parent customer = loader.load();

        Scene customerScene = new Scene(customer);

        MiniStatementController controller = loader.getController();
        controller.sendInfo(c2);

        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //label2.setText("First Name: " + c2.getfName() + "\n" + "Last: " + c2.getlName());
    }

}

