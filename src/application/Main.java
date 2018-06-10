package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            MyWindow window = new MyWindow (primaryStage);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
