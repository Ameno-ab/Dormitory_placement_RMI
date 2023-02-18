package dormitoy_placement.client;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class clientRun {

    public static void main(String[] args) throws NotBoundException, RemoteException {
       clientImpl client=new clientImpl();
        List<String> list = new ArrayList<String>();
        List<String> req = new ArrayList<String>();
       String user=null;

        client.startClient();
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.println("please login");
            System.out.println("enter username");
            String line = in.nextLine();
            System.out.println("enter password");
            String line2 = in.nextLine();

            if(line.equalsIgnoreCase("exit")) break;

            String[] result = new String[4];

           try {
            result = client.Login(line,line2);
             if(result[0].equals("true")){
                 if(result[1].equals("1")){
                     user=result[3];
            if(result[2].equals("proctor")){
                System.out.println("Logged in as Procter");
                System.out.println("Welcome\t" + user);

                while (true) {
                    //Creating menu
                    System.out.println("Press 1 for Add Block");
                    System.out.println("Press 2 for Request view");
                    System.out.println("Press 3 to Quit\n \n ");

                    //Asking user to make choice
                    System.out.println("Make your choice");
                    int choice = Integer.parseInt(in.nextLine());
                    if( choice ==1) {
                        System.out.println("Add dorm....");
                        System.out.println("Enter Block Name");

                        String blockname = in.nextLine();
                        String re=client.addDorm(blockname);
                        System.out.println(re);
                    }
                    else if(choice ==2) {
                        System.out.println("Press 2 for View Request");
                        req = client.getRequest();
                        System.out.println("Requests dorm....");
                        for (String l : req){
                            System.out.println("Requests for  rooms" + l + "\n");
                                 }
                            System.out.println("please insert the id " );
                            int reqID = Integer.parseInt(in.nextLine());
                            System.out.println("please insert the '1' for approve and '0 for reject' " );
                            int reqS = Integer.parseInt(in.nextLine());
                            String reqR= client.setRequest(reqID,reqS);
                            System.out.println(reqR);


                    }
                    else if(choice == 3) {
                        System.exit(0);

                    }
                    else {
                        System.out.println("Invalid choice!!! Please make a valid choice. \\n\\n");
                    }



                }
            }
            else if(result[2].equals("admin")) {
                System.out.println("Logged in as Admin");
                System.out.println("Welcome\t" + user);

                while (true) {
                    //Creating menu
                    System.out.println("Press 1 for Add user");
                    System.out.println("Press 2 for Manage account");
                    System.out.println("Press 3 to Quit\n \n ");

                    //Asking user to make choice
                    System.out.println("Make your choice");
                    int choice = Integer.parseInt(in.nextLine());


                    if( choice ==1) {
                        System.out.println("Add user....");
                        System.out.println("Enter Username");

                        String name = in.nextLine();

                        System.out.println("Enter Password");
                        String pass = in.nextLine();

                        System.out.println("Enter Type");

                        System.out.println("Press 1 for Admin");
                        System.out.println("Press 2 for Student");
                        System.out.println("Press 3 to for proctor");
                        int typechoice = Integer.parseInt(in.nextLine());
                        String type = "";
                        if(typechoice == 1) {
                            type=new String("admin");

                        }
                        else if(typechoice == 2) {
                            type = new String("student");
                        }
                        else if (typechoice == 3){
                            type = new String("proctor");

                        }else{
                            System.out.println("please make a valid choice");

                        }
                        String result2=null;
                        try {
                            result2= client.Register(name,pass,type);
                            System.out.println(result2);

                        }catch (Exception e){
                            System.out.println("Error:" + e.getMessage());
                        }
                        System.out.println("user"+name+ "pass"+pass+"type"+type);
                    }
                    else if(choice ==2) {
                        System.out.println("Press 2 for Manage account");
                        System.out.println("Manage user....");
                        System.out.println("Enter Username");

                        String name = in.nextLine();

                        System.out.println("press 1 for activate and 0 to deavtivate");

                        Integer sta=in.nextInt();

                        String res=client.manageAccount(name,sta);
                        System.out.println(res);



                    }
                    else if(choice == 3) {
                        System.exit(0);

                    }
                    else {
                        System.out.println("Invalid choice!!! Please make a valid choice. \\n\\n");
                    }

                }


            }
            else if(result[2].equals("student")) {
                System.out.println("Logged in as Student");
                System.out.println("Welcome \t" + user);
                while (true) {
                    //Creating menu
                    System.out.println("Press 1 for Book Dorm");
                    System.out.println("Press 2 for View Dorm");
                    System.out.println("Press 3 to Quit\n \n ");


                    //Asking user to make choice
                    System.out.println("Make your choice");
                    int choice = Integer.parseInt(in.nextLine());

                    if( choice ==1) {
                        System.out.println("Book dorm....");
                        list = client.getDorm();
                        System.out.println("Avaliable dorm....");
                        if (!list.isEmpty()) {
                            for (String l : list) {
                                System.out.println("Avalible rooms" + l + "\n");
                            }

                            System.out.println("Please enter the id of dorm to book");
                            int b = Integer.parseInt(in.nextLine());
                            String res = client.Book(b, user);
                            System.out.println(res);
                            continue;
                        } else {
                            System.out.println("NO Room Available");
                            continue;
                        }
                    }
                    else if(choice ==2) {
                         String viw=client.viewDorm(user);
                        System.out.println("Dorm location\t"+viw);


                    }
                    else if(choice == 3) {
                        System.exit(0);

                    }
                    else {
                        System.out.println("Invalid choice!!! Please make a valid choice. \\n\\n");
                    }

                }
            }
            }
            else{
                System.out.println("Account blocked please contact Admin");
            }
            }
            else{
                System.out.println("Invalid user!please try again");
            }
           }catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
}


        }
    }
}
