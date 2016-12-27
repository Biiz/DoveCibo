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
    private String azioneAccetta;
    private String azioneRifiuta;
    private Date data;
    private Photo foto;

    public Notifica(String descrizione, Date data) {
        this.descrizione = descrizione;
        this.data = data;
    }
    
    public Notifica(String descrizione, Date data, String azioneAccetta) {
        this.descrizione = descrizione;
        this.data = data;
        this.azioneAccetta = azioneAccetta;
    }
    

    public Notifica(String descrizione, Date data, String azioneRifiuta, Photo ph) {
        this.descrizione = descrizione;
        this.data = data;
        this.azioneRifiuta = azioneRifiuta;
        this.foto = ph;
    }

    public String getAzioneAccetta() {
        return azioneAccetta;
    }

    public String getAzioneRifiuta() {
        return azioneRifiuta;
    }

    public Date getData() {
        return data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    protected void setAzioneAccetta(String azioneAccetta) {
        this.azioneAccetta = azioneAccetta;
    }

    protected void setAzioneRifiuta(String azioneRifiuta) {
        this.azioneRifiuta = azioneRifiuta;
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
    
        
    
    
}
