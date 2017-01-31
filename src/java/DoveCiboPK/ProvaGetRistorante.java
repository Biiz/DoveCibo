package DoveCiboPK;

import database.DB_Coordinate;
import database.DB_GestioneRestaurant;
import database.DB_GestioneUser;
import database.DB_Manager;
import database.DB_RestaurantOwner;
import database.DB_Reviews;
import java.sql.SQLException;

/**
 *
 * @author postal
 */
public class ProvaGetRistorante {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
       
        
        
        //PIGLIARE ID RISTORANTE
        Integer idR = 3; //MOMENTANEO
        Restaurant rest = new Restaurant(idR);
        
        
        DB_Manager dbm = new DB_Manager();
            
            if( new DB_GestioneRestaurant().cercaRistorante_perId(rest)){

                    
                    if( ! new DB_GestioneUser().cercaUser_perId(rest.getCreator()))
                        System.out.println("ERROE2");
                    System.out.println("id ho"+ rest.getDay_hours().getId_restaurant());
                    
                    
                    
                    if( ! new DB_Coordinate().cercaCoordinate_perId(rest.getCordinate()))
                        System.out.println("ERROE5");
                    
                    if( ! new DB_Reviews().setCommenti_perRistorante(rest))
                        System.out.println("ERROE6");
                    
                    new DB_RestaurantOwner().cercaOwners_perRistoranti(rest);

                    System.out.println("ID r " + rest.getId());
                    System.out.println("ID o " + rest.getOwners().size());
                    
                    System.out.println("nik cr " + rest.getCreator().getNickname());
                    //System.out.println("end m mar " + rest.getDay_hours().getEndM());
                    System.out.println("coordinate " + rest.getCordinate().getAdrers());
                    System.out.println("Commento " + rest.getReviews().size());
                
                
            }else{
                System.out.println("ERROE555");
            }
        
        
        
    }
    
}
