package animals.tables;

import animals.database.AbsConnector;
import animals.database.IDBConnector;
import animals.factory.DBFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsTable {

    private DBFactory dbFactory = new DBFactory();
    protected IDBConnector dbConnector;
    private String tableName;

    public AbsTable(String tableName) {
        this.tableName = tableName;
        try {
            this.dbConnector = dbFactory.getDBConnector();
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }
    }

//    создание таблицы
    public void create(String... columns) throws SQLException {
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, String.join(", ", columns));
        this.dbConnector.execute(sqlRequest);
    }

//    чтение таблицы
    public List<Map<String, String>> listDataFromTable(String predicate, String... columns) throws SQLException {

        String sqlColumns = "*";
        if (columns.length != 0) {
            sqlColumns = String.join(", ", columns);
        }

        String sqlRequest = String.format("SELECT %s FROM %s", sqlColumns, tableName);
        if (!predicate.isEmpty()) {
            sqlRequest += String.format(" WHERE %s", predicate);
        }

        ResultSet resultSet = this.dbConnector.executeWithData(sqlRequest);

        List<Map<String, String>> result = new ArrayList<>();

//        чтение построчно
        while (resultSet.next()) {
            Map<String, String> stroka = new HashMap<>();
            for (String columnName: columns) {
                stroka.put(columnName, resultSet.getString(columnName));
            }
            result.add(stroka);
        }

        return result;
    }
}
