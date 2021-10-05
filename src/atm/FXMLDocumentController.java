/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import Customer.MainController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
//import javafx.fxml.Initializable;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField id1;
    @FXML
    private TextField id2;
    @FXML
    private TextField id3;
    @FXML
    private TextField id4;
    @FXML
    private PasswordField pf;
    @FXML
    private Label label2;
    @FXML
    private Button b;

    @FXML
    private void logInButtonOnClick(ActionEvent event) throws IOException {
        try {
            String check = id1.getText() + id2.getText() + id3.getText() + id4.getText();

            long cID = Long.parseLong(check);
            cID = cID * 1l;
            int cPIN = Integer.parseInt(pf.getText());
            boolean autherized = false;
            File file = new File("Customer.bin");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customer.bin"));
            Customer c = null;
            try {
                while ((c = (Customer) ois.readObject()) != null) {
                    if (c.getiD() == cID && c.getpIN() == cPIN) {
                        autherized = true;
                        break;
                    }
                    else if(c.getiD() == cID && c.getpIN() != cPIN)
                        break;
                    //else if(c.getiD() != cID && c.getpIN() == cPIN)
                      //  break;
                }
            } catch (Exception e) {
                ois.close();
            }
            if (check.isEmpty() || pf.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please enter the necessary details!");
                alert.showAndWait();
            } else {
                if (check.length() == 16) {
                    if (autherized) {
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
                        if (c.getiD() == cID) {
                            System.out.println(cID);
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("PIN is not valid!");
                            alert.showAndWait();
                        } else if (c.getpIN() == cPIN) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("Card No. is not valid!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("Both Card No. and PIN are not valid!");
                            alert.showAndWait();
                        }
                    }
                } else if (check.length() == 4) {
                    if (Integer.parseInt(pf.getText()) == 321) {
                        Parent customer = FXMLLoader.load(getClass().getResource("/Administrator/main.fxml"));
                        Scene customerScene = new Scene(customer);
                        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        customerWindow.setScene(customerScene);
                        customerWindow.show();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter correct username and PIN!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Card Number!");
                    alert.showAndWait();
                }
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("You must enter both Card No. and PIN!");
            alert.showAndWait();
        }
    }

    @FXML
    private void id1Focus(KeyEvent e) {
        if (id1.getText().length() == 4) {
            id2.requestFocus();
        }
    }

    @FXML
    private void id2Focus(KeyEvent e) {
        if (id2.getText().length() == 4) {
            id3.requestFocus();
        }
    }

    @FXML
    private void id3Focus(KeyEvent e) {
        if (id3.getText().length() == 4) {
            id4.requestFocus();
        }
    }

    @FXML
    private void id4Focus(KeyEvent e) {
        if (id4.getText().length() >= 4) {
            pf.requestFocus();
        }
    }

    @FXML
    private void logInFocus(KeyEvent e) {
        if (pf.getText().length() == 4) {
            b.requestFocus();
        }
    }

    @FXML
    private void testOnClick(ActionEvent event) throws IOException {
        Parent customer = FXMLLoader.load(getClass().getResource("/Administrator/main.fxml"));
        Scene customerScene = new Scene(customer);
        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

