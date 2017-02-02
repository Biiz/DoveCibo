package restaurants;

/**
 * Contiene i metodi per la gestione degli orari di apertura dei ristoranti
 *
 * @author stefano
 */
public class Day_hours {
    private Integer id_restaurant;
    private Integer start_H_M;
    private Integer start_M_M;
    private Integer end_H_M;
    private Integer end_M_M;
    private Integer start_H_P;
    private Integer start_M_P;
    private Integer end_H_P;
    private Integer end_M_P;

    /**
     * Costruttore data e ora
     *
     * @param start_H_M
     * @param start_M_M
     * @param end_H_M
     * @param end_M_M
     * @param start_H_P
     * @param start_M_P
     * @param end_H_P
     * @param end_M_P
     */
    public Day_hours(Integer start_H_M, Integer start_M_M, Integer end_H_M, Integer end_M_M, Integer start_H_P, Integer start_M_P, Integer end_H_P, Integer end_M_P) {
        this.start_H_M = start_H_M;
        this.start_M_M = start_M_M;
        this.end_H_M = end_H_M;
        this.end_M_M = end_M_M;
        this.start_H_P = start_H_P;
        this.start_M_P = start_M_P;
        this.end_H_P = end_H_P;
        this.end_M_P = end_M_P;
    }

    /**
     * Costruttore data e ora
     *
     * @param id_restaurant
     * @param start_H_M
     * @param start_M_M
     * @param end_H_M
     * @param end_M_M
     * @param start_H_P
     * @param start_M_P
     * @param end_H_P
     * @param end_M_P
     */
    public Day_hours(Integer id_restaurant, Integer start_H_M, Integer start_M_M, Integer end_H_M, Integer end_M_M, Integer start_H_P, Integer start_M_P, Integer end_H_P, Integer end_M_P) {
        this.id_restaurant = id_restaurant;
        this.start_H_M = start_H_M;
        this.start_M_M = start_M_M;
        this.end_H_M = end_H_M;
        this.end_M_M = end_M_M;
        this.start_H_P = start_H_P;
        this.start_M_P = start_M_P;
        this.end_H_P = end_H_P;
        this.end_M_P = end_M_P;
    }

    /**
     * Costruttore data e ora
     *
     * @param id_restaurant
     */
    public Day_hours(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public Integer getStart_H_M() {
        return start_H_M;
    }

    public Integer getStart_M_M() {
        return start_M_M;
    }

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public Integer getEnd_H_M() {
        return end_H_M;
    }

    public Integer getEnd_M_M() {
        return end_M_M;
    }

    public Integer getStart_H_P() {
        return start_H_P;
    }

    public Integer getStart_M_P() {
        return start_M_P;
    }

    public Integer getEnd_H_P() {
        return end_H_P;
    }

    public Integer getEnd_M_P() {
        return end_M_P;
    }

    public void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public void setStart_H_M(Integer start_H_M) {
        this.start_H_M = start_H_M;
    }

    public void setStart_M_M(Integer start_M_M) {
        this.start_M_M = start_M_M;
    }

    public void setEnd_H_M(Integer end_H_M) {
        this.end_H_M = end_H_M;
    }

    public void setEnd_M_M(Integer end_M_M) {
        this.end_M_M = end_M_M;
    }

    public void setStart_H_P(Integer start_H_P) {
        this.start_H_P = start_H_P;
    }

    public void setStart_M_P(Integer start_M_P) {
        this.start_M_P = start_M_P;
    }

    public void setEnd_H_P(Integer end_H_P) {
        this.end_H_P = end_H_P;
    }

    public void setEnd_M_P(Integer end_M_P) {
        this.end_M_P = end_M_P;
    }

    /**
     * Ritorna stringa di descrizione dell'orario, mostrata nel QR
     *
     * @return
     */
    public String DayHoursDescriptionToText() {
        //Stringa descrittiva dell'orario, mostrata nel QR
        String description = 
                  " - Orari Pranzo: "
                + "da " + this.start_H_M + ":" + this.start_M_M
                + " a " + this.end_H_M + ":" + this.end_M_M
                + " - Orari Cena: "
                + "da " + this.start_H_P + ":" + this.start_M_P
                + " a " + this.end_H_P + ":" + this.end_M_P;

        return description;       
    }
}
