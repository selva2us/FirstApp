package com.nmp.firstapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {

 String IP,DB,DBUserName,DBPassword;

 @SuppressLint("LoginApi")
 public Connection connection(){
     IP="10.0.2.2";
     DB="nmi";
     DBUserName="sa";
     DBPassword="selva@!";
     StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
     StrictMode.setThreadPolicy(policy);
     Connection connection = null;
     String connectionURL= null;
     try{
         Class.forName("net.sourceforge.jtds.jdbc.Driver");
         connectionURL = "jdbc:jtds:sqlserver://" + IP + ";"
                 + "databaseName=" + DB + ";user=" + DBUserName + ";password="
                 + DBPassword + ";";
         connection = DriverManager.getConnection(connectionURL);
         Boolean user = connection.isClosed();
       //  final String CHECK_SQL_QUERY = "SELECT 1";
         //final PreparedStatement statement = connection.prepareStatement(CHECK_SQL_QUERY);
         //Log.i("Test",statement.toString());
     }catch (SQLException e){
         Log.i("Error from Sql", e.getMessage());
     }catch (ClassNotFoundException e) {
         Log.i("Error from class", e.getMessage());
     }
     catch (Exception e) {
         Log.i("Error from Excep", e.getMessage());
     }
     return connection;
 }
}
