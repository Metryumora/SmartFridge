package snaige.smartfridge.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import snaige.smartfridge.HibernateUtil;
import snaige.smartfridge.Main;
import snaige.smartfridge.entity.User;

/**
 * Created by Metr_yumora on 01.05.2017.
 */
public class RegistrationController {

    @FXML
    TextField loginTF;

    @FXML
    PasswordField passwordTF;

    @FXML
    PasswordField verifyTF;

    @FXML
    void register() {
        if (passwordTF.getText().equals(verifyTF.getText()) && HibernateUtil.getUserByName(loginTF.getText()) == null) {
            User newUser = new User(loginTF.getText(), passwordTF.getText());
            HibernateUtil.getSession().save(newUser);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("User '" + newUser.getName() + "' successfully created!");
            alert.showAndWait();
            returnToLogin();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Unable to create user, passwords do not match or selected username already exists!");
            alert.showAndWait();
        }
    }

    @FXML
    void returnToLogin() {
        Main.getMainController().getRegistrationStage().hide();
        Main.getMainController().getLoginStage().show();
    }
}
