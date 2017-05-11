package snaige.smartfridge.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Metr_yumora on 11.05.2017.
 */
public class MainController {

    private Stage loginStage;

    private Parent loginParent;

    private Stage registrationStage;

    private Parent registrationParent;

    private Stage fridgeControlStage;

    private Parent fridgeControlParent;

    public MainController(Stage primaryStage) throws Exception {
        fridgeControlParent = FXMLLoader.load((getClass().getResource("/view/control.fxml")));
        fridgeControlStage = new Stage();
        fridgeControlStage.setScene(new Scene(fridgeControlParent));
        fridgeControlStage.setTitle("SmartFridge Control Panel");

        registrationParent = FXMLLoader.load((getClass().getResource("/view/register.fxml")));
        registrationStage = new Stage();
        registrationStage.setScene(new Scene(registrationParent));
        registrationStage.setTitle("SmartFridge Control Panel");

        loginParent = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        primaryStage.setTitle("Log in SmartFridge");
        primaryStage.setScene(new Scene(loginParent));
        loginStage = primaryStage;
        primaryStage.show();

    }

    public Stage getLoginStage() {
        return loginStage;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    public Parent getLoginParent() {
        return loginParent;
    }

    public void setLoginParent(Parent loginParent) {
        this.loginParent = loginParent;
    }

    public Stage getRegistrationStage() {
        return registrationStage;
    }

    public void setRegistrationStage(Stage registrationStage) {
        this.registrationStage = registrationStage;
    }

    public Parent getRegistrationParent() {
        return registrationParent;
    }

    public void setRegistrationParent(Parent registrationParent) {
        this.registrationParent = registrationParent;
    }

    public Stage getFridgeControlStage() {
        return fridgeControlStage;
    }

    public void setFridgeControlStage(Stage fridgeControlStage) {
        this.fridgeControlStage = fridgeControlStage;
    }

    public Parent getFridgeControlParent() {
        return fridgeControlParent;
    }

    public void setFridgeControlParent(Parent fridgeControlParent) {
        this.fridgeControlParent = fridgeControlParent;
    }
}
