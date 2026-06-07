package animals.database;

import java.sql.*;

public class AbsConnector implements IDBConnector {

    private static final String jdbcUrl = System.getenv("dbURL");
    private static final String username = System.getenv("userName");
    private static final String password = System.getenv("password");

    private static Statement statement = null;
    private static Connection connection = null;


    public AbsConnector() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Подключение к БД установлено!");
        }
        if (statement == null) {
            statement = connection.createStatement();
        }
    }

    //    закрытие connection
    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public void execute(String sqlRequest) throws SQLException {
        statement.execute(sqlRequest);
    }

    public ResultSet executeWithData(String sqlRequest) throws SQLException {
        return statement.executeQuery(sqlRequest);
    }
}
