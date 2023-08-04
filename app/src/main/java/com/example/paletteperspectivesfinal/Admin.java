package com.example.paletteperspectivesfinal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

import Classes.ArtPiece;
import Classes.User;

public class Admin extends User {
    private ArrayList<User> bannedUsers;
    private ArtPiece artPiece;

    public Admin(String name, int id, String password, int age) {
        super(name, id, password, age);
        bannedUsers = new ArrayList<>();
    }

    // Getter and setter for bannedUsers
    public ArrayList<User> getBannedUsers() {
        return bannedUsers;
    }

    public void setBannedUsers(ArrayList<User> bannedUsers) {
        this.bannedUsers = bannedUsers;
    }

    // Method to delete an art piece from Firestore
    public void deleteArtPiece(ArtPiece artPiece) {
        // Get a reference to the Firestore database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Check if the art piece exists in the system
        if (artPieceExists(artPiece)) {
            // Delete the art piece document from the "artPieces" collection in Firestore
            db.collection("artPieces")
                    .document(artPiece.getDocumentId()) // Use the unique document ID of the art piece
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("Art piece removed successfully!");
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
                                System.out.println("Failed to remove art piece: " + task.getException());
                            }
                        }
                    });
        } else {
            System.out.println("Art piece not found!");
        }
    }

    // Helper method to check if the art piece exists in the system
    private boolean artPieceExists(ArtPiece artPiece) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if(ArtPiece.getDocumentId != null){
            DocumentReference artPieceRef = db.collection("artPieces").document(artPiece.getDocumentId());
            // Retrieve the art piece document from Firestore
            Task<DocumentSnapshot> documentSnapshotTask = artPieceRef.get();
            try {
                // Block on the task and get the document snapshot
                DocumentSnapshot documentSnapshot = Tasks.await(documentSnapshotTask);

                // Check if the document exists and contains data
                return documentSnapshot.exists();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
        }

}
