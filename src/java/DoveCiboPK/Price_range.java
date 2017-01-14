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

    protected void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    protected void setMax_value(Double max_value) {
        this.max_value = max_value;
    }

    protected void setMin_value(Double min_value) {
        this.min_value = min_value;
    }

}
