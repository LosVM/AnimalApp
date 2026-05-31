package animals.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnector {

    void close() throws SQLException;
    void execute(String sqlRequest) throws SQLException;
    ResultSet executeWithData(String sqlRequest) throws SQLException;
}
