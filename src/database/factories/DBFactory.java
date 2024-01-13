package database.factories;

import database.engines.IDataBase;
import database.engines.*;

public class DBFactory {
    public static IDataBase DataBaseFactory(String engineName){
        return switch (engineName) {
            case "MySql" -> MySql.getInstance();
            default -> Postgres.getInstance();
        };
    }
}
