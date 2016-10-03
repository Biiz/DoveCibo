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
public class Coordinate {

    private Integer id_resturant;
    private Float latitude;
    private Float longitude;
    private String adrers;

    public Coordinate(Integer id_resturant) {
        this.id_resturant = id_resturant;
    }

    public Coordinate(Float latitude, Float longitude, String adrers) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adrers = adrers;
    }

    //iniz con solo adress
    public Coordinate(Integer id_resturant, String adrers) {
        this.id_resturant = id_resturant;
        this.latitude = 0f; //da calcolare;
        this.longitude = 0f; //da calcolare;
        this.adrers = adrers;
    }

    public String getAdrers() {
        return adrers;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Integer getId_resturant() {
        return id_resturant;
    }

    protected void setId_resturant(Integer id_resturant) {
        this.id_resturant = id_resturant;
    }

    protected void setAdrers(String adrers) {
        this.adrers = adrers;
    }

    protected void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    protected void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
    
    

}
