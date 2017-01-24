package DoveCiboPK;

import java.sql.Date;

/**
 *
 * @author stefano
 */
public class Photo {
    private Integer id;
    private String name;
    private String description;
    private String path;
    private User owner;
    private Integer validation;
    private Date date_creation;

    public Photo(Integer id, String name, String description, String path, User owner, Integer validation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.owner = owner;
        this.validation = validation;
    }

    public Photo(Integer id, String name, String description, String path, User owner, Integer validation, Date date_creation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.owner = owner;
        this.validation = validation;
        this.date_creation = date_creation;
    }

    public Date getDate_creation() {
        return date_creation;
    }
    

    public Photo(Integer id) {
        this.id = id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setOwner(User owner) {
        this.owner = owner;
    }

    protected void setPath(String phat) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Integer getValidation() {
        return validation;
    }

    public void setValidation(Integer validation) {
        this.validation = validation;
    }

    public User getOwner() {
        return owner;
    }
    
    
    
}
