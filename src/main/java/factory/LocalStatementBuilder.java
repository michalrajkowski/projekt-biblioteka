package main.java.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalStatementBuilder implements StatementBuilder{

    private static LocalStatementBuilder istance = null;

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/biblioteka?serverTimezone=UTC";

    private String USER = "root";
    private String PASS = "admin";

    private Connection connection;

    public LocalStatementBuilder() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void resetDatabase() throws IOException, SQLException {
        ScriptRunner runner=new ScriptRunner(connection, false, false);
        InputStreamReader reader1 = new InputStreamReader(new FileInputStream("./src/main/resources/delete.sql"));
        InputStreamReader reader2 = new InputStreamReader(new FileInputStream("./src/main/resources/table.sql"));
        InputStreamReader reader3 = new InputStreamReader(new FileInputStream("./src/main/resources/view.sql"));
        InputStreamReader reader4 = new InputStreamReader(new FileInputStream("./src/main/resources/index.sql"));
        InputStreamReader reader5 = new InputStreamReader(new FileInputStream("./src/main/resources/example.sql"));
        runner.runScript(reader1);
        runner.runScript(reader2);
        runner.runScript(reader3);
        runner.runScript(reader4);
        runner.runScript(reader5);
        reader1.close();
        reader2.close();
        reader3.close();
        reader4.close();
        reader5.close();
    }

    public Statement createStatement(){
        try {
            return connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static LocalStatementBuilder getTestInstance(){
        if(istance==null) {
            istance = new LocalStatementBuilder();
            try {
                istance.resetDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return istance;
    }

    public static LocalStatementBuilder getInstance(){
        if(istance==null) {
            istance = new LocalStatementBuilder();
        }
        return istance;
    }

}
