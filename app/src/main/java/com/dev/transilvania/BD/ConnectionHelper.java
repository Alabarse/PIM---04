package com.dev.transilvania.BD;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uName, pass, ip, port, database;
    @SuppressLint("NewApi")
    public Connection connectionClass(){
        ip = "10.0.2.2";
        database = "Transilvania";
        uName = "sa";
        pass = "123456";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + ip+":"+port+";" + "databasename="+ database+";user="+uName+";password="+pass;
            connection = DriverManager.getConnection(connectionURL);

        }catch (Exception ex) {
            Log.e("Error ", ex.getMessage());

        }
        return connection;
    }
}
