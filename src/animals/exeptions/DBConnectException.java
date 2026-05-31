package animals.exeptions;

public class DBConnectException extends RuntimeException {

    public DBConnectException(String dbType) {
        super(String.format("DB %s not supported", dbType));
    }
}
