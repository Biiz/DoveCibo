package restaurants;

/**
 * Contiene i metodi per la gestione dei giorni di apertura settimanali dei ristoranti
 *
 * @author stefano
 */
public class Week_hours {
    private Day_hours[] orari = new Day_hours[7];

    /**
     * Costruttore giorni della settimana per orari
     *
     * @param orari array coi giorni di apertura settimanali
     */
    public Week_hours(Day_hours[] orari) {
        this.orari = orari;
    }

    public Week_hours() { }

    public Day_hours[] getWeek() {
        return orari;
    }
}
