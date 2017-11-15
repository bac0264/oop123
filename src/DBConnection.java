/**
 * Created by Bac on 11/13/2017.
 */

import java.sql.*;
public class DBConnection {
    public static Connection connection(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/smanagement";
        String user = "root";
        String pass = "";
        try {
            conn = DriverManager.getConnection(url,user,pass);
            return conn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
