package DoveCiboPK;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stefano
 */
public class Restaurant {

    private Integer id;
    private String name;
    private String description;
    private String web_site_url;
    private Integer global_value;
    private User owner;
    private User creator;
    private Price_range price_range;
    private Week_hours week_hours;
    private Coordinate cordinate;
    private ArrayList<Review> reviews;
    private ArrayList<Cusine> cusines = new ArrayList<Cusine>();

    public Restaurant(Integer id) {
        this.id = id;
    }

    public Restaurant(Integer id, String name, String description, String web_site_url, User creator, Coordinate cordinate, Price_range price_range, Week_hours weew_hours) {
        this.id = id;
        this.name = name;
        this.cordinate = cordinate;
        this.description = description;
        this.web_site_url = web_site_url;
        this.creator = creator;
        this.price_range = price_range;
        this.week_hours = week_hours;
        reviews = new ArrayList();

    }

    protected void setId(Integer id) {
        this.id = id;
    }

    protected void setWeek_hours(Week_hours weew_hours) {
        this.week_hours = weew_hours;
    }

    public Week_hours getWeek_hours() {
        return week_hours;
    }

    public Coordinate getCordinate() {
        return cordinate;
    }

    public ArrayList<Cusine> getCusines() {
        return cusines;
    }
    
    
    
    
    public void addCusine( Cusine c) {
        cusines.add(c);
    }    
    
    
    

    public void addReviews(Review reviews) {
        this.reviews.add(reviews);
    }

    public String getDescription() {
        return description;
    }

    public Integer getGlobal_value() {
        return global_value;
    }

    public Integer getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public Price_range getPrice_range() {
        return price_range;
    }

    public String getName() {
        return name;
    }

    public String getWeb_site_url() {
        return web_site_url;
    }

    public User getCreator() {
        return creator;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    protected void setAltro(String description, Integer global_value, User owner,
            Price_range price_range, String name, String web_site_url, User creator, Coordinate c) {
        this.description = description;
        this.global_value = global_value;
        this.owner = owner;
        this.creator = creator;
        this.price_range = price_range;
        this.name = name;
        this.web_site_url = web_site_url;
        this.cordinate = c;
    }

}