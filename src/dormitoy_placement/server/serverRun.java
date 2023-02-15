package dormitoy_placement.server;

import dormitoy_placement.service.DPS;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class serverRun   {
    public static void main(String[] args) throws SQLException, RemoteException, ClassNotFoundException, AlreadyBoundException {
        DPS server =new Serverimpl();

        Registry registry = LocateRegistry.createRegistry(1098);
        registry.bind("Server", server);
        System.out.println("server started");
    }
}
