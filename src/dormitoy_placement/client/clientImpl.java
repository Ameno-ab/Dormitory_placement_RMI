package dormitoy_placement.client;

import dormitoy_placement.service.DPS;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class clientImpl {
    private DPS server;

    public clientImpl() {}
     public void startClient () throws RemoteException, NotBoundException {
         Registry registry= LocateRegistry.getRegistry("localhost",1098);
         server=(DPS) registry.lookup("Server");
     }

public String LoginAsAdmin (String argu){
     String res=null;
try {
      res=server.LoginAsAdmin(argu);
}catch (RemoteException e) {
        e.printStackTrace();
        throw new RuntimeException("could not contact server");
    } catch (SQLException e) {
    throw new RuntimeException(e);
}
    return res;
}
}
