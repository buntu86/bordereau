package com.bordereau.model;

import com.bordereau.utils.Log;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {

    private final StringProperty path = new SimpleStringProperty();
    private final StringProperty num = new SimpleStringProperty();
    private final ObjectProperty<TypeDocument> typeDoc = new SimpleObjectProperty<>();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty nombre = new SimpleStringProperty();    

    public Document(String path) {
        setPath(path);
        setNum();
        setNom();
        
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


    public String getNum() {
        return num.get();
    }

    public void setNum() {
        //http://cyberzoide.developpez.com/tutoriels/java/regex/
        //https://www.freeformatter.com/java-regex-tester.html#ad-output
        
        Path tempPath = Paths.get(this.getPath());
        String str = "";
        
        Pattern p = Pattern.compile("^[a-zA-Z0-9]*-[0-9]*");
        Matcher m = p.matcher(tempPath.getFileName().toString());
              
        if(m.find())
            str = m.group();
        
        Log.msg(0, "Num:" + str);
        
        num.set(str);
    }


    public TypeDocument getTypeDoc() {
        return typeDoc.get();
    }

    public void setTypeDoc(TypeDocument value) {
        typeDoc.set(value);
    }

    public ObjectProperty typeDocProperty() {
        return typeDoc;
    }

    public StringProperty numProperty() {
        return num;
    }


    public String getNom() {
        return nom.get();
    }

    public void setNom() {
        //http://cyberzoide.developpez.com/tutoriels/java/regex/
        //https://www.freeformatter.com/java-regex-tester.html#ad-output
        
        Path tempPath = Paths.get(this.getPath());
        String str = "";
        
        Pattern p = Pattern.compile("^[a-zA-Z0-9]*-[0-9]*");
        Matcher m = p.matcher(tempPath.getFileName().toString());
              
        if(m.find())
            str = m.group();
        
        Log.msg(0, "Nom:" + str);
        
        num.set(str);
    }

    public StringProperty nomProperty() {
        return nom;
    }


    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String value) {
        nombre.set(value);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }



    
}
