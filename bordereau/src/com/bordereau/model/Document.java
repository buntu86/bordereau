package com.bordereau.model;

import com.bordereau.utils.Log;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {

    private final StringProperty path = new SimpleStringProperty();
    private final StringProperty num = new SimpleStringProperty();
    private final ObjectProperty<TypeDocument> typeDoc = new SimpleObjectProperty<>();
    private final StringProperty nom = new SimpleStringProperty();
    private final IntegerProperty nombre = new SimpleIntegerProperty();    
    private final StringProperty extension = new SimpleStringProperty();
    private final StringProperty numMandat = new SimpleStringProperty();
    
    public Document(String path) {
        setPath(path);
        setNum(pathToStringWithPattern("^[a-zA-Z0-9]*-[0-9]*[a-zA-Z]?"));
        setExtension(pathToStringWithPattern("\\.\\w*$"));
        setNom(iniNom());
        setNombre(3);
        setNumMandat(pathToStringWithPattern("^[a-zA-Z0-9]*"));
        Log.msg(0, "num:" + getNum() + "|nom:" + getNom() + "|ext:" + getExtension() + "|mandat:" + getNumMandat());
    }

    public void setPath(String value) {
        path.set(value);
    }
    public void setNum(String value) {       
        num.set(value);
    }
    public void setNom(String value) {        
        nom.set(value);
    }
    public void setExtension(String value) {
        extension.set(value);
    }    
    
    public String getExtension() {
        return extension.get();
    }

   public String getNumMandat() {
        return numMandat.get();
    }

    public void setNumMandat(String value) {
        numMandat.set(value);
    }

    public StringProperty numMandatProperty() {
        return numMandat;
    }



    public StringProperty extensionProperty() {
        return extension;
    }
    
    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }
    public String getNum() {
        return num.get();
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


    public StringProperty nomProperty() {
        return nom;
    }


    public int getNombre() {
        return nombre.get();
    }

    public void setNombre(int value) {
        nombre.set(value);
    }

    public StringProperty nombreProperty() {
        return new SimpleStringProperty(getNombre() + "x");
    }

    private String pathToStringWithPattern(String pattern){
        Path tempPath = Paths.get(this.getPath());
        String str = "";
        
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(tempPath.getFileName().toString());
              
        if(m.find())
            str = m.group();
                
        return str;    
    }

    private String iniNom() {
        String tmp = Paths.get(getPath()).getFileName().toString();
        
        tmp = tmp.replace(getNum(), "");
        tmp = tmp.replace(getExtension(), "");
        
        if(tmp.substring(0, 1).equals("_"))
            tmp = tmp.substring(1);
        
        return tmp.trim();
        
    }
}
