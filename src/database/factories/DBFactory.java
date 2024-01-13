package database.factories;

import database.engines.IDataBase;
import database.engines.*;

public class DBFactory {
    public static IDataBase DataBaseFactory(String engineName){
        switch (engineName) {
            case "MySql":
                return MySql.getInstance();
            default:
                return Postgres.getInstance();
        }
    }
}
