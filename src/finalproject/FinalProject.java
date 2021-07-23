package finalproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FinalProject {

    public static void main(String[] args) {
        
        /**
         * Made by programmers [ Husam , Suhaib & Ibraheem ]
         */

        /**
         * Let Kamal is the manager & his password is 111
         * let Ahmed is an employee & his password is 222
         * let Hasan is an employee & his password is 333
         * 
         */

        System.out.println("Let Kamal is the manager & his password is 111\n"
                + "let Ahmed is an employee & his password is 222\n"
                + "let Hasan is an employee & his password is 333");
        
        ArrayList<Users> users = new ArrayList<>();  // Array list Users { Store managers and employees}

        Users Emp1 = new Users("Hasan", "333", false, true);//Employee
        users.add(Emp1);

        Users Emp2 = new Users("Ahmed", "222", false, true);//Employee
        users.add(Emp2);

        Users Emp3 = new Users("Kamal", "111", true, true);//Admin
        users.add(Emp3);

        Scanner scan = new Scanner(System.in); //Scanner

        String inputUser = ""; //Entrance from the user to know what he wants to do

        int cardNumber = 1; //Customer cards counter 

        Queue<Integer> cards = new LinkedList<>(); //Queue Cards { Store customer card numbers }

        Queue<Customers> unserved_clients_queue = new LinkedList<>();//Queue { Storage of underserved customers }

        Stacks<Customers> Served_clients_stack = new Stacks<>();//Stack { Storage of serviced customers }

        ArrayList<Customers> all_clints_list = new ArrayList<>();//Array list { Store all customers Served and Unserved customers }

        String nameEmployee1 = users.get(0).getName();//Hasan
        String nameEmployee2 = users.get(1).getName();//Ahmed

        BTree<Object> tree = new BTree<>();

        tree.addRoot("Kamal");
        tree.addLeft(tree.getPosition("Kamal"), nameEmployee1);//users.get(0).getName() == Hasan
        tree.addRight(tree.getPosition("Kamal"), nameEmployee2);//users.get(1).getName() == Ahmed

        int counterEmployee1 = 1;
        int counterEmployee2 = 1;
        
        boolean showMassage=false;

        do {
            System.out.println("======================================================================");
            System.out.println("\t\t     >>>>>> Welcome to Jawwal <<<<<<");
            System.out.println("Press { 0 } to get your waiting ID: ");
            System.out.println("If you are a registered employee or manegers enter your { UserName }: ");
            System.out.println("If you want to exit the system, enter { Exit }: ");
            System.out.println("======================================================================");
            System.out.print("\nEnter your choice: ");
            inputUser = scan.nextLine();
           
            if (inputUser.equalsIgnoreCase("0")) {
                System.out.println("Your waiting ID is " + cardNumber);
                cards.add(cardNumber);
                cardNumber++;
            } else if (!inputUser.equalsIgnoreCase("0") && !inputUser.equalsIgnoreCase("Exit")) {
                System.out.print("Hi " + inputUser + " , now enter your password:");
                String password = scan.nextLine();
                for (int x = 0; x < users.size(); x++) {
                    if (inputUser.equalsIgnoreCase(users.get(x).getName()) && password.equalsIgnoreCase(users.get(x).getPassword())) {
                            showMassage=false;
                        Users u = users.get(x);
                        if (!u.IsAdmin() && u.IsActive()) {
                            boolean bEmployee = true;
                            while (bEmployee) {
                                System.out.println("---------------------------------------");
                                System.out.println("1- Serve the next customer: ");
                                System.out.println("2- Check last served customer: ");
                                System.out.println("3- View all served customers: ");
                                System.out.println("4- View a served customer issue by ID: ");
                                System.out.println("5- Exit: ");
                                System.out.println("---------------------------------------");
                               
                                System.out.print("Enter your choice: ");

                                int numberSelected = 0;
                                try {
                                    numberSelected = scan.nextInt();
                                    
                                } catch (Exception e) {
                                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
                                    System.out.println("you entered invalid value");
                                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
                                }

                                scan.nextLine();
                                if (numberSelected == 1) {

                                    if (cards.size() != 0) {
                                        int numberCard = cards.poll();
                                        System.out.println("Customer service " + numberCard);
                                        System.out.print("Enter the customer's name: ");
                                        String name = scan.nextLine();
                                        System.out.print("Enter the customer's id: ");
                                        String id = scan.nextLine();
                                        System.out.print("Enter the customer's address: ");
                                        String adress = scan.nextLine();
                                        System.out.print("Enter the customer's problem: ");
                                        String problem = scan.nextLine();
                                        System.out.print("Enter the customer's Status { Yes=1 , No=0 }: ");
                                        int status = scan.nextInt();
                                        Customers c = new Customers(name, id, adress, problem, status, inputUser);
                                        c.setNumberCard(numberCard);

                                        all_clints_list.add(c);

                                        if (status == 1) {
                                            Served_clients_stack.push(c);
                                            // Start Tree
                                            if (inputUser.equalsIgnoreCase(nameEmployee1)) {
                                                if (counterEmployee1 == 1) {
                                                    tree.addLeft(tree.getPosition(nameEmployee1), c);
                                                    counterEmployee1++;
                                                } else if (counterEmployee1 == 2) {
                                                    tree.addRight(tree.getPosition(nameEmployee1), c);
                                                }

                                            } else if (inputUser.equalsIgnoreCase(nameEmployee2)) {
                                                if (counterEmployee2 == 1) {
                                                    tree.addLeft(tree.getPosition(nameEmployee2), c);
                                                    counterEmployee2++;
                                                } else if (counterEmployee2 == 2) {
                                                    tree.addRight(tree.getPosition(nameEmployee2), c);
                                                }
                                            }
                                            // End Tree

                                        } else if (status == 0) {
                                            unserved_clients_queue.add(c);
                                        }

                                    } else {
                                        System.out.println(">>>>> There are no customers to serve <<<<< ");
                                    }

                                } else if (numberSelected == 2) {
                                    if (Served_clients_stack.isEmpty()) {
                                        System.out.println(">>>>> There is no customers served <<<<<");

                                    } else {
                                        Customers c = Served_clients_stack.peek();
                                        String name = c.getName();
                                        int idNumber = c.getNumberCard();
                                        System.out.println("You served " + name + " with ID number " + idNumber + "\n");
                                        System.out.println(c.toString());
                                    }

                                } else if (numberSelected == 3) {
                                    System.out.println(">>>>> You served " + Served_clients_stack.getSize() + " customer <<<<<");
                                    for (int s = 0; s < all_clints_list.size(); s++) {
                                        if(all_clints_list.get(s).getStatus()==1){
                                        System.out.println(all_clints_list.get(s).toString());
                                        }
                                    }

                                } else if (numberSelected == 4) {
                                    if (all_clints_list.isEmpty()) {
                                        System.out.println(">>>>> There is no customer to search for <<<<<");
                                    } else {
                                        String massage = "";
                                        System.out.print("Enter the customer name or ID: ");
                                        String name_OR_id = scan.nextLine();
                                        for (int d = 0; d < all_clints_list.size(); d++) {
                                            if (name_OR_id.equalsIgnoreCase(all_clints_list.get(d).getName()) || name_OR_id.equalsIgnoreCase(all_clints_list.get(d).getId())) {
                                                massage = all_clints_list.get(d).toString();
                                                break;
                                            } else if (!(name_OR_id.equalsIgnoreCase(all_clints_list.get(d).getName()) || name_OR_id.equalsIgnoreCase(all_clints_list.get(d).getId()))) {
                                                massage = "\n>>>>> There is no customer with this name or ID <<<<<";
                                            }
                                        }
                                        System.out.println(massage);
                                    }

                                } else if (numberSelected == 5) {
                                    bEmployee = false;
                                }
                            }

                        } else if (!u.IsAdmin() && !u.IsActive()) {
                            System.out.println(">>>>> The employee is disabled <<<<<");
                        } else if (u.IsAdmin() && u.IsActive()) {
                            boolean bAdmin = true;
                            while (bAdmin) {
                                System.out.println("---------------------------------------------------------");
                                System.out.println("1- View customers queue: ");
                                System.out.println("2- Check last served customer and by whom he was served: ");
                                System.out.println("3- View all customers issues: ");
                                System.out.println("4- Manage employees: ");
                                System.out.println("5- View Customer Service Tree: ");
                               
                                System.out.println("6- Exit");
                                System.out.println("---------------------------------------------------------");
                                System.out.print("Enter your choice: ");
                                int numberSelected = 0;
                                try {
                                    numberSelected = scan.nextInt();
                                    
                                } catch (Exception e) {
                                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
                                    System.out.println("you entered invalid value");
                                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
                                }

                                scan.nextLine();
                                if (numberSelected == 1) {

                                    System.out.println("There are " + unserved_clients_queue.size() + " customers on the waiting list");
                                    Iterator itr = unserved_clients_queue.iterator();
                                    while (itr.hasNext()) {

                                        System.out.println(itr.next().toString());

                                    }
                                } else if (numberSelected == 2) {
                                    if (Served_clients_stack.isEmpty()) {
                                        System.out.println("There is no customer to display");
                                    } else {
                                        Customers c = Served_clients_stack.peek();
                                        System.out.println("\nLast served customer was " + c.getName() + "." + c.getNameEmployee() + " served him.");
                                    }

                                } else if (numberSelected == 3) {
                                    System.out.println("We served " + Served_clients_stack.getSize() + " customers today:");
                                    for (int d = 0; d < all_clints_list.size(); d++) {
                                        if (all_clints_list.get(d).getStatus() == 1) {
                                            System.out.println(all_clints_list.get(d).toString2());
                                        } else if (all_clints_list.get(d).getStatus() == 0) {
                                            System.out.println(all_clints_list.get(d).toString());
                                        }
                                    }
                                } else if (numberSelected == 4) {

                                    for (int s = 0; s < users.size(); s++) {
                                        if (users.get(s).IsAdmin() != true) {
                                            System.out.println("Employee: " + users.get(s).toString());
                                        }
                                    }
                                    System.out.println("--------------------------------------------");
                                    System.out.println("What do you want to do with this Employee ? ");
                                    System.out.println("1- Disable/Enable user");
                                    System.out.println("2- Change Name");
                                    System.out.println("3- Exit");
                                    System.out.println("--------------------------------------------");
                                    System.out.print("Enter your choice: ");
                                    int option = 0;
                                    try {
                                        option = scan.nextInt();
                                    } catch (Exception e) {
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");
                                        System.out.println("you entered invalid value");
                                        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-");

                                    }

                                    if (option == 1) {
                                        scan.nextLine();
                                        System.out.print("Enter the name employee: ");
                                        String nameEmployee = scan.nextLine();
                                        for (int c = 0; c < users.size(); c++) {
                                            if (nameEmployee.equalsIgnoreCase(users.get(c).getName())) {
                                                Users user = users.get(c);
                                                System.out.println("Employee: " + users.get(c).toString());
                                                System.out.print("\nDisable or Enable: ");
                                                String active = scan.nextLine();
                                                if (active.equalsIgnoreCase("Disable")) {
                                                    user.setIsActive(false);
                                                } else if (active.equalsIgnoreCase("Enable")) {
                                                    user.setIsActive(true);
                                                }
                                                users.set(c, user);

                                            }
                                        }

                                    } else if (option == 2) {
                                        scan.nextLine();
                                        System.out.print("Enter the name employee: ");
                                        String nameEmployee = scan.nextLine();
                                        for (int c = 0; c < users.size(); c++) {
                                            if (nameEmployee.equalsIgnoreCase(users.get(c).getName())) {
                                                Users user = users.get(c);
                                                System.out.println("Employee: " + users.get(c).toString());
                                                System.out.print("Enter the name new: ");
                                                String namenew = scan.nextLine();

                                                user.setName(namenew);
                                                users.set(c, user);
                                                System.out.println("Employee data after name change: " + users.get(c).toString());
                                                if (nameEmployee.equalsIgnoreCase(nameEmployee1)) {
                                                    tree.set(tree.getPosition(nameEmployee1), namenew);
                                                    nameEmployee1 = namenew;
                                                } else if (nameEmployee.equalsIgnoreCase(nameEmployee2)) {
                                                    tree.set(tree.getPosition(nameEmployee2), namenew);
                                                    nameEmployee2 = namenew;
                                                }

                                            }
                                        }
                                    } else if (option == 3) {
                                        scan.nextLine();
                                        break;

                                    }
                                } else if (numberSelected == 5) {
                                    tree.preOrder(tree.root());
                                } else if (numberSelected == 6) {
                                    bAdmin = false;
                                }
                            }
                        }
                            break;
                    } else if(!inputUser.equalsIgnoreCase(users.get(x).getName()) || !password.equalsIgnoreCase(users.get(x).getPassword())) {
                            showMassage=true;
                        continue;
                        

                    }
                }
                if(showMassage){
                    System.out.println("There is a problem in your password or your name");
                }
            }
            

        } while (!inputUser.equalsIgnoreCase("Exit"));

    }

}
