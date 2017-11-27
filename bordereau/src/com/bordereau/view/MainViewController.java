package com.bordereau.view;

import com.bordereau.data.Employes;
import com.bordereau.model.Auteur;
import com.bordereau.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainViewController implements Initializable {

    @FXML
    private ChoiceBox<Auteur> auteur;
    
    @FXML
    private Pane dragDropZone;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Log.msg(0, "size" + Employes.getListEmployes().size());
        
        auteur.setItems(Employes.getListEmployes());
        auteur.getSelectionModel().selectFirst();

    }       
}

