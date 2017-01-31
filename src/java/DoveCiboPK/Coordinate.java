package DoveCiboPK;

/**
 *
 * @author stefano
 */
public class Coordinate {
    private Integer id_resturant;
    private Float latitude;
    private Float longitude;
    private String adrers;
    private Integer numero;
    private String city;
    private String nazione;

    public Coordinate(Integer id_resturant) {
        this.id_resturant = id_resturant;
    }

    public Coordinate(Float latitude, Float longitude, String adrers, Integer numero, String city, String nazione) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.adrers = adrers;
        this.numero = numero;
        this.city = city;
        this.nazione = nazione;
    }

    //iniz con solo adress
    public Coordinate(Integer id_resturant, String adrers) {
        this.id_resturant = id_resturant;
        this.latitude = 0f; //da calcolare;
        this.longitude = 0f; //da calcolare;
        this.adrers = adrers;
        this.numero = numero;
        this.city = city;
        this.nazione = nazione;
    }

    public String getAdrers() {
        return adrers;
    }
    
    public Integer getNumero() {
        return numero;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getNazione() {
        return nazione;
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

    public void setId_resturant(Integer id_resturant) {
        this.id_resturant = id_resturant;
    }

    public void setAdrers(String adrers) {
        this.adrers = adrers;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}