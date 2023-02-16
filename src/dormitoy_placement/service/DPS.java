package dormitoy_placement.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface DPS extends Remote {
    String Login(String username ,String password) throws RemoteException, SQLException;
}
