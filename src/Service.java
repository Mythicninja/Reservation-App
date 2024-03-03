
public class Service {

    private static int MAXDATES = 10;
    private static int MAXSLOTS = 10;
    private static int MAXINDICATOR = 2;
    private static int UNAVAILIBLE = 0;
    private static int key = 2023; // used for staff to access create/edit service

    private String name;
    private String serviceType;
    private int numDates;
    private int[][] slot;
    
    public Service(String s, String sType){
        // each service will at minimum have a name and a service 
        this.name = s;
        this.serviceType = sType;
        this.numDates = MAXDATES;
        this.slot = new int[MAXDATES][MAXSLOTS];
        // populate a blank array 
        for (int i = 0; i < MAXDATES; i++) {
            for (int j = 0; j < MAXSLOTS; j++) {
                slot[i][j] = UNAVAILIBLE;
            }
        }
        
    }

    public Service(String s, String sType, int mDate, int mSlots){
        // each service will at minimum have a name and a service 
        this.name = s;
        this.serviceType = sType;
        this.numDates = MAXDATES;
        this.slot = new int[mDate][mSlots];
        // populate a blank array 
        for (int i = 0; i < mDate; i++) {
            for (int j = 0; j < mSlots; j++) {
                slot[i][j] = UNAVAILIBLE;
            }
        }
    }

    public void setMaxDates(int maxDates) {
        int[][] res = new int[maxDates][this.slot[0].length];
        //check if wanting more dates or less
        if (maxDates > this.slot.length) { // if more dates than prior array
            // copy all of old contents to new array and populate new array with blank slots
            for(int i = 0; i < this.slot.length; i++) {
                res[i] = this.slot[i].clone();
            }
            // populate rest of array
            for (int i = this.slot.length + 1; i < maxDates; i++) {
                for (int j = 0; j < this.slot[0].length; j++) {
                    res[i][j] = UNAVAILIBLE;
                }
            }
        }
        else { // if less that prior number of dates
            // copy info from original array to new one until maxDates reached
            for (int i = 0; i < maxDates; i++) {
               for (int j = 0; j < MAXSLOTS; j++) {
                 res[i][j] = slot[i][j];
               }
            }
        }
        this.numDates = res.length;
        setSlot(res);
    }

    public void setMaxSlots(int maxSlots) {
        // add tail 
        for (int i = 0; i < MAXDATES; i++) {
            slot[i][maxSlots] = MAXINDICATOR;
        }
        setSlot(slot);
    }

    public int getNumDates() {
        return this.numDates;
    }

    public static int getMaxSlots() {
        return MAXSLOTS;
    }

    public String getType() {
        return this.serviceType;
    }

    public String getName() {
        return this.name;
    }

    public int[][] getSlot() {
        return this.slot;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setSlot(int[][] s) {
        this.slot = s;
    }

    public static int getKey() {
        return key;
    }
}
