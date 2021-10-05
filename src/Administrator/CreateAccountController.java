/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrator;

import atm.Customer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CreateAccountController implements Initializable {

    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private DatePicker dOB;
    @FXML
    private TextField peradd;
    @FXML
    private TextField preadd;
    @FXML
    private RadioButton rdM;
    @FXML
    private RadioButton rdF;
    @FXML
    private RadioButton rdO;
    @FXML
    private TextField em;
    @FXML
    private TextField zc;
    @FXML
    private ComboBox combo;
    @FXML
    private Label id;

    ObservableList<String> cityList = FXCollections.observableArrayList("Dhaka", "Chittagong", "Rajshahi", "Khulna", "Sylhet");
    //long iD = 51280000;// + Integer.parseInt(zc.getText());
    long iD;

    private long generateCardNo(long iD) {
        for (int i = 0; i < 8; i++) {
            iD *= 10;
            iD += (new Random()).nextInt(9);
        }
        return iD;
    }

    @FXML
    private void showOnLabel(KeyEvent e) {
        if (zc.getText().length() == 4) {
            iD = 51280000 + Integer.parseInt(zc.getText());
            iD = generateCardNo(iD);
            String show = "";
            for (int i = 0; i < Long.toString(iD).length(); i++) {
                if (i % 4 == 0 && i != 0) {
                    show += "-";
                }
                show += Long.toString(iD).charAt(i);
            }
            id.setText(show);
        }
    }

    @FXML
    private void refreshCardNo(ActionEvent e) {
        if (zc.getText() == null) {
            iD = 51280000 + 0;
        } else {
            iD = 51280000 + Integer.parseInt(zc.getText());
        }
        iD = generateCardNo(iD);
        String show = "";
        for (int i = 0; i < Long.toString(iD).length(); i++) {
            if (i % 4 == 0 && i != 0) {
                show += "-";
            }
            show += Long.toString(iD).charAt(i);
        }
        id.setText(show);
    }

    @FXML
    private void setToFile() {
        try {

            String fName = fname.getText();
            String lName = lname.getText();
            String dob = dOB.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println(dob);
            System.out.println(dob);
            String perAdd = peradd.getText();
            String preAdd = preadd.getText();
            String eMail = em.getText();

            String city = combo.getSelectionModel().getSelectedItem().toString();
            int zip = Integer.parseInt(zc.getText());
            char gender = 'N';
            if (rdM.isSelected()) {
                gender = 'M';
            } else if (rdF.isSelected()) {
                gender = 'F';
            } else if (rdO.isSelected()) {
                gender = 'O';
            }
            //iD = generateCardNo(iD, zip);
            //System.out.println("Card No.: " + iD);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss a");
            LocalDateTime now = LocalDateTime.now();
            String createDate = dtf.format(now);
            Customer c = new Customer(iD, fName, lName, dob, eMail, perAdd, preAdd, city, zip, gender, createDate);
            File file = new File("Customer.bin");
            //System.out.println(iD);
            if (!file.exists()) {
                try {
                    FileOutputStream fos = new FileOutputStream("Customer.bin");
                    try {
                        ObjectOutputStream ous = new ObjectOutputStream(fos);
                        ous.writeObject(c);
                        ous.flush();
                        ous.close();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Account has been created successfully!");
                        alert.showAndWait();
                    } catch (IOException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Unable to save to binary file!");
                        alert.showAndWait();
                    }
                } catch (FileNotFoundException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Unable to save to binary file, file not found!");
                    alert.showAndWait();
                }
            } else {
                try {
                    FileOutputStream fos = new FileOutputStream("Customer.bin", true);
                    try {
                        ObjectOutputStream ous = new AppendableObjectOutputStream(fos);
                        ous.writeObject(c);
                        ous.flush();
                        ous.close();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Account has been created successfully!");
                        alert.showAndWait();
                    } catch (IOException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Unable to save to binary file!");
                        alert.showAndWait();
                    }
                } catch (FileNotFoundException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Unable to save to binary file, file not found!");
                    alert.showAndWait();
                }

            }
        } catch (RuntimeException e) {
            System.out.println("asdasdasd");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill up all the fields!");
            alert.showAndWait();
        }

    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent createAccount = FXMLLoader.load(getClass().getResource("/Administrator/main.fxml"));
        Scene createAccountScene = new Scene(createAccount);
        Stage createAccountWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createAccountWindow.setScene(createAccountScene);
        createAccountWindow.show();
    }

    class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo.setValue("Dhaka");
        combo.setItems(cityList);

    }

}
