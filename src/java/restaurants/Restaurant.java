package restaurants;

import java.util.ArrayList;
import java.util.List;
import restaurant_comments.Review;
import restaurant_photos.Photo;
import users.User;

/**
 * Contiene i metodi per la gestione dei ristoranti
 *
 * @author stefano
 */
public class Restaurant {
    private Integer id;
    private String name;
    private String description;
    private String web_site_url;
    private Float global_value;
    private Integer n_reviews;
    private ArrayList<User> owners = new ArrayList<User>();
    private User creator;
    private Price_range price_range;
    private Day_hours day_hours;
    private Coordinate cordinate;
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private ArrayList<Cusine> cusines = new ArrayList<Cusine>();

    /**
     * Costruttore ristorante
     *
     * @param id id ristorante
     */
    public Restaurant(Integer id) {
        this.id = id;
    }

    /**
     * Costruttore ristorante
     *
     * @param id id ristorante
     * @param name nome ristorante
     * @param description descrizione ristorante
     * @param web_site_url sito web del ristorante
     * @param creator creatore del ristorante nel sito
     * @param cordinate coordinate geografiche del ristorante
     * @param price_range fascia di przzi del ristorante
     * @param day_hours orari di apertura del ristorante
     */
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void addOwner(User owner) {
        this.owners.add(owner);
    }

    public void setN_reviews(Integer n_reviews) {
        this.n_reviews = n_reviews;
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

    public Float getGlobal_value() {
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

    public void setPhoto(Photo photo) {
        this.photos.add(photo);
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    /**
     *
     * @param user utente da testare
     * @return true se l'utente e' il proprietario del ristorante, false altrimenti
     */
    public Boolean isOwner(User user){
        for (User uf : owners) {
            if(uf.getId() == user.getId()) return true;
        }
        return false;
    }
    
    /**
     * Set di piu' dati assieme
     *
     * @param description descrizione del ristorante
     * @param global_value voto globale ristorante
     * @param price_range fascia di prezzo ristorante
     * @param name nome ristorante
     * @param web_site_url sito web del ristorante
     * @param creator utente creatore del ristorante
     * @param c coordinate del ristorante
     * @param dh orari di apertura del ristorante
     */
    public void setAltro(String description, Float global_value, 
            Price_range price_range, String name, String web_site_url, User creator, Coordinate c, Day_hours dh) {
        this.description = description;
        this.global_value = global_value;
        this.creator = creator;
        this.price_range = price_range;
        this.name = name;
        this.web_site_url = web_site_url;
        this.cordinate = c;
        this.day_hours = dh;
    }
       
    /**
     * Ritorna stringa di descrizione di un ristorante
     *
     * @return stringa che riassume le informazioni del ristorante
     */
    public String RestDescriptionToText(){
        //ottengo la stringa degli orari di apertura
        String hours_description = this.day_hours.DayHoursDescriptionToText();
        //Stringa descrittiva del ristorante, mostrata nel QR
        String description =
                  "Nome: " + this.getName()
                + " - Indirizzo: " + this.cordinate.getAdrers()
                + " " + this.cordinate.getNumero()
                + ", " + this.cordinate.getCity()
                + ", " + this.cordinate.getNazione()
                + hours_description;
        
        return description;
    }
}
