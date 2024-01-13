package database.engines;

public class Postgres implements IDataBase{
    private static Postgres connection;
    private Postgres(){}

    public static Postgres getInstance(){
        if(connection == null){
            connection = new Postgres();
        }
        return connection;
    }

    @Override
    public String getName(){
        return "PostgreSQL";
    }
}
