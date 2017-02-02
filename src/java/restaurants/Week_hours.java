package restaurants;

/**
 *
 * @author stefano
 */
public class Week_hours {
    private Day_hours[] orari = new Day_hours[7];

    /**
     * Costruttore giorni della settimana per orari
     *
     * @param orari
     */
    public Week_hours(Day_hours[] orari) {
        this.orari = orari;
    }

    public Week_hours() { }

    public Day_hours[] getWeek() {
        return orari;
    }
}
