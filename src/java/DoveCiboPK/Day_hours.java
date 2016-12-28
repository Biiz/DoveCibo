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
public class Day_hours {

    private Integer id;
    private String startM;
    private String endM;
    private String startP;
    private String endP;

    public Day_hours(String startM, String endM, String startP, String endP) {
        this.startM = startM;
        this.endM = endM;
        this.startP = startP;
        this.endP = endP;
    }

    public Day_hours(Integer id, String startM, String endM, String startP, String endP) {
        this.id = id;
        this.startM = startM;
        this.endM = endM;
        this.startP = startP;
        this.endP = endP;
    }
    
    

    public Day_hours(Integer id) {
        this.id = id;
    }
    

    public String getEndM() {
        return endM;
    }

    public String getEndP() {
        return endP;
    }

    public Integer getId() {
        return id;
    }

    public String getStartM() {
        return startM;
    }

    public String getStartP() {
        return startP;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

}
