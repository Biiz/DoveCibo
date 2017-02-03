package restaurants;

/**
 * Contiene i metodi per la gestione delle tipologie di cucine
 *
 * @author stefano
 */
public class Cusine {
    private Integer id;
    private String name;

    /**
     * Costruttore Cuisine
     *
     * @param id id tipologia di cucina
     */
    public Cusine(Integer id) {
        this.id = id;
    }
    
    /**
     * Costruttore cuisine
     *
     * @param name nome della tipologia di cucina
     */
    public Cusine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
