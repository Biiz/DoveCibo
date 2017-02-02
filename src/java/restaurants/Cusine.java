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
     * @param id
     */
    public Cusine(Integer id) {
        this.id = id;
    }
    
    /**
     * Costruttore cuisine
     *
     * @param name
     */
    public Cusine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
