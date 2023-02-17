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
    public String[] Login(String username, String password) throws RemoteException, SQLException {



//        String name="befi";
//        String pass="123";
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user where username ='"+username+"'and password = '"+password +"'";

            ResultSet rs = stmt.executeQuery(sql);
            String[] res = new String[4];
            if (rs.next()){
                Integer status = rs.getInt("status");
                res[0]="true";
                if (status == 1){
                    System.out.println("Welcome::: " + status);
                    res[1]="1";
                    res[2]=rs.getString("type");
                    res[3]=rs.getString("username");
                    return res;
                }else{
                    System.out.println("account blocked"+ status);
                    res[1]="0";
                    return res;
                }
           }
            else {
                System.out.println("Invalid user name and password");
                res[0]="false";
                return res;
            }

//            while(rs.next()) {
//                // Retrieve by column name
////                int id  = rs.getInt("id");
////
////                String name = rs.getString("username");
////                String pass = rs.getString("password");
//                   System.out.println("name"+name+"password"+pass);
//
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public String Register(String username,String password,String type) throws RemoteException, SQLException {
        try {
            stmt = conn.createStatement();

//            String sql = "SELECT * FROM user where username ='"+username+"'and password = '"+password +"'";
            int result = stmt.executeUpdate(
                    "insert into user(username,password,type,status) values('"+username+"','"+password+"','"+type+"','1')");

//            ResultSet rs = stmt.executeUpdate(sql);
            if (result > 0){

                    System.out.println("Succsfully registred ");
//                    String type = rs.getString("type");
                    return "Succsfully registred";

            }
            else {
                System.out.println("try again");
                return "try again";
            }

//            while(rs.next()) {
//                // Retrieve by column name
////                int id  = rs.getInt("id");
////
////                String name = rs.getString("username");
////                String pass = rs.getString("password");
//                   System.out.println("name"+name+"password"+pass);
//
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getDorm() throws RemoteException, SQLException {
        return new String[0];
    }


}
