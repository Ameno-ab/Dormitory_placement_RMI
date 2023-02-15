package dormitoy_placement.server;

import dormitoy_placement.service.DPS;
import java.sql.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Serverimpl implements DPS {

  public Connection conn = null;
    public Statement stmt = null;
    public Serverimpl() throws RemoteException, ClassNotFoundException, SQLException {
        UnicastRemoteObject.exportObject(this , 0);
        String DB_URL = "jdbc:mysql://localhost:3306/dps";
        String USER = "root";
        String PASS = "amen";


        //Register JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Open a connection
        System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected database successfully...");

    }

    @Override
    public String LoginAsAdmin(String username) throws RemoteException, SQLException {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");

                String name = rs.getString("username");
                String pass = rs.getString("password");
                   System.out.println("name"+name+"password"+pass);

            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "amen";

    }
}
