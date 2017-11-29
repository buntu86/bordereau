package com.bordereau.data;

import com.bordereau.model.Mandat;
import com.bordereau.utils.Config;
import com.bordereau.utils.Log;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Mandats {
    public static ObservableList<Mandat> getList(){
        ObservableList<Mandat> list = FXCollections.observableArrayList();
    
        Path pathListMandat = Paths.get(Config.getListMandatPath());
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + pathListMandat);
            
            String sql = "SELECT * FROM ListMandats WHERE idStatut=0 ORDER BY numMandat DESC";            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){                
                list.add(new Mandat(rs.getInt("ID"), 
                        rs.getString("numMandat"), 
                        rs.getString("nomMandat"), 
                        rs.getString("path")));
            }
            
            Log.msg(0, "size " + list.size() + "|" + pathListMandat.toString());
            
        }
        catch (SQLException e) {
            Log.msg(1, "getListMandat fail to connect " + pathListMandat + " | "+ e.getMessage());
        }        
        
        return list;
    }
}
