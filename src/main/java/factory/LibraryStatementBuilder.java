package main.java.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryStatementBuilder implements StatementBuilder{

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://116.202.211.83:3306/db_62692";

    private String USER = "db_62692";
    private String PASS = "NwLurZQOTOnY";

    private Connection connection;

    public LibraryStatementBuilder() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Statement createStatement(){
        try {
            return connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
