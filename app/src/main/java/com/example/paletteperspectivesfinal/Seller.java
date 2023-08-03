package com.example.paletteperspectivesfinal;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Seller extends User{
    private int followers;
    private String bio;
    private String profilePhotoUrl;

    private ArtPiece[] portfolio;
    private Gallery gallery;


    public void UploadProfilePhoto(Uri imageURL){
        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference().child("profile_photos");
        //We will replace profile photos with the right path to add to db
        StorageReference imageReference = storageReference.child("user.jpg");
        //Each photo will have a unique name
        UploadTask uploadTask = imageReference.putFile(imageURL);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Image uploaded successfully, you can get the download URL and store it in the seller object.
            imageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                this.profilePhotoUrl = uri.toString();
            });
        }).addOnFailureListener(e -> {
        });

    }
    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    @Override
    public void createAccount() {

    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void changePassword() {

    }
}
