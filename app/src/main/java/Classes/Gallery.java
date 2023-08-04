package Classes;

import java.util.ArrayList;

//Created this class in order to use Seller it is not final verison
//just draft
public class Gallery {
    private ArrayList<ArtPiece> artPieces;
    private String title;
    private Seller artist;
    private int price;


    public Gallery(int price, String title, Seller artist) {
        artPieces = new ArrayList<ArtPiece>();
        this.title = title;
        this.artist = artist;
        this.price = price;
    }

    // Method to add an art piece to the gallery
    public void addArtPiece(ArtPiece artPiece) {
        artPieces.add(artPiece);
    }

    // Method to remove an art piece from the gallery
    public void removeArtPiece(ArtPiece artPiece) {
        artPieces.remove(artPiece);
    }

    public static class BestSeller extends User {
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
                if(seller instanceof BestSeller){
                    BestSeller bestSeller = (BestSeller) seller;
                    if(bestSeller.getRank() > rank && bestSeller.getSoldArtPieces().size > 100){ // create a variable that holds
                        //number of sold art pieces and retrive it
                        topSellingArtists.add(bestSeller);
                    }

                }
            }

        }
    }
}
