package com.bordereau.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mandat {
    private final StringProperty num = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty path = new SimpleStringProperty();    
    private final IntegerProperty ID = new SimpleIntegerProperty();
    
    public Mandat(int ID, String num, String nom, String path) {
        setNum(num);
        setNom(nom);
        setPath(path);
        setID(ID);
    }

    @Override
    public String toString() {
        return getNum() + " - " + getNom();
    }
    

    public int getID() {
        return ID.get();
    }

    public void setID(int value) {
        ID.set(value);
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public String getNum() {
        return num.get();
    }

    public void setNum(String value) {
        num.set(value);
    }

    public StringProperty numProperty() {
        return num;
    }


    public String getNom() {
        return nom.get();
    }

    public void setNom(String value) {
        nom.set(value);
    }

    public StringProperty nomProperty() {
        return nom;
    }


    public String getPath() {
        return path.get();
    }

    public void setPath(String value) {
        path.set(value);
    }

    public StringProperty pathProperty() {
        return path;
    }
}
