package com.bordereau.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auteur {

    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final StringProperty initiales = new SimpleStringProperty();    
    
    public Auteur(int id, String nom, String prenom, String initiales){
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setInitiales(initiales);
    }
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
    
    
    public String getNom() {
        return nom.get();
    }

    public void setNom(String value) {
        nom.set(value);
    }

    public StringProperty nomProperty() {
        return nom;
    }


    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String value) {
        prenom.set(value);
    }

    public StringProperty prenomProperty() {
        return prenom;
    }


    public String getInitiales() {
        return initiales.get();
    }

    public void setInitiales(String value) {
        initiales.set(value);
    }

    public StringProperty initialesProperty() {
        return initiales;
    }

    @Override
    public String toString() {
        return getInitiales();
    }
}
