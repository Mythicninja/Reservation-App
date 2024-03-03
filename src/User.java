import java.util.Vector;

public class User {
    // used to determine whether a user is allowed to continue creating reservations or not
    private static int MAXRESERVATIONS = 5;

    private String name;
    private Vector<Reservation> reservations;

    public User(String name) {
        this.name = name;
        this.reservations = new Vector<Reservation>();
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }
    public void setReservation(Reservation res) {
            this.reservations.add(res);
    }

    public Vector<Reservation> getReservation() {
       return this.reservations;
    }

    public static int getMaxReservations() {
        return MAXRESERVATIONS;
    }
}