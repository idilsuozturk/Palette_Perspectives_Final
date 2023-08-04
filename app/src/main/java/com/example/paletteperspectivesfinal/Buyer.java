package com.example.paletteperspectivesfinal;

import java.util.ArrayList;

public class Buyer extends User{
    private ArrayList<Seller> bestSellers;
    private ArrayList<ArtPiece> favouritePieces;
    private  ArrayList<ArtPiece> boughtArtPieces;

    public Buyer(String name, int id, String password, int age){
        this.setName(name);
        this.setId(id);
        this.setPassword(password);
        this.setAge(age);
    }

    //Getters
    public ArrayList<ArtPiece> getFavoritePieces() {
        return favoritePieces;
    }

    public ArrayList<ArtPiece> getBoughtPieces() {
        return boughtPieces;
    }

    public ArrayList<Seller> getBestSellers() {
        return bestSellers;
    }

    public void buyArtPiece(ArtPiece artPiece) {
        // Assume there's a payment process here or some sort of verification
        // After payment is successful, add the art piece to the boughtPieces collection
        boughtArtPieces.add(artPiece);
    }
    public void addFavoritePiece(ArtPiece artPiece){
        favouritePieces.
    }






}