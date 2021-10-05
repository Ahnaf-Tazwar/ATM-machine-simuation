/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class ATM extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ATM_Main.fxml"));

        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("/Stylesheet/BackGround.jpeg"));
        stage.getIcons().add(icon);
        stage.setTitle("ATM Machine");
        stage.setScene(scene);
        stage.show();
    
}

/**
 * @param args the command line arguments
 */
public static void main(String[] args) {
        launch(args);
    }

}


