package com.bordereau.view;

import com.bordereau.MainApp;
import com.bordereau.utils.Log;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class RootLayoutController {

    private BorderPane rootLayout;
    
    public RootLayoutController(){
        
    }

    public void mainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/bordereau/view/MainView.fxml"));            
            AnchorPane mainViewLayout = (AnchorPane) loader.load();     
            MainViewController controller = loader.getController();
            
            rootLayout.setCenter(mainViewLayout);

        } catch (Exception e) {
            Log.msg(1, "mainView | " + e.getMessage());
        }
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }
}
