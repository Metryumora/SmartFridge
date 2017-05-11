package snaige.smartfridge.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import snaige.smartfridge.HibernateUtil;
import snaige.smartfridge.Main;

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
            Main.getMainController().getLoginStage().hide();
            Main.getMainController().getFridgeControlStage().show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wrong user or password!");
            alert.showAndWait();
        }
    }

    @FXML
    void switchToRegister() throws Exception {
        Main.getMainController().getLoginStage().hide();
        Main.getMainController().getRegistrationStage().show();
    }

}
