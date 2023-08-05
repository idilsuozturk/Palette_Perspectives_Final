/*package Classes;

import static com.google.firebase.database.core.operation.OperationSource.Source.User;

import java.util.ArrayList;

public class Buyer extends User{
    private ArrayList<Seller> bestSellers;
    private ArrayList<ArtPiece> favouritePieces;
    private  ArrayList<ArtPiece> boughtArtPieces;

    public Buyer(String name, int id, String password, int age){
        super(name, id, password, age);
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
        favouritePieces.add(artPiece);
    }


    public static class Admin extends User {
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
}*/
