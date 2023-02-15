package dormitoy_placement.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface DPS extends Remote {
    String LoginAsAdmin(String username) throws RemoteException, SQLException;
}
