package dormitoy_placement.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class clientRun {
    public static void main(String[] args) throws NotBoundException, RemoteException {
       clientImpl client=new clientImpl();
        client.startClient();
        Scanner in = new Scanner(System.in);

        while (true){
             System.out.println("usrname");
           String line = in.nextLine();
            if(line.equalsIgnoreCase("exit")) break;

            String result = null;
           try {
            result = client.LoginAsAdmin(line);
            System.out.println("username"+result);
           }catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
}


        }
    }
}
