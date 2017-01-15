/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.sql.Date;

/**
 *
 * @author postal
 */
public class Notifica {
    
    private String descrizione;
    private Date data;
    private String tipo;
    private Photo foto;
    private Integer idGen;
    private User user; 

    public Notifica(String descrizione, Date data, String tipo, Integer idGen, User user) {
        this.descrizione = descrizione;
        this.data = data;
        this.tipo = tipo;
        this.idGen = idGen;
        this.user = user;
    }

    public Notifica(String descrizione, Photo foto, String tipo, Integer idGen, User user) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.foto = foto;
        this.idGen = idGen;
        this.user = user;
    }
    


    public Date getData() {
        return data;
    }

    public String getDescrizione() {
        return descrizione;
    }


    protected void setData(Date data) {
        this.data = data;
    }

    protected void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    protected void setFoto(Photo foto) {
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getIdGen() {
        return idGen;
    }
    
    
    
    
    
        
    
    
}
