package com.example.foodiepal;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

   // public static Alert E = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
    public static   Connection connectDb(){
       // Connection connect=null;
        try {
            //  Class.forName("com.mysql.jdbc.Driver");
           // Class.forName("com.mysql.cj.jdbc.Drive");
            //Connect your Database
            String driver="com.mysql.cj.jdbc.Drive";
            String url = "jdbc:mysql://localhost:3306/Foodiepal";
            //String url       = "jdbc:mysql://localhost/Foodiepal";
            String user = "root";
            String password = "";
            Class.forName(driver);
            Connection connect = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected");
            return connect;
        }


        //    Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/Foodiepal","root","");



//            try {
//               // Class.forName("com.mysql.jdbc.Driver");
//                Class.forName("com.mysql.cj.jdbc.Driver.");
//
//                String url = "jdbc:mysql://localhost/:3306/Foodiepal";
//                String user = "root";
//                String password = "";
//                connect = DriverManager.getConnection(url, user, password);
//            }
//            catch (ClassNotFoundException | SQLException ex){
//                System.err.println("Exception: "+ex.getMessage());
//                E.show();
//            }
//
//            return  connect;

       // }
        catch (Exception e){
            e.printStackTrace();
          //  System.out.println(e);
        }


        return  null;

    }

}
