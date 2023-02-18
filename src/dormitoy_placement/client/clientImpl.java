package dormitoy_placement.client;

import dormitoy_placement.service.DPS;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clientImpl {
    private DPS server;
    List<String> list = new ArrayList<String>();
    List<String> req = new ArrayList<String>();

    public clientImpl() {}
    public void startClient () throws RemoteException, NotBoundException {
         Registry registry= LocateRegistry.getRegistry("localhost",1098);
         server=(DPS) registry.lookup("Server");
     }

public String[] Login (String username,String password){
     String[] res= new String[4];
try {
      res=server.Login(username,password);
}catch (RemoteException e) {
        e.printStackTrace();
        throw new RuntimeException("could not contact server");
    } catch (SQLException e) {
    throw new RuntimeException(e);
}
    return res;
}

public String Register (String username,String password,String type){
        String res=null;
        try {
            res=server.Register(username,password,type);
        }catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("could not contact server");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
  public List<String> getDorm(){
      try {
       list= server.getDorm();
      }catch (RemoteException e) {
          e.printStackTrace();
          throw new RuntimeException("could not contact server");
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
      return list;
  }

  public String  Book(Integer  id,String username){
        String res=null;
      try {
         res=server.Book(id,username);
      }catch (RemoteException e) {
          e.printStackTrace();
          throw new RuntimeException("could not contact server");
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
   return res;
  }
  public List<String> getRequest(){
        try {
            req= server.getRequest();
        }catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("could not contact server");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return req;
    }
    public String  setRequest(Integer id,Integer req){
        String res=null;
        try {
            res=server.setRequest(id,req);
        }catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("could not contact server");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public String viewDorm(String username){
        String res=null;
        try {
            res= server.viewDorm(username);
        }catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("could not contact server");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String addDorm(String blockname) {
        String res=null;
        try {
            res=server.addDorm(blockname);
        }catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("could not contact server");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String manageAccount (String username , Integer status){
        String res=null;
        try {
            res=server.managAccout(username,status);
        }catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("could not contact server");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }




}
