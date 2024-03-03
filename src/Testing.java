import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Testing {
    User test = new User("test");
    Service s1 = new Service("testS", "testType");
    Controller con = new Controller();

    @Test
    public void correctUser() {  // ensures the controller is getting the right reservation and can retrieve the correct user
       con.createReservation(test, s1, 10, 10, 1);
       assertEquals("test", con.getReservation(1).getUser().getName());
    }
    @Test
    public void correctService() {  // ensures the controller is getting the right reservation and can retrieve the service
       con.createReservation(test, s1, 10, 10, 1);
       assertEquals("testS", con.getReservation(1).getService().getName());
    }
    @Test
    public void correctDate() {  // ensures the controller is getting the right reservation and can retrieve the dates
        con.createReservation(test, s1, 10, 10, 1);
        assertEquals(10, con.getReservation(1).getDate());
    }
    @Test
    public void correctDateDefault() {  // ensures the controller can call a create reservation funtion with default slot/date
        con.createReservation(test, s1, 0, 0, 1);
        assertEquals(10, con.getReservation(1).getDate());
    }
    @Test
    public void correctSlot() {    // ensures the controller is getting the right reservation and can retrieve the slots
        con.createReservation(test, s1, 10, 10, 1);
        assertEquals(1, con.getReservation(1).getSlot());
    }
    @Test
    public void correctSlotDefault() {  // ensures the controller can call a create reservation funtion with default slot/date
        con.createReservation(test, s1, 0, 0, 1);
        assertEquals(10, con.getReservation(1).getDate());
    }
    @Test
    public void changeReservedSlots() {    // ensures the controller is getting the right reservation and can change slots
        con.createReservation(test, s1, 10, 10, 1);
        con.editReservation(1, 10, 5);
        assertEquals(1, con.getReservation(1).getSlot());
    }
    @Test
    public void changeReservedDates() {    // ensures the controller is getting the right reservation and can change dates
        con.createReservation(test, s1, 10, 10, 1);
        con.editReservation(1, 5, 10);
        assertEquals(1, con.getReservation(1).getDate());
    }
}
