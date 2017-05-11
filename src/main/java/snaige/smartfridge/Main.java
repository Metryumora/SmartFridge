package snaige.smartfridge;

import javafx.application.Application;
import javafx.stage.Stage;
import snaige.smartfridge.controllers.MainController;

public class Main extends Application {

    private static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainController = new MainController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
        HibernateUtil.getSession();
    }

    public static MainController getMainController() {
        return mainController;
    }


}
