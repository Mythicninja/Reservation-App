import java.util.*;
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);
        User u = new User("test");
        int menu = 0;
        System.out.println("Welcome User " + u + ", please select one of the following below:");
        while (menu != 5) {
            System.out.println("press 1 to create reservation, 2 for editing a reservation, 3 for creating a service, 4 for editing a service, and 5 for quitting the program");
            menu = sc.nextInt();
            switch (menu) {
                case 1: // create reservation, use controller to pass through
                    String service;
                    int date;
                    Service ser;
                    while (true) {
                        System.out.print("Enter which service you would like to reserve: ");
                        sc.nextLine();
                        service = sc.nextLine();
                        ser = controller.getService(service);
                        if (ser != null) {
                            //enter two ints higher than 0
                            System.out.print("Enter a day: 0 - " + ser.getNumDates());
                            date = sc.nextInt();
                            Random rand = new Random();
                            int id = rand.nextInt();
                            if (controller.createReservation(u, ser, date, 1, id) != -1) {
                                System.out.println("Successfully created a reservation!");
                            }
                            else {
                                System.out.println("Couldn't create a reservation.");
                            }
                            break;
                        }
                        else {
                            System.out.println("Invalid Service!");
                        }
                    }
                    break;
                case 2: // edit reservation, use controller to pass through
                    
                    break;
                case 3: // create a service.. for this one, they need to enter a 'key', provided in the service class
                    int key;
                    String service3;
                    String type;
                    int date3;
                    int slot;
                    int custom;
                    System.out.print("Please enter the required key: ");
                    key = sc.nextInt();
                    if (key == controller.getKey()) {
                        System.out.print("Success, please provide the name of the new service: ");
                        sc.nextLine();
                        service3 = sc.nextLine();
                        System.out.print("Please provide the type of service: ");
                        type = sc.nextLine();
                        System.out.print("Custom dates/slots? (1 for yes, 0 for no): ");
                        custom = sc.nextInt();
                        if (custom == 1) {
                            System.out.print("Please provide the number of days: ");
                            date3 = sc.nextInt();
                            System.out.print("Please provide the type of service: ");
                            slot = sc.nextInt();
                            if (controller.createService(service3, type, date3, slot) != -1) {
                                System.out.println("Successfully created a service!");
                            }
                            else {
                                System.out.print("Couldn't create a service.");
                            }
                        }
                        else {
                            if (controller.createService(service3, type, 0, 0) != -1) {
                                System.out.println("Successfully created a service!");
                            }
                            else {
                                System.out.print("Couldn't create a service.");
                            }
                        }
                    }
                    else {
                        System.out.println("Invalid key.");
                    }
                    break;
                case 4: // edit service.. for this one, they need to enter a 'key', provided in the service class
                    int key2;
                    System.out.print("Please enter the required key: ");
                    key2 = sc.nextInt();
                    if (key2 == controller.getKey()) {
                        System.out.print("Success, please provide the name of the service: ");
                        
                    }
                    else {
                        System.out.println("Invalid key.");
                    }
                    break;
                default:    // any of the 4 was not pressed
                    break;
            }
        }
        sc.close();
        // exit program
    }
}
