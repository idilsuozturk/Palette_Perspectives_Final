package com.example.paletteperspectivesfinal;
//Created this class in order to use Seller it is not final verison
//just draft
public class Gallery {
    private ArrayList<ArtPiece> artPieces;

    public Gallery() {
        artPieces = new ArrayList<>();
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
