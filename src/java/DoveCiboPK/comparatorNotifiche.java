package DoveCiboPK;

import java.util.Comparator;

/**
 *
 * @author postal
 */
public class comparatorNotifiche implements Comparator<Notifica>{

    @Override
    public int compare(Notifica n1, Notifica n2) {
        Integer r;
        if( n2.getData().compareTo(n1.getData())>0) r =1;
            else if( n2.getData().compareTo(n1.getData())<0) r =-1;
                else r=0;
        return r;
    }

    
    
    
    
    
}
