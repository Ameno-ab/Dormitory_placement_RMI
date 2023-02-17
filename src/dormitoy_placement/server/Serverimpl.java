package dormitoy_placement.server;



import dormitoy_placement.service.DPS;

import java.sql.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Serverimpl implements DPS {


    public Connection conn = null;
    public Statement stmt = null;
    List<String> list = new ArrayList<String>();
    List<String> req = new ArrayList<String>();


    public Serverimpl() throws RemoteException, ClassNotFoundException, SQLException {
        UnicastRemoteObject.exportObject(this, 0);
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
            String sql = "SELECT * FROM user where username ='" + username + "'and password = '" + password + "'";

            ResultSet rs = stmt.executeQuery(sql);
            String[] res = new String[4];
            if (rs.next()) {
                Integer status = rs.getInt("status");
                res[0] = "true";
                if (status == 1) {
                    System.out.println("Welcome::: " + status);
                    res[1] = "1";
                    res[2] = rs.getString("type");
                    res[3] = rs.getString("username");
                    return res;
                } else {
                    System.out.println("account blocked" + status);
                    res[1] = "0";
                    return res;
                }
            } else {
                System.out.println("Invalid user name and password");
                res[0] = "false";
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
    public String Register(String username, String password, String type) throws RemoteException, SQLException {
        try {
            stmt = conn.createStatement();

//            String sql = "SELECT * FROM user where username ='"+username+"'and password = '"+password +"'";
            int result = stmt.executeUpdate(
                    "insert into user(username,password,type,status) values('" + username + "','" + password + "','" + type + "','1')");

//            ResultSet rs = stmt.executeUpdate(sql);
            if (result > 0) {

                System.out.println("Succsfully registred ");
//                    String type = rs.getString("type");
                return "Succsfully registred";

            } else {
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
    public List<String> getDorm() throws RemoteException, SQLException {
        try {
            stmt = conn.createStatement();
            list.clear();
            String sql = "SELECT * FROM block where booked = 0 and request IS NULL  ";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("id\t\tname");
            while (rs.next()) {

                int id = rs.getInt("id");
                String s = String.valueOf(id);
                String name = rs.getString("block_name");
                String[] r = {s, name};
                list.add(Arrays.toString(r));
            }
            for (String l : list) {
                System.out.println("Avalible rooms" + l + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public String Book(Integer id,String username) throws RemoteException, SQLException {
        String  i;
        try {
            stmt = conn.createStatement();

            String sql = "update block set request='pending',userid='"+username+"' where id="+id+"";
            PreparedStatement p = conn.prepareStatement(sql);
            p.execute();
           System.out.println("succsfully booked");
            i="succsfully booked";
        } catch (SQLException e) {
            System.out.println("not succsfully booked");
            i="Error while booking";
            throw new RuntimeException(e);


        }
        return i;


    }

    @Override
    public List<String> getRequest() throws RemoteException, SQLException {
        try {
            stmt = conn.createStatement();
            req.clear();
            String sql = "SELECT * FROM block where request ='pending'  ";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("id\t\tname");
            while (rs.next()) {

                int id = rs.getInt("id");
                String s = String.valueOf(id);
                String name = rs.getString("block_name");
                String username = rs.getString("userid");
                String[] r = {s, name,username};
                req.add(Arrays.toString(r));
            }
            for (String l : req) {
                System.out.println("Requests for rooms" + l + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return req;
    }

    @Override
    public String setRequest(Integer id,Integer req) throws RemoteException, SQLException {
        String  i;
        String o;
        Integer b;
        if(req == 1){
            o="approve";
            b=1;
        }else{
            o="reject";
            b=0;
        }
        try {
            stmt = conn.createStatement();

            String sql = "update block set request='"+o+"',booked="+b+" where id="+id+"";
            PreparedStatement p = conn.prepareStatement(sql);
            p.execute();
            System.out.println("successfully booked");
            if(req==1){
                i="Request Approved";
            }else{
                i="Request Rejected";
            }

        } catch (SQLException e) {
            System.out.println("not succsfully booked");
            i="Error while booking";
            throw new RuntimeException(e);


        }
        return i;
    }

    @Override
    public String viewDorm(String username) throws RemoteException, SQLException {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM block where userid ='"+username+"' and booked = 1  ";
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String  dorm = rs.getString("block_name");
                return dorm;
            }else{
                return "No dorm";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String addDorm(String blockname) throws RemoteException, SQLException {
        try {
            stmt = conn.createStatement();

//            String sql = "SELECT * FROM user where username ='"+username+"'and password = '"+password +"'";
            int result = stmt.executeUpdate(
                    "insert into block(block_name,booked) values('" + blockname + "','0')");

//            ResultSet rs = stmt.executeUpdate(sql);
            if (result > 0) {

                System.out.println("Successfully Added Dorm ");
//                    String type = rs.getString("type");
                return "Successfully Added Dorm";

            } else {
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
}