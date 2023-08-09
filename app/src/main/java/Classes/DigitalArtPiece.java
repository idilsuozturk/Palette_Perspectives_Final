/*
    We will use the super class's attributes and methods in the Digital Art Piece class. In
addition, we add an instance blurred that says whether the owner blurs the art piece. Moreover,
we implement a new method that makes the art piece blurred or removes the blur of the art
piece.
 */

package Classes;

import java.util.HashMap;

public class DigitalArtPiece  {
    private String userId;
    private String imageUrl;
    private String price;
    private boolean canBid;

    public DigitalArtPiece(String userId, String imageUrl, String price, boolean canBid) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.price = price;
        this.canBid = canBid;
    }
    public DigitalArtPiece() {
        // Default constructor is needed for Firestore deserialization
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isCanBid() {
        return canBid;
    }

    public void setCanBid(boolean canBid) {
        this.canBid = canBid;
    }

    // Getter and Setter for the 'blurred' attribute
    /*public boolean isBlurred() {
        return blurred;
    }

    public void setBlurred(boolean blurred) {
        this.blurred = blurred;
    }

    public void toggleBlur() {
        blurred = !blurred;
    }

    @Override
    public void addOffer(User user, int offer) {
        if (this.canOffered) {
            offers.put(user, offer);
        }
    }

    @Override
    public void removeOffer(User user) {
        if (this.canOffered) {
            offers.remove(user);
        }
    }*/

}

