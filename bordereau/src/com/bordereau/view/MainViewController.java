package com.bordereau.view;

import com.bordereau.data.Employes;
import com.bordereau.model.Auteur;
import com.bordereau.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class MainViewController implements Initializable {

    @FXML
    private ChoiceBox<Auteur> auteur;
    
    @FXML
    private Pane dragDropZone;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        defaultStyleDropZone();
        
        Log.msg(0, "size" + Employes.getListEmployes().size());
        
        auteur.setItems(Employes.getListEmployes());
        auteur.getSelectionModel().selectFirst();

        dragDropZone.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != dragDropZone) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });
        
        dragDropZone.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dragDropZone) {
                    greenStyleDropZone();
                }
                event.consume();
            }
        });        

        dragDropZone.setOnDragDropped(event -> {
                boolean success = false;
                if (event.getGestureSource() != dragDropZone && event.getDragboard().hasFiles()) {
                    event.getDragboard().getFiles().forEach(file -> System.out.println(file.getAbsolutePath()));
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();            
        });
        
        
        dragDropZone.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                defaultStyleDropZone();

                event.consume();
            }
        }); 
        
    }     
    
    private void defaultStyleDropZone(){
        dragDropZone.setStyle("-fx-border-color: gray; -fx-border-style: dotted; -fx-border-width: 2px; -fx-background-color: white;");
    }
    private void greenStyleDropZone(){
        dragDropZone.setStyle("-fx-border-color: green; -fx-border-style: dotted; -fx-border-width: 2px; -fx-background-color: white;");
    }    
}

