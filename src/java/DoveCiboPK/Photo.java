package DoveCiboPK;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public Photo(Integer id, String name, String description, String path, User owner, Integer validation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.owner = owner;
        this.validation = validation;
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
