package restaurants;

/**
 *
 * @author stefano
 */
public class Price_range {
    private Integer id_restaurant;
    private Double min_value;
    private Double max_value;

    public Price_range(Integer id_restaurant, Double min_value, Double max_value) {
        this.id_restaurant = id_restaurant;
        this.min_value = min_value;
        this.max_value = max_value;
    }

    public Price_range(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public Double getMax_value() {
        return max_value;
    }

    public Double getMin_value() {
        return min_value;
    }

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public void setMax_value(Double max_value) {
        this.max_value = max_value;
    }

    public void setMin_value(Double min_value) {
        this.min_value = min_value;
    }
}
