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
    private Double latitude;
    private Double longitude;
    private String adrers;

    public Coordinate(Integer id_resturant) {
        this.id_resturant = id_resturant;
    }

    public Coordinate(Double latitude, Double longitude, String adrers) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adrers = adrers;
    }

    //iniz con solo adress
    public Coordinate(Integer id_resturant, String adrers) {
        this.id_resturant = id_resturant;
        this.latitude = 0d; //da calcolare;
        this.longitude = 0d; //da calcolare;
        this.adrers = adrers;
    }

    public String getAdrers() {
        return adrers;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getId_resturant() {
        return id_resturant;
    }

    protected void setId_resturant(Integer id_resturant) {
        this.id_resturant = id_resturant;
    }

}
