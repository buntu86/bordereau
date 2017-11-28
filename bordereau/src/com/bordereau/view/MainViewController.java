package com.bordereau.view;

import com.bordereau.data.Employes;
import com.bordereau.model.Auteur;
import com.bordereau.model.Document;
import com.bordereau.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class MainViewController implements Initializable {

    @FXML
    private ChoiceBox<Auteur> auteur;
    
    @FXML
    private Pane dragDropZone;
    
    @FXML
    private TableView<Document> tableView;
    
    @FXML
    private TableColumn<Document, String> tc_num;
    @FXML
    private TableColumn<Document, String> tc_nombre;
    @FXML
    private TableColumn<Document, String> tc_nom;    
    @FXML
    private TableColumn<Document, String> tc_type;    

    private ObservableList<Document> listDocuments = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Definition colonnes
        tc_num.setCellValueFactory(cellData -> cellData.getValue().numProperty());
        tc_nom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        tc_nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        
        //Definitino dropZone
        defaultStyleDropZone();        
        
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
                        event.getDragboard().getFiles().forEach(file -> listDocuments.add(new Document(file.getAbsolutePath())));
                        Log.msg(0, "size:" + listDocuments.size());
                        if(listDocuments.size()!=0)
                            tableView.setItems(listDocuments);  
                    }
                    success = true;
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
        dragDropZone.setStyle("-fx-border-color: green; -fx-border-style: dotted; -fx-border-width: 5px; -fx-background-color: white;");
    }    
}

