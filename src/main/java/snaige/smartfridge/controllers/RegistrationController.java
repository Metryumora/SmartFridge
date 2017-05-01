package snaige.smartfridge.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import snaige.smartfridge.HibernateUtil;
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
        }
    }
}
