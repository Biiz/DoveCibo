/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.sql.SQLException;
import jdk.nashorn.internal.runtime.RewriteException;

/**
 *
 * @author postal
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        
        Restaurant res = new Restaurant(1);
        DB_Manager d = new DB_Manager();
        if ( ! d.setCommenti_perRistorante(res)){ 
                System.out.println(d.getErrore());
        }
        
        
        for (Review rew : res.getReviews()) {
            System.out.println("name: " + rew.getName());
            new DB_Manager().setPhoto_perCommento(rew);
            System.out.println("phat: " + rew.getPhoto().getPath());
            new DB_Manager().cercaUser_perId(rew.getCreator());
            System.out.println("name creator: " + rew.getCreator().getNickname());
        }
            
        
        
        
    }
    
}
