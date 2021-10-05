/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import atm.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class MiniStatementController implements Initializable {

    @FXML
    private Label l;

    Customer c = null;

    public void sendInfo(Customer c1) {
        c = c1;
        setLabelText();
    }

    @FXML
    private TableView table;
    @FXML
    private TableColumn<MiniStatement, String> amount;
    @FXML
    private TableColumn<MiniStatement, String> time;
    @FXML
    private TableColumn<MiniStatement, String> date;
    @FXML
    private TableColumn<MiniStatement, String> type;

    @FXML
    private void setLabelText(ActionEvent event) throws IOException {

        amount.setCellValueFactory(new PropertyValueFactory("amount"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        type.setCellValueFactory(new PropertyValueFactory("type"));

        ArrayList<String> logg = new ArrayList<String>();
        ArrayList<String> logA = new ArrayList<String>();

        logg = c.getLogs();
        logA = c.getAmount();
        String sTime = "";
        String sType = "";
        String sDate = "";
        //System.out.println(logg);
        for (int i = 1; i < logg.size(); i++) {
            for (int j = 1; j < logg.get(i).length(); j++) {
                sType = ((logg.get(i).charAt(0) == 'W') ? "Withdrawed" : "Deposited");
                if (j >= 1 && j <= 10) {
                    sDate += Character.toString(logg.get(i).charAt(j));
                } else if (j >= 12 && j <= 22) {
                    sTime += Character.toString(logg.get(i).charAt(j));
                }
            }
            //System.out.println("->" + sTime  + sDate + sType);
            addLog(logA.get(i - 1), sTime, sDate, sType);
            sType = sDate = sTime = "";
        }
        // l.setText(printT);

    }

    private void setLabelText() {
        /*ArrayList<Customer> list = new ArrayList<Customer>();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customer.bin"));
        Customer ct = null;*/

        amount.setCellValueFactory(new PropertyValueFactory("amount"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        type.setCellValueFactory(new PropertyValueFactory("type"));

        ArrayList<String> logg = new ArrayList<String>();
        ArrayList<String> logA = new ArrayList<String>();
        /*try {
            while ((ct = (Customer) ois.readObject()) != null) {
                list.add(ct);
            }
        } catch (Exception e) {
            ois.close();
        }*/
 /*ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("Customer.bin"));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss a");
                LocalDateTime now = LocalDateTime.now();
                String createDate = "W";
                createDate += dtf.format(now);*/

 /* for (Customer cIN : list) {
            if (cIN.getiD() == c.getiD()) {
                c = cIN;
                logg = c.getLogs();
                logA = c.getAmount();
            }
        }*/
        logg = c.getLogs();
        logA = c.getAmount();
        String sTime = "";
        String sType = "";
        String sDate = "";
        //System.out.println(logg);
        for (int i = 1; i < logg.size(); i++) {
            for (int j = 1; j < logg.get(i).length(); j++) {
                sType = ((logg.get(i).charAt(0) == 'W') ? "Withdrawed" : "Deposited");
                if (j >= 1 && j <= 10) {
                    sDate += Character.toString(logg.get(i).charAt(j));
                } else if (j >= 12 && j <= 22) {
                    sTime += Character.toString(logg.get(i).charAt(j));
                }
            }
            //System.out.println("->" + sTime  + sDate + sType);
            addLog(logA.get(i - 1), sTime, sDate, sType);
            sType = sDate = sTime = "";
        }
        // l.setText(printT);

    }

    private void addLog(String amount, String time, String date, String type) {
        //System.out.println("--------" + time + "::" + date + "::" + type);
        MiniStatement newM = new MiniStatement(amount, time, date, type);
        table.getItems().add(newM);
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
        //
    }

}


