package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
    Connection connection = null;
    String url = "jdbc:mariadb://localhost:3306/db-sistema";
    String user = "root";
    String password = "4435";
    public Conexion() {

    }

  

    public Connection getConexion() {



        try {

            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("CONEXION FALLIDA");
        }

        return connection;
    }
    
    public boolean Conectar() {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection( url, user, password);
            return connection != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void Desconectar() {
        try {
            this.connection.close();
        } catch (Exception e) {
        }
    }
    
    
    

}
