package animals.database;

import java.sql.*;

public abstract class AbsConnector implements IDBConnector {

    //   getProperty изменить на getenv?
//    private String username = System.getProperty(" ");
//    private String password = System.getProperty(" ");
//    private String jdbcUrl = System.getProperty("jdbcUrl", "jdbc:postgresql://sql.otus.kartushin.su:5432/stage");

//    private static final String jdbcUrl = "jdbc:mysql://sql.otus.kartushin.su:5432/stage";
//    private static final String username = "student";
//    private static final String password = "student";

    private static final String jdbcUrl = System.getenv("dbURL");
    private static final String username = System.getenv("userName");
    private static final String password = System.getenv("password");

    private static Statement statement = null;
    private static Connection connection = null;

    // Конструктор. Подключение к БД
//    public AbsConnector() throws SQLException {
//        if (connection != null) {
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//            System.out.println("Подключение к БД есть!");
//        }
//
//        if (statement != null) {
//            statement = connection.createStatement();
//        }
//    }

    public AbsConnector() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Подключение к БД установлено!");
        }
        if (statement == null && connection != null) {
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
