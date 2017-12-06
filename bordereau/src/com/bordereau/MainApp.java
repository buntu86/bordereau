package com.bordereau;

import com.bordereau.utils.Log;
import com.bordereau.utils.XmlToPdf;
import com.bordereau.view.RootLayoutController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/bordereau/view/RootLayout.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            RootLayoutController controller = loader.getController();
            controller.setRootLayout(rootLayout);
            controller.mainView();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            Log.msg(1, "Error RootLayout.fxml | " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        //launch(args);
        XmlToPdf.print();
        System.exit(0);
    }
}


