package restaurants;

import database.DB_GestioneRestaurant;
import database.DB_RestaurantPhoto;
import database.DB_Reviews;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import restaurant_comments.Review;
import restaurant_photos.Photo;
import users.User;

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
        ALRev.add(new Review(null, 5, 5, 5, 5, 5, "Bellisimo!", "Ho mangiato molto bene, il servizio è ottimo e la location è bellissima. Ci torneremo di sicuro!", null, 0, new User(3)));
        ALRev.add(new Review(null, 4, 5, 3, 3, 4, "Discreto", "Ho mangiato bene ma i camerieri non sono molto gentili e il servizio è stato un po' lento.", null, 0, new User(4)));
        ALRev.add(new Review(null, 1, 1, 1, 1, 1, "Pessimo!", "Ho mangiato malissimo, servizio molto lento e atmosfera un po' troppo cupa e triste.", null, 0, new User(5)));
        ALRev.add(new Review(null, 2, 1, 2, 2, 1, "Non ci siamo proprio", "Ristorante terribile! Un vero schifo! Appena pagato il conto siamo scappati di corsa", null, 0, new User(6)));
        ALRev.add(new Review(null, 3, 4, 4, 4, 4, "Si può fare di meglio...", "Cibo discreto, personale gentile, atmosfera carina. Unica pecca: il volume della musica un po' troppo alto.", null, 0, new User(7)));
        ALRev.add(new Review(null, 3, 4, 3, 5, 4, "Sì e no", "Buona la pizza ma gli antipasti che abbiamo ordinato erano freddi ed insipidi. Birra decente.", null, 0, new User(8)));
        ALRev.add(new Review(null, 2, 4, 2, 2, 2, "Personale scortese", "Ad un certo punto della cena ho chiesto un altro bicchiere alla cameriera perchè il mio era sporco, ma non è mai arrivato! Si perdono i clienti così!", null, 0, new User(9)));
        ALRev.add(new Review(null, 3, 4, 2, 3, 4, "Attesa lunga", "Ho dovuto aspettare 30 minuti prima di ordinare e di nuovo 30 minuti prima di mangiare. Peccato perchè il ristorante avrebbe del potenziale.", null, 0, new User(10)));
        ALRev.add(new Review(null, 4, 4, 4, 4, 4, "Bello ma...", "Ho mangiato bene ma c'era davvero troppa gente! I tavoli sono troppo vicini e fare una normale conversazione è impossibile!", null, 0, new User(4)));
        ALRev.add(new Review(null, 5, 5, 5, 5, 5, "Il miglior ristorante", "Ci vado sempre, consigliatissimo! Atmosfera, servizio, cibo sempre impeccabili! In aggiunta il conto non è mai alto, meglio di così!", null, 0, new User(3)));
        ALRev.add(new Review(null, 1, 2, 1, 1, 1, "Peccato", "Gli orari sono sbagliati e il link non è più online. Correggete!", null, 0, new User(2)));
        
        ArrayList <Photo> ALPh = new ArrayList();
        for (int i =1; i<37; i++) {
            String ext = ".jpg";
            if (i==14 || i==13)
                ext = ".png";
            
            ALPh.add(new Photo(null, "ok", null, i+ext, new User((i%9)+2), 2));
        }
        
        ArrayList <Restaurant> ALRes = new ArrayList();
        new DB_GestioneRestaurant().cercaTuttiRes(ALRes);
        
        for (Restaurant res : ALRes) {           
            new DB_GestioneRestaurant().cercaRistorante_perId(res);
            
            Random random1 = new Random();
            int randomNumber1 = random1.nextInt(11);
            Random random2 = new Random();
            int randomNumber2 = random2.nextInt(11);
            Random random3 = new Random();
            int randomNumber3 = random3.nextInt(36);
            Random random4 = new Random();
            int randomNumber4 = random4.nextInt(36);
            
            while(randomNumber2==randomNumber1)
                randomNumber2 = random2.nextInt(11);
            
            while(randomNumber3==randomNumber4)
                randomNumber4 = random2.nextInt(11);
            
            Review rev = ALRev.get(randomNumber1);
            new DB_Reviews().inserisciReview(rev, res.getId());
            new DB_Reviews().increaseReviewRestaurant(new Restaurant(res.getId()));
            Double reviews_value [] = new Double [1];
            new DB_Reviews().countReviews(res, reviews_value);
            double newGV = ((((rev.getGlobal_value() 
                    + rev.getFood() + 
                    rev.getService() + 
                    rev.getValue_of_money() + 
                    rev.getAtmosphere())/5) + 
                    res.getGlobal_value().intValue()  + 
                    reviews_value[0])/3.0);
            if (newGV <= 5)
                new DB_GestioneRestaurant().updateRate(res, newGV);
            else
                new DB_GestioneRestaurant().updateRate(res, 5);
            
                
            rev = ALRev.get(randomNumber2);
            new DB_Reviews().inserisciReview(rev, res.getId());
            new DB_Reviews().increaseReviewRestaurant(new Restaurant(res.getId()));
            reviews_value = new Double [1];
            new DB_Reviews().countReviews(res, reviews_value);
            newGV = ((((rev.getGlobal_value() 
                    + rev.getFood() + 
                    rev.getService() + 
                    rev.getValue_of_money() + 
                    rev.getAtmosphere())/5) + 
                    res.getGlobal_value().intValue() + 
                    reviews_value[0])/3.0);
            if (newGV <= 5) {
                new DB_GestioneRestaurant().updateRate(res, newGV);
            }else{
                new DB_GestioneRestaurant().updateRate(res, 5);
            }
            
            
            new DB_RestaurantPhoto().inserisciPhoto(ALPh.get(randomNumber3), res.getId());
            
            new DB_RestaurantPhoto().inserisciPhoto(ALPh.get(randomNumber4), res.getId());
            
        }
        
        
    }
    
}
