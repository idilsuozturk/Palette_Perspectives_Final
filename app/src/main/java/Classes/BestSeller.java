/*package Classes;

import android.media.MediaCodec;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

import Classes.Seller;

import Classes.ArtPiece;
import Classes.User;


public class BestSeller extends User {
    private int rank;

    public BestSeller(String name, int id, String password, int age, int rank){
        //There is a mistake here I could not solve it
        super(name, id, password, age);
        this.rank = rank;
    }

    //Getters and Setters for the rank
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public ArrayList <Seller> getTopSellingArtist(ArrayList <Seller> allSellers){
        allSellers = new ArrayList<>();
        ArrayList<Seller> topSellingArtists = new ArrayList<>();
        for(Seller seller: allSellers){
            if(seller instanceof BestSeller ){
                BestSeller bestSeller = (BestSeller) seller;
                if(bestSeller.getRank() > rank && bestSeller.getSoldArtPieces().size > 100){ // create a variable that holds
                    //number of sold art pieces and retrive it
                    topSellingArtists.add(bestSeller);
                }

            }
        }

    }
}
*/