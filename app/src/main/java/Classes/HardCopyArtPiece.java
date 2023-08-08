/*
    The Hardcopy Art Piece class also uses the super class's attributes. This class will
be the super class of the Auction Art Piece class. Moreover, we implement a new method to
start an auction that creates a regular auction process like in real life.
 */

/*
package Classes;

import java.util.HashMap;

public class HardCopyArtPiece extends ArtPiece implements Offerable {
    private String photoUrl;
    private int price;
    private boolean canOffered;
    private HashMap<User, Integer> offers;

    public HardCopyArtPiece(String title, Seller artist, int price, boolean canOffered) {
        super(title, artist);
        this.price = price;
        this.canOffered = canOffered;
        this.offers = new HashMap<User, Integer>();

    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
    }

} */
