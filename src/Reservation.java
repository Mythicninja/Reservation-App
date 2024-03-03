public class Reservation {
    
    private int date;
    private User user;
    private Service service;
    private int slot;
    private int id;

    public Reservation(User u, Service s, int date, int slot, int id) {
        this.user = u;
        this.service = s;
        this.date = date;
        this.slot = slot;
        this.id = id;
    }

    public void setSlot(int s) {
        this.slot = s;
    }

    public void setDate(int d) {
        this.date = d;
    }

    public int getDate(){

        return date;
    }

    public int getSlot(){

        return slot;
    }

    public User getUser(){

        return user;
    }

    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }

    public Service getService() {
        return service;
    }
}
