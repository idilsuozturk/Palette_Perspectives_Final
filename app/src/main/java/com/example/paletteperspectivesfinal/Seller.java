package com.example.paletteperspectivesfinal;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Seller extends User{
    private int followers;
    private String bio;
    private String profilePhotoUrl;

    private ArtPiece[] portfolio;
    private Gallery gallery;
    public Seller(String name, int id, String password, int age) {
        super(name, id, password, age);
    }
    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    // Getter and setter for bio
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Getter and setter for portfolio
    public ArtPiece[] getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArtPiece[] portfolio) {
        this.portfolio = portfolio;
    }

    // Getter and setter for gallery
    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
    public void UploadProfilePhoto(Uri imageURL){
        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference().child("profile_photos");
        //We will replace profile photos with the right path to add to db
        String fileName = "user_" + System.currentTimeMillis() + ".jpg";
        StorageReference imageReference = storageReference.child(fileName);

        //Each photo will have a unique name
        UploadTask uploadTask = imageReference.putFile(imageURL);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Image uploaded successfully, you can get the download URL and store it in the seller object.
            imageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                this.profilePhotoUrl = uri.toString();
            });
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(@NonNull Exception e) {
                // Handle unsuccessful uploads here
            }
        });
    }
    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    // Method to add a DigitalArtPiece to the gallery
    public void addDigitalArtPiece(DigitalArtPiece artPiece) {
        // Check if the gallery exists
        if (gallery == null) {
            // If the gallery is not created yet, create a new gallery
            gallery = new Gallery();
        }

        // Add the digital art piece to the gallery
        gallery.addArtPiece(artPiece);
    }
    //Method to add a Hard Copy Art piece to the gallery
    public void addHardCopyArtPiece(HardcopyArtPiece hardcopyArtPiece) {
        // Check if the gallery exists
        if (gallery == null) {
            // If the gallery is not created yet, create a new gallery
            gallery = new Gallery();
        }

        // Add the hard copy art piece to the gallery
        gallery.addArtPiece(hardcopyArtPiece);
    }
    public void removeArtPiece(ArtPiece artPiece){
        if(gallery != null){
            gallery.removeArtPiece(artPiece);
        }
    }
    public void addAuctionArtPiece(AuctionArtPiece auctionArtPiece){
        if(gallery != null){
            gallery.addArtPiece(auctionArtPiece);
        }
    }
    public void addGallery(){
        if(gallery == null){
            gallery = new Gallery();
        }
    }

}
