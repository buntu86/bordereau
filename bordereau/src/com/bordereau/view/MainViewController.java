package com.bordereau.view;

import com.bordereau.data.Employes;
import com.bordereau.data.Mandats;
import com.bordereau.model.Auteur;
import com.bordereau.model.Document;
import com.bordereau.model.Mandat;
import com.bordereau.model.XmlPrint;
import com.bordereau.utils.Log;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;


import org.xml.sax.SAXException;

public class MainViewController implements Initializable {

    @FXML
    private ChoiceBox<Auteur> auteur;
    
    @FXML
    private ComboBox<Mandat> combo_mandat;
    
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
    
    @FXML
    private DatePicker date;
    
    private ObservableList<Document> listDocuments = FXCollections.observableArrayList();
    private ObservableList<Mandat> listMandats = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        date.setValue(LocalDate.now());
        
        //Definition colonnes
        tc_num.setCellValueFactory(cellData -> cellData.getValue().numProperty());
        tc_nom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        tc_nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        //Definition choiceBox Employés
        auteur.setItems(Employes.getList());
        auteur.getSelectionModel().selectFirst();

        //Definition choiceBox mandat
        listMandats.add(new Mandat(0, "1000", "Divers", ""));
        listMandats.addAll(Mandats.getList());
        
        combo_mandat.setItems(listMandats);
        combo_mandat.getSelectionModel().selectFirst();
        
        //Definition dropZone
        defaultStyleDropZone();        

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
                        if(listDocuments.size()!=0)
                            tableView.setItems(listDocuments);
                        Log.msg(0, selectMandat().getNum() + "-" + selectMandat().getNom());
                        combo_mandat.getSelectionModel().select(selectMandat());
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

    private Mandat selectMandat() {       
        Mandat mandat = null;
        
        if(listDocuments.size()>0)
        {
            final String numMandat = listDocuments.get(listDocuments.size()-1).getNumMandat();
            mandat = listMandats
                    .stream()
                    .filter(m -> m.getNum().equals(numMandat))
                    .findFirst()
                    .get();
        }
        
        return mandat;
    }
    
    @FXML
    private void print() throws IOException, FOPException, SAXException, TransformerConfigurationException, TransformerException, JAXBException{
        /*
        XmlPrint printDoc = new XmlPrint(listDocuments);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlPrint.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(printDoc, sw);
            String xmlString = sw.toString();
            System.out.println(xmlString);                
        } catch (JAXBException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        /*
        File xsltFile = new File("C:/Temp/AMVtemplate.xsl");
        StreamSource xmlSource = new StreamSource(new File("C:/Temp/AMVdocuments.xml"));
        //FopFactory fopFactory = FopFactory.newInstance(new File("C:/Temp/fop.xconf"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out;
        out = new java.io.FileOutputStream("C:/Temp/AMVout.pdf");
        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            
            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        } */          
    }    
}

        