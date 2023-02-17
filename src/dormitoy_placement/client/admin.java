//package dormitoy_placement.client;
//
//import java.util.Scanner;
//
//public class admin {
//    clientImpl client = new clientImpl();
//    Scanner inn = new Scanner(System.in);
//
//    public admin (){
//          while (true) {
//      //Creating menu
//      System.out.println("Press 1 for Add user");
//      System.out.println("Press 2 for Manage account");
//      System.out.println("Press 3 to Quit\n \n ");
//
//      //Asking user to make choice
//      System.out.println("Make your choice");
//              int choice = Integer.parseInt(inn.nextLine());
//
//
//                if( choice ==1) {
//                    System.out.println("Add user....");
//                    System.out.println("Enter Username");
//
//                    String name = inn.nextLine();
//
//                    System.out.println("Enter Password");
//                   String pass = inn.nextLine();
//
//                    System.out.println("Enter Type");
//
//                    System.out.println("Press 1 for Admin");
//                    System.out.println("Press 2 for Student");
//                    System.out.println("Press 3 to for proctor");
//                    int typechoice = Integer.parseInt(inn.nextLine());
//                    String type = "";
//                  if(typechoice == 1) {
//                      type=new String("admin");
//
//                  }
//                  else if(typechoice == 2) {
//                      type = new String("student");
//                  }
//                  else if (typechoice == 3){
//                            type = new String("procter");
//
//                    }else{
//                      System.out.println("please make a valid choice");
//
//                  }
//                  String result=null;
//                  try {
//                      result= client.Register();
//
//                  }catch (Exception e){
//                      System.out.println("Error:" + e.getMessage());
//                  }
//                     System.out.println("user"+name+ "pass"+pass+"type"+type);
//                }else if(choice ==2) {
//                    System.out.println("Press 2 for Manage account");
//
//                }else if(choice == 3) {
//                    System.exit(0);
//
//                }else {
//                    System.out.println("Invalid choice!!! Please make a valid choice. \\n\\n");
//                }
//
//  }
//
//         }
//    }
//
//
//
//
//
//
