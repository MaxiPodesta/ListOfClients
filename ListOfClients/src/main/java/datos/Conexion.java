package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    //Necessary variables for the connection to the DB in MySql
    // final because this values shouldn't be modify under any circunstances from other class or method
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";

    private static BasicDataSource dataSource;//variable use to stablish the connection to the DB
   
    //This method is on charge to recover the connection(with the values that are already set on the variables) to the DB is the reason why bring baack an object of the class DataSource
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);//initial size of the connection pool
        }
        return dataSource;
    }
   //Method to get a connection from  the connection pool through the metoth getDataSource
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    //Metoth to close the objects conn,stmt,rs of the objects once are not need it anymore
    //I close them in order to realese all the resources asociated with them and the connection onces is not need it anymore
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
