package restaurants;

import restaurant_photos.Photo;
import restaurant_comments.Review;
import restaurants.Restaurant;
import users.User;
import database.DB_GestioneRestaurant;
import database.DB_RestaurantPhoto;
import database.DB_Reviews;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
        ALRev.add(new Review(null, 4, 4, 4, 4, 4, "bello ma..", "ho mangiato bene ma c'era molta gente", null, 0, new User(4)));
        ALRev.add(new Review(null, 5, 5, 5, 5, 5, "top", "ci vado sempre, consigliatissimo", null, 0, new User(3)));
        ALRev.add(new Review(null, 1, 1, 1, 1, 1, "peccato", "gli orari sono sbagliati", null, 0, new User(2)));
        
        ArrayList <Photo> ALPh = new ArrayList();


        for(int i =1; i<37; i++){
            String ext = ".jpg";
            if(i==14 || i==13){
                ext = ".png";
            }
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
            
            while(randomNumber2==randomNumber1){
                randomNumber2 = random2.nextInt(11);
            }
            
            while(randomNumber3==randomNumber4){
                randomNumber4 = random2.nextInt(11);
            }
            
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
            if (newGV <= 5) {
                new DB_GestioneRestaurant().updateRate(res, newGV);
            }else{
                new DB_GestioneRestaurant().updateRate(res, 5);
            }
                
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
