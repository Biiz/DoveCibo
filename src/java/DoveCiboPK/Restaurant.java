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
    private Integer n_reviews;
    private ArrayList<User> owners = new ArrayList<User>();
    private User creator;
    private Integer like;
    private Price_range price_range;
    private Day_hours day_hours;
    private Coordinate cordinate;
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private ArrayList<Cusine> cusines = new ArrayList<Cusine>();

    public Restaurant(Integer id) {
        this.id = id;
    }

    public Restaurant(Integer id, String name, String description, String web_site_url, User creator, Coordinate cordinate, Price_range price_range, Day_hours day_hours) {
        this.id = id;
        this.name = name;
        this.cordinate = cordinate;
        this.description = description;
        this.web_site_url = web_site_url;
        this.creator = creator;
        this.price_range = price_range;
        this.day_hours = day_hours;
        reviews = new ArrayList();

    }

    protected void setId(Integer id) {
        this.id = id;
    }

    protected void setDay_hours(Day_hours day_hours) {
        this.day_hours = day_hours;
    }

    protected void addOwner(User owner) {
        this.owners.add(owner);
    }

    protected void setLike(Integer like) {
        this.like = like;
    }

    protected void setN_reviews(Integer n_reviews) {
        this.n_reviews = n_reviews;
    }
    
    
    
    
    
    
    

    public Integer getLike() {
        return like;
    }

    public Integer getN_reviews() {
        return n_reviews;
    }
    
    public Day_hours getDay_hours() {
        return day_hours;
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

    public ArrayList<User> getOwners() {
        return owners;
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

    protected void setPhoto(Photo photo) {
        this.photos.add(photo);
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }


    
    
    
    
    

    protected void setAltro(String description, Integer global_value, 
            Price_range price_range, String name, String web_site_url, User creator, Coordinate c) {
        this.description = description;
        this.global_value = global_value;
        this.creator = creator;
        this.price_range = price_range;
        this.name = name;
        this.web_site_url = web_site_url;
        this.cordinate = c;
    }

}