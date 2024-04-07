package mx.com.gm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConePostgresql {
    public static void main(String[] args){
        String url="jdbc:postgresql://localhost:5432/test?serverTimezone=UTC";
        String usermane = "postgres";
        String password = "admin";

        try {
            Connection connection = DriverManager.getConnection(url, usermane, password);
            Statement statement = connection.createStatement();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
