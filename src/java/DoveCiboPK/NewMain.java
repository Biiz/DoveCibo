/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoveCiboPK;

import java.util.ArrayList;

/**
 *
 * @author postal
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
          try {


            //RICERCA DB

            DB_Manager dbm = new DB_Manager();

            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();
            
            if( new DB_Manager().tuttiRistoranti(ALR)){
                
                for(Restaurant rest : ALR){
                    
                    if( ! new DB_Manager().cercaUser_perId(rest.getOwner()))
                        System.out.println("ERROE1");
                    
                    if( ! new DB_Manager().cercaUser_perId(rest.getCreator()))
                        System.out.println("ERROE2");
                    
                    if( ! new DB_Manager().setOrariPerRistorante(rest))
                          System.out.println("ERROE3");
                    
                    if( ! new DB_Manager().cercaPriceRange_perId(rest.getPrice_range()))
                        System.out.println("ERROE4");
                    
                    if( ! new DB_Manager().cercaCoordinate_perId(rest.getCordinate()))
                        System.out.println("ERROE5");

                }
                
                
                
                for(Restaurant rest : ALR){
                    
                    System.out.println("ID r " + rest.getId());
                    System.out.println("nik ow " + rest.getOwner().getNickname());
                    System.out.println("nik cr " + rest.getCreator().getNickname());
                    System.out.println("end m mar " + rest.getWeek_hours().getWeek()[1].getEndM());
                   System.out.println("coordinate " + rest.getCordinate().getAdrers());
                }
                
            }else{
                System.out.println("ERROE555");
            }
            

        } catch (Exception ex) {
System.out.println("ERROE11111");
        }

    }
        
        
        
    }