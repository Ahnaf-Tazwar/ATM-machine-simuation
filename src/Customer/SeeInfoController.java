/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import atm.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class SeeInfoController implements Initializable {

    Customer c = null;

    public void sendInfo(Customer c1) {
        c = c1;
        String card = "/Images/" + convertCardNo(Long.toString(c.getiD())) + ".png";
        String b = Float.toString(c.getBalance()) + " BDT";
        fName.setText(c.getfName());
        lName.setText(c.getlName());
        cardNo.setText(convertCardNo(Long.toString(c.getiD())));
        balance.setText(b);
        gender.setText((c.getGender() == 'M') ? "Male" : "Female");
        email.setText(c.getEmail());
        dob.setText(c.getDob());
        System.out.println("See Info:"+c.getDob());
        preAdd.setText(c.getPreAddress());
        perAdd.setText(c.getPerAddress());
        image.getChildren().clear();
        Image im = new Image(card);
        ImageView i = new ImageView(im);
        i.setFitWidth(120);
        i.setFitHeight(120);
        image.getChildren().add(i);
        
    }

    private String convertCardNo(String cardNo) {
        String show = "";
        for (int i = 0; i < cardNo.length(); i++) {
            if (i % 4 == 0 && i != 0) {
                show += "-";
            }
            show += cardNo.charAt(i);
        }
        return show;
    }

    @FXML
    private Label fName;
    @FXML
    private Label lName;
    @FXML
    private Label cardNo;
    @FXML
    private Label balance;
    @FXML
    private Label gender;
    @FXML
    private Label email;
    @FXML
    private Label dob;
    @FXML
    private Label preAdd;
    @FXML
    private Label perAdd;
    @FXML
    private Pane image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //fName.setText(c.getfName());
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

}
