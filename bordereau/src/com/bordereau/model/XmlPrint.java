package com.bordereau.model;

import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlPrint {

    private ObservableList<Document> list;

    public XmlPrint(){};

    public XmlPrint(ObservableList<Document> list){
        super();
        this.list = list;
    }
    
    @XmlElement
    public ObservableList<Document> getDocuments(){
        return this.list;
    }
}
