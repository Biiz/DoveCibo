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
public class Review {

    private Integer id;
    private Integer global_value;
    private Integer food;
    private Integer service;
    private Integer value_of_money;
    private Integer atmosphere;
    private String name;
    private String description;
    private Date date_creation;
    private Integer like;
    private User creator;
    private Replies repile;

    public Review(Integer id, Integer global_value, Integer food, Integer service, Integer value_of_money, Integer atmosphere, String name, String description, Date date_creation, Integer like, User creator) {
        this.id = id;
        this.global_value = global_value;
        this.food = food;
        this.service = service;
        this.value_of_money = value_of_money;
        this.atmosphere = atmosphere;
        this.name = name;
        this.description = description;
        this.date_creation = date_creation;
        this.like = like;
        this.creator = creator;
    }

    public Review(Integer id) {
        this.id = id;
    }
    
    

    public Integer getId() {
        return id;
    }

    public Integer getLike() {
        return like;
    }

    public void setRepile(Replies repile) {
        this.repile = repile;
    }

    public Integer getAtmosphere() {
        return atmosphere;
    }

    public User getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Integer getFood() {
        return food;
    }

    public Integer getService() {
        return service;
    }

    public Integer getValue_of_money() {
        return value_of_money;
    }


    public Integer getGlobal_value() {
        return global_value;
    }

    public Date getDate_creation() {
        return date_creation;
    }
    
    

    protected void setId(Integer id) {
        this.id = id;
    }

            
            
            

}
