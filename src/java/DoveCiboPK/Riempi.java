package DoveCiboPK;

import database.DB_GestioneRestaurant;
import database.DB_RestaurantPhoto;
import database.DB_Reviews;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author postal
 */
public class Riempi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        

        ArrayList <Review> ALRev = new ArrayList();
        
        ALRev.add(new Review(null, 5, 4, 5, 4, 5, "bello", "ho mangiato bene", null, 0, new User(3)));
        ALRev.add(new Review(null, 4, 5, 3, 3, 4, "discerto", "ho mangiato bene ma la cameriera non è stata gentile", null, 0, new User(4)));
        ALRev.add(new Review(null, 1, 2, 1, 1, 1, "pessimo", "ho mangiato male", null, 0, new User(5)));
        ALRev.add(new Review(null, 2, 1, 2, 2, 1, "non ci siamo", "uno schifo", null, 0, new User(6)));
        ALRev.add(new Review(null, 3, 4, 4, 4, 4, "si può fare di meglio", "cibo discreto, personale gentile", null, 0, new User(7)));
        ALRev.add(new Review(null, 3, 4, 3, 5, 4, "si e no", "la pizza era buona ma la birra era pessima", null, 0, new User(8)));
        ALRev.add(new Review(null, 2, 4, 2, 2, 2, "personale scortese", "ho chiesto uno stuzzicadenti e non me lo hanno portato", null, 0, new User(9)));
        ALRev.add(new Review(null, 3, 4, 2, 3, 4, "attesa lunga", "ho aspettato 30min prima di ordinare", null, 0, new User(10)));
        ALRev.add(new Review(null, 4, 4, 4, 4, 4, "bello ma..", "ho mangiato bene ma c'era molta gente", null, 0, new User(11)));
        ALRev.add(new Review(null, 5, 5, 5, 5, 5, "top", "ci vado sempre, consigliatissimo", null, 0, new User(12)));
        ALRev.add(new Review(null, 1, 1, 1, 1, 1, "peccato", "gli orari sono sbagliati", null, 0, new User(13)));
        
        ArrayList <Photo> ALPh = new ArrayList();
        
        ALPh.add(new Photo(null, "ok", null, "prova1", new User(3), 2));
        ALPh.add(new Photo(null, "ok", null, "prova2", new User(4), 2));
        ALPh.add(new Photo(null, "ok", null, "prova3", new User(5), 2));
        ALPh.add(new Photo(null, "ok", null, "prova4", new User(7), 2));
        ALPh.add(new Photo(null, "ok", null, "prova5", new User(8), 2));
        ALPh.add(new Photo(null, "ok", null, "prova6", new User(9), 2));
        ALPh.add(new Photo(null, "ok", null, "prova7", new User(10), 2));
        ALPh.add(new Photo(null, "ok", null, "prova8", new User(11), 2));
        ALPh.add(new Photo(null, "ok", null, "prova9", new User(12), 2));
        ALPh.add(new Photo(null, "ok", null, "prova10", new User(13), 2));
        
        
        ArrayList <Restaurant> ALRes = new ArrayList();
        
        new DB_GestioneRestaurant().cercaTuttiRes(ALRes);
        
        for (Restaurant res : ALRes) {
            for (Review rev : ALRev) {
                new DB_Reviews().inserisciReview(rev, res.getId());
            }
            for (Photo ph : ALPh) {
                if(ph.getOwner().getId() % 5 == 0)
                    new DB_RestaurantPhoto().inserisciPhoto(ph, res.getId());
            }
        }
        
        
    }
    
}
