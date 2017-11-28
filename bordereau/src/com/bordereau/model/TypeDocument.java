package com.bordereau.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TypeDocument {

    private final IntegerProperty id = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    private final StringProperty nom = new SimpleStringProperty();

    public String getNom() {
        return nom.get();
    }

    public void setNom(String value) {
        nom.set(value);
    }

    public StringProperty nomProperty() {
        return nom;
    }
    
}
