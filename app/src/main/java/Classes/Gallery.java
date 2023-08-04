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
}
