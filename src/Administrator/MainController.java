/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrator;

import atm.Customer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MainController implements Initializable {

    @FXML
    private Label show;
    @FXML
    private ListView<String> showNames;

    ObservableList<String> namesList = FXCollections.observableArrayList();

    @FXML
    private void createAccountButtonOnClick(ActionEvent event) throws IOException {
        Parent createAccount = FXMLLoader.load(getClass().getResource("/Administrator/Create account.fxml"));
        Scene createAccountScene = new Scene(createAccount);
        Stage createAccountWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createAccountWindow.setScene(createAccountScene);
        createAccountWindow.show();
    }

    @FXML
    private void showAccountButtonOnClick() {
        File file = new File("Customer.bin");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customer.bin"))) {
                Customer c = null;
                try {
                    while ((c = (Customer) ois.readObject()) != null) {
                        String t = Long.toString(c.getiD());
                        namesList.add("Card No.: " + t);
                        namesList.add("Name: " + c.getfName() + " " + c.getlName());
                        namesList.add("PIN: " + c.getpIN());
                        namesList.add("");
                    }
                } catch (Exception e) {
                    ois.close();
                }
                showNames.setItems(namesList);
                showNames.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            } catch (FileNotFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("FileNotFoundException!");
                alert.showAndWait();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("IOException!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No customer account has been added!");
            alert.showAndWait();
        }
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent createAccount = FXMLLoader.load(getClass().getResource("/atm/ATM_Main.fxml"));
        Scene createAccountScene = new Scene(createAccount);
        Stage createAccountWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createAccountWindow.setScene(createAccountScene);
        createAccountWindow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
