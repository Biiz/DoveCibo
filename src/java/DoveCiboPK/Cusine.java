package DoveCiboPK;

/**
 *
 * @author stefano
 */
public class Cusine {
    private Integer id;
    private String name;

    public Cusine(Integer id) {
        this.id = id;
    }
    
    public Cusine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
