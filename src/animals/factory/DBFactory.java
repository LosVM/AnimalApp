package animals.factory;

import animals.database.AbsConnector;
import animals.database.IDBConnector;
import animals.exeptions.DBConnectException;

import java.sql.SQLException;

public class DBFactory {

    public IDBConnector getDBConnector() throws SQLException {
        return new AbsConnector();
    }
}
