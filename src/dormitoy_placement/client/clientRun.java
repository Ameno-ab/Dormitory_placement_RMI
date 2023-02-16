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
             System.out.println("please login");
            System.out.println("enter username");
           String line = in.nextLine();
            System.out.println("enter password");
            String line2 = in.nextLine();

            if(line.equalsIgnoreCase("exit")) break;

            String result = null;
           try {
            result = client.Login(line,line2);
            if(result.equals("proctor")){
                System.out.println("Logged in as Procter");
            } else if(result.equals("admin")) {
                System.out.println("Logged in as Admin");
            }
            else if(result.equals("student")) {
                System.out.println("Logged in as Student");
            }else{
                System.out.println("Invalid user!please try again");
            }
           }catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
}


        }
    }
}
