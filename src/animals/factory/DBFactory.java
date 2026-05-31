package animals.factory;

import animals.database.IDBConnector;
import animals.database.MySQLConnector;
import animals.database.PostgreSQLConnector;
import animals.exeptions.DBConnectException;

import java.sql.SQLException;

public class DBFactory {
    private String dbType = System.getenv("dbType");

    public IDBConnector getDBConnector() throws SQLException {
        switch (dbType) {
            case "PostgreSQL" -> {
                return new PostgreSQLConnector();
            }
            case "mysql" -> {
                return new MySQLConnector();
            }

        }

        throw new DBConnectException(dbType);
    }
}
