package com.bordereau.data;

import com.bordereau.model.Auteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employes {
   
    public static ObservableList<Auteur> getList(){
        
        ObservableList<Auteur> list = FXCollections.observableArrayList();
        list.add(new Auteur(0, "inconnu", "inconnu", "---"));
        list.add(new Auteur(0, "Pillonel", "Adrien", "AP"));
        list.add(new Auteur(0, "Melly", "Lucianne", "LM"));
        list.add(new Auteur(0, "Neto", "Fabrice", "FN"));

        return list;
    }
}
