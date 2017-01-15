package DoveCiboPK;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;

/**
 *
 * @author stefano
 */
public class Replies {

    private Integer id;
    private String description;
    private Date date_creation;
    private Date date_validation;
    private User validator;
    private User owner;

    public Replies(String description) {
        this.description = description;
    }

    public Replies(Integer id, String description, Date date_creation, Date date_validation, User validator, User owner) {
        this.id = id;
        this.description = description;
        this.date_creation = date_creation;
        this.date_validation = date_validation;
        this.validator = validator;
        this.owner = owner;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public Date getDate_validation() {
        return date_validation;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    protected void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public User getOwner() {
        return owner;
    }

    
    
}
