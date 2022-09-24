package edu.hackrice.lifehackstartercode.dbUtil;

import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static final String CONN = "jdbc:sqlite:";

    public static java.sql.Connection getConnection() throws SQLException{
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(CONN);
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }

}
