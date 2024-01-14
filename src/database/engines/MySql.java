package database.engines;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import models.Producto;

public class MySql implements IDataBase{
    private static MySql connection;
    private static Connection dbConnection;

    private MySql(){
        //conectar a la base de datos
        connect();
    }

    public static MySql getInstance(){       
        
        if(connection==null){
            connection=new MySql();
        }
        return connection;
    }

    private void connect() {
        // Configuración de la conexión a MySQL
        String dbUrl = "jdbc:mysql://localhost:3306/";
        String database = "productos";
        String user = "productos";
        String password = "123";

        try {
            // el controlador
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // hacer la conexión
            dbConnection = DriverManager.getConnection(dbUrl + database, user, password);

            // verificar las tablas
            checkTables();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkTables() {
        try {
            // SQL para verificar si existe la tabla
            String query= "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'productos')";

            try (Statement checkStatement = dbConnection.createStatement();
                ResultSet checkResultSet = checkStatement.executeQuery(query)) {

                if (checkResultSet.next() && !checkResultSet.getBoolean(1)) {
                    // sql para crear la tabla
                    String createTable = "CREATE TABLE productos (id SERIAL PRIMARY KEY, nombre VARCHAR(250), cantidad INT, valor_unitario FLOAT)";

                    try (Statement createStatement = dbConnection.createStatement()) {
                        //ejecutar el comando
                        createStatement.executeUpdate(createTable);
                    }

                    System.out.println("Tabla 'productos' creada.");
                } else {
                    System.out.println("La tabla 'productos' ya existe.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName(){
        return "MySql";
    }
    
    @Override
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        //SQL
        String query = "SELECT * FROM productos";

        //se hace la consulta. el try asegura el control del error       
        try (Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // se recogen los resultados
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidad");
                float valor_unitario = resultSet.getFloat("valor_unitario");

                Producto p = new Producto(id, nombre, cantidad, valor_unitario);
                p.bd = "MySql";

                productos.add(p);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
    
    @Override
    public String addProducto(Producto producto){
        //SSSSSSSSSSSSSSQL
        String query = "INSERT INTO productos (nombre, cantidad, valor_unitario) VALUES (?, ?, ?)";

        //el try asegura el control del error
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            // se establecen los parametros
            preparedStatement.setString(1, producto.nombre);
            preparedStatement.setInt(2, producto.cantidad);
            preparedStatement.setFloat(3, producto.valor_unitario);

            // ejecutar el comando
            preparedStatement.executeUpdate();

            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error: " + e.getMessage();
        }
    }

    @Override
    public String deleteProducto(int pk){
        String query = "DELETE FROM productos WHERE id = ?";
        //preparar la sentencia
        try(PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setInt(1, pk);
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "success";
            } else {
                return "error: No se encontró el producto con el ID especificado.";
            }
        } catch (SQLException e) {
            return "error: "+e.getMessage();
        }
    }

    @Override
    public Producto getProducto(int pk){

        //preparar la sentencia
        String query = "SELECT * FROM productos WHERE id = ?";

        //se hace la consulta. el try asegura el control del error       
        try(PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setInt(1, pk);

            // ejecutar la consulta y recoger los resultados
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    int cantidad = resultSet.getInt("cantidad");
                    float valor_unitario = resultSet.getFloat("valor_unitario");

                    Producto p = new Producto(id, nombre, cantidad, valor_unitario);
                    p.bd = "MySql";

                    return p;
                } else {
                    // no hay nada
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
