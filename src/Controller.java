
public class Controller {

    private DBMngr dbMgr = new DBMngr();
    
    // used to return a key needed to check if user is allowed to add/edit service
    public int getKey() { return Service.getKey(); } 
    // used to return max number of reservation a user is allowed to have
    public int getMaxReservations() { return User.getMaxReservations(); }

    public int createUser(String n) {
        User u = new User(n);
        return dbMgr.saveUser(u);
    }

    public User getUser(String n) {
        return dbMgr.getUser(n);
    }

    public int createReservation(User usr, Service ser, int dates, int slots, int id) {
        Reservation res = new Reservation(usr, ser, dates, slots, id);
        return dbMgr.saveReservation(res);
    }

    public Reservation getReservation(int id) {
        return dbMgr.getReservation(id);
    }

    public int createService(String ser, String type, int date, int slots) {
        Service s;
        if (date == 0 && slots == 0) { // non-custom dates and slots
            s = new Service(ser, type);
        }
        else {  // custom dates and slots
            s = new Service(ser, type, date, slots);
        }
        return dbMgr.saveService(s);
   }

   public Service getService(String n) {
        return dbMgr.getService(n);
   }

    public int editService(String companyName, int maxDates, int maxSlots){
        Service service = dbMgr.getService(companyName);
        service.setMaxDates(maxDates);
        service.setMaxSlots(maxSlots);
        int returnCode = dbMgr.saveService(service);
        return returnCode;
    }
    
    public int editReservation(int ID, int date, int spot){
        Reservation reservation = dbMgr.getReservation(ID);
        reservation.setDate(date);
        reservation.setSlot(spot);
        int returnCode = dbMgr.saveReservation(reservation);
        return returnCode;
    }
}
