import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Scanner;

public class DBMngr {

    private static File file = new File("DBVer1.csv");
    private static Vector<Service> services;
    private static Vector<Reservation> reservations;
    private static Vector<User> users;

    DBMngr() {
        services = new Vector<>();
        reservations = new Vector<>();
        users = new Vector<>();
        readDB();
    }

    public Vector<Service> getServices() {
        return services;
    }

    public Service getService(String name) {
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getName().equals(name))
                return services.get(i);
        }
        System.err.println("Service not found or invalid name: " + name);
        return null;
    }

    public int saveService(Service s) {
        services.add(s);
        return writeDB();
    }

    public Reservation getReservation(int id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getID() == id)
                return reservations.get(i);
        }
        System.err.println("Reservation not found or invalid id: " + id);
        return null;
    }

    public int saveReservation(Reservation r) {
        reservations.add(r);
        return writeDB();
    }

    public User getUser(String name) {
        for (int i = 0; i < services.size(); i++) {
            if (users.get(i).getName().equals(name))
                return users.get(i);
        }
        System.err.println("User not found or invalid name: " + name);
        return null;
    }

    public int saveUser(User u) {
        users.add(u);
        return writeDB();
    }

    private void readDB() {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            try { 
                file.createNewFile();
            } catch (IOException i) {
                System.err.println("File was not found and could not be created");
            }
        }
        if (sc == null) return;
        // parsing a CSV file into the constructor of Scanner class
        sc.useDelimiter(",");
        // setting comma as delimiter pattern
        while (sc.hasNext()) { // while loop
            String header = sc.next();
            if (header.contains("Services")) {
                String numServices = sc.next();
                int numberOfServices = Integer.parseInt(numServices);
                for (int i = 0; i < numberOfServices; i++) {
                    String name = sc.next().trim();
                    String serviceType = sc.next().trim();
                    String numDates = sc.next().trim();
                    int numberOfDates = Integer.parseInt(numDates);
                    int numberOfSlots = Service.getMaxSlots();
                    int[][] slots = new int[numberOfDates][numberOfSlots];
                    for (int j = 0; j < numberOfDates; j++) {
                        for (int k = 0; k < numberOfSlots; k++) {
                            String slot = sc.next().trim();
                            slots[j][k] = Integer.parseInt(slot);
                        }
                    }
                    Service s = new Service(name, serviceType, numberOfDates, numberOfSlots);
                    s.setSlot(slots);
                    services.add(s);
                }
            } else if (header.contains("Users")) {
                String numUsers = sc.next();
                int numberOfUsers = Integer.parseInt(numUsers);
                for (int i = 0; i < numberOfUsers; i++) {
                    String name = sc.next().trim();
                    User u = new User(name);
                    users.add(u);
                }
            } else if (header.contains("Reservations")) {
                String numReservations = sc.next();
                int numberOfReservations = Integer.parseInt(numReservations);
                for (int i = 0; i < numberOfReservations; i++) {
                    String serviceName = sc.next().trim();
                    String userName = sc.next().trim();
                    String sDate = sc.next();
                    int date = Integer.parseInt(sDate);
                    String sSlot = sc.next();
                    int slot = Integer.parseInt(sSlot);
                    String sID = sc.next();
                    int id = Integer.parseInt(sID);
                    Service s = getService(serviceName);
                    User u = getUser(userName);
                    Reservation r = new Reservation(u, s, date, slot, id);
                    r.setID(id);
                    u.setReservation(r); // Adding reservation to set of user's reservations
                }
            }
        }
        sc.close();
        // closes the scanner
    }
   
    private int writeDB() {
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write("Services," + services.size() + ",\n");
            for (int i = 0; i < services.size(); i++) {
                Service s = services.get(i);
                int slot[][] = s.getSlot();
                String name = s.getName();
                String type = s.getType();
                int dates = slot.length;
                writer.write(name + "," + type + "," + dates + ",\n");
                for (int j = 0; j < dates; j++) {
                    for (int k = 0; k < slot[0].length; k++) {
                        writer.write(slot[j][k] + ",");
                    }
                    writer.write("\n");
                }
            }
            writer.write("Users," + users.size() + ",\n");
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                writer.write(u.getName() + ",\n");
            }
            writer.write("Reservations," + reservations.size() + ",\n");
            for (int i = 0; i < reservations.size(); i++) {
                Reservation r = reservations.get(i);
                String service = r.getService().getName();
                String user = r.getUser().getName();
                int date = r.getDate();
                int slot = r.getSlot();
                int id = r.getID();
                writer.write(service + "," + user + "," + date + "," + slot + "," + id + ",\n");
            }
            writer.close();
            return 0;
        } catch (IOException e) {
            System.out.println("An error occurred when writing to database.");
            e.printStackTrace();
            return -1;
        }
    }
}