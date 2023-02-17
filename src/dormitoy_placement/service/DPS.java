package dormitoy_placement.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public interface DPS extends Remote {
    String[] Login(String username , String password) throws RemoteException, SQLException;
    String Register(String username,String password,String type) throws RemoteException, SQLException;
   List<String> getDorm() throws RemoteException,SQLException;

   String  Book(Integer id,String username) throws RemoteException,SQLException;
   List<String> getRequest() throws RemoteException,SQLException;
   String setRequest(Integer id,Integer req) throws  RemoteException,SQLException;

   String viewDorm(String username) throws RemoteException,SQLException;

   String addDorm(String blockname) throws  RemoteException,SQLException;







}
