package posty;

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

    private Integer id;

    private Double min_value;
    private Double max_value;

    public Price_range(Integer id, Double min_value, Double max_value) {
        this.id = id;

        this.min_value = min_value;
        this.max_value = max_value;
    }

    public Price_range(Integer id) {
        this.id = id;
    }

    public Double getMax_value() {
        return max_value;
    }

    public Double getMin_value() {
        return min_value;
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    protected void setMax_value(Double max_value) {
        this.max_value = max_value;
    }

    protected void setMin_value(Double min_value) {
        this.min_value = min_value;
    }

}
