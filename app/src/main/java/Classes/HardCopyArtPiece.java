/*
    The Hardcopy Art Piece class also uses the super class's attributes. This class will
be the super class of the Auction Art Piece class. Moreover, we implement a new method to
start an auction that creates a regular auction process like in real life.
 */


package Classes;

import java.util.HashMap;

public class HardCopyArtPiece  {

    private String userId;
    private String imageUrl;
    private String price;
    private boolean canBid;

    public HardCopyArtPiece(String userId, String imageUrl, String price, boolean canBid) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.price = price;
        this.canBid = canBid;
    }
    public HardCopyArtPiece() {
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

    /*@Override
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
