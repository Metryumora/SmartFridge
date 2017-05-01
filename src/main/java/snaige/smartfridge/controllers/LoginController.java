package snaige.smartfridge.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import snaige.smartfridge.HibernateUtil;

/**
 * Created by Metr_yumora on 01.05.2017.
 */
public class LoginController {

    @FXML
    TextField loginTF;

    @FXML
    PasswordField passwordTF;

    @FXML
    void login() throws Exception {
        if (HibernateUtil.authUser(loginTF.getText(), passwordTF.getText())) {
            System.out.print("Done!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
            Parent root = fxmlLoader.load();
            Stage fridgeControlStage = new Stage();
            fridgeControlStage.setScene(new Scene(root));
            fridgeControlStage.setTitle("SmartFridge Control Panel");
            fridgeControlStage.show();
        } else System.out.print("Not found!");
    }

}
