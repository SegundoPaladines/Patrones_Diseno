package database.engines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import models.Producto;

public class Postgres implements IDataBase {
    private static Postgres connection;
    private static Connection dbConnection;

    private Postgres() {
        // hacer la conexion
        connect();
    }

    public static Postgres getInstance() {
        if (connection == null) {
            connection = new Postgres();
        }
        return connection;
    }

    private void connect() {
        // conexion
        String dbUrl = "jdbc:postgresql://localhost:5432/productos";
        String user = "postgres";
        String password = "123";

        try {
            // cargar el controlador
            Class.forName("org.postgresql.Driver");
            //establecer conexion
            dbConnection = DriverManager.getConnection(dbUrl, user, password);
            this.checkTables();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkTables() {
        try {
            // si la tabla existe
            String checkTableQuery = "SELECT to_regclass('public.productos')";
    
            Statement checkStatement = dbConnection.createStatement();
            ResultSet checkResultSet = checkStatement.executeQuery(checkTableQuery);
    
            if (checkResultSet.next() && checkResultSet.getString(1) == null) {
                // crear la tabla si no existe
                String createTableQuery = "CREATE TABLE productos (id SERIAL PRIMARY KEY, nombre VARCHAR(250), cantidad INT, valor_unitario FLOAT)";
    
                Statement createStatement = dbConnection.createStatement();
                createStatement.executeUpdate(createTableQuery);
                
                System.out.println("Tabla 'productos' creada.");
            } else {
                System.out.println("La tabla 'productos' ya existe.");
            }
            // cerrar los recursos
            checkResultSet.close();
            checkStatement.close();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    @Override
    public String getName() {
        return "PostgreSQL";
    }

    @Override
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        try {
            // obtener los productos
            //la sentencia SQL
            String query = "SELECT * FROM productos";
            //el stanmet de la conexion
            Statement statement = dbConnection.createStatement();
            //ejecucion de la sentencia SQL
            ResultSet resultSet = statement.executeQuery(query);

            // recorrer los resultados
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidad");
                float valor_unitario = resultSet.getFloat("valor_unitario");

                Producto p = new Producto(id, nombre, cantidad, valor_unitario);
                p.bd = "PostgreSQL";
                
                // almacenar los productos
                productos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public String addProducto(Producto producto){
        String query = "INSERT INTO productos (nombre, cantidad, valor_unitario) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = dbConnection.prepareStatement(query)){

            preparedStatement.setString(1, producto.nombre);
            preparedStatement.setInt(2, producto.cantidad);
            preparedStatement.setFloat(3, producto.valor_unitario);

            preparedStatement.executeUpdate();

            return "success";
            
        } catch (Exception e) {
            return "error "+e.toString();
        }
    }
}