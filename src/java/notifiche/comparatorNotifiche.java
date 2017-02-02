package notifiche;

import java.util.Comparator;

/**
 * Ordina le notifiche in ordine temporale
 *
 * @author postal
 */
public class comparatorNotifiche implements Comparator<Notifica>{
    /**
     * compara notifiche inbase alla data
     * 
     * @param n1
     * @param n2
     * @return 
     */
    @Override
    public int compare(Notifica n1, Notifica n2) {
        Integer r;
        if( n2.getData().compareTo(n1.getData())>0) r =1;
            else if( n2.getData().compareTo(n1.getData())<0) r =-1;
                else r=0;
        return r;
    }

    
    
    
    
    
}
