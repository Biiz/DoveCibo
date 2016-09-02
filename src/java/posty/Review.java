package posty;

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
    private Double global_value;
    private Double food;
    private Double service;
    private Double value_of_money;
    private Double atmosphere;
    private String name;
    private String description;
    private Date date_creation;
    private User creator;
    private Photo photo;
    private Integer validation;
    private Replies repile;

    public Review(Integer id, Double global_value, Double food, Double service, Double value_of_money, Double atmosphere, String name, String description, Date date_creation, User creator, Photo photo, Integer validation) {
        this.id = id;
        this.global_value = global_value;
        this.food = food;
        this.service = service;
        this.value_of_money = value_of_money;
        this.atmosphere = atmosphere;
        this.name = name;
        this.description = description;
        this.date_creation = date_creation;
        this.creator = creator;
        this.photo = photo;
        this.validation = validation;
        this.repile = repile;
    }

    public void setRepile(Replies repile) {
        this.repile = repile;
    }

    public Double getAtmosphere() {
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

    public Double getFood() {
        return food;
    }

    public Double getService() {
        return service;
    }

    public Double getValue_of_money() {
        return value_of_money;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Double getGlobal_value() {
        return global_value;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

}
