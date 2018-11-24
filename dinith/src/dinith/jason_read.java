/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinith;


import java.io.FileReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.*;

/**
 *
 * @author Dinith Gamage
 */
public class jason_read {
    
Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;

 public jason_read() {
        
        
            conn = DBconnect.connect();// setting up the connection
 }
        
      JSONArray a = (JSONArray) parser.parse(new FileReader
        ("C:\\Users\\Dinith Gamage\\Documents\\NetBeansProjects\\dinith.json"));//read from Json file

  for (Object o : a)
  {
      /*
      getting values from jason file
      */
    JSONObject vehicl = (JSONObject) o;

    String vehicalID = (String) vehicl.get("vehicleId");
    System.out.println(vehicalID);

    String location = (String) vehicl.get("Location");
    System.out.println(location);
    
    //Split location data
    String [] arr=location.split(",",2);

    String log = arr[0];
    String lat = arr[1];

    String time = (String) vehicl.get("time");
    System.out.println(time);
    
    String speed = (String) vehicl.get("speed");
    System.out.println(speed);
    
      
         try{
             /*
             Insert in to the data base
             */
           String sql="insert into vehicl_log values(?,?,?,?,?)";
           pst=conn.prepareStatement(sql);
           pst.setString(1,vehicalID);
           pst.setString(2,lat);
            pst.setString(3,log);
           pst.setString(4,time);
           pst.setString(4,speed);
                            
         
           pst.executeUpdate();
          }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Insertion error !");
            
        }

  }
    
}
