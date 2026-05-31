package animals.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLConnector extends AbsConnector {

    public PostgreSQLConnector() throws SQLException {
        super();
    }

//   getProperty изменить на getenv?
//    private String username = System.getProperty(" ");
//    private String password = System.getProperty(" ");
//    private String jdbcUrl = System.getProperty("jdbcUrl", "jdbc:postgresql://sql.otus.kartushin.su:5432/stage");
//
//    private static Statement statement = null;
//    private static Connection connection = null;
//
//    // Конструктор. Подключение к БД
//    public PostgresqlConnector() throws SQLException {
//        if (connection != null) {
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//        }
//
//        if (statement != null) {
//            statement = connection.createStatement();
//        }
//    }
//
////    закрытие connection
//    public void close() throws SQLException {
//        if (statement != null) {
//            statement.close();
//        }
//
//        if (connection != null) {
//            connection.close();
//        }
//    }
}

