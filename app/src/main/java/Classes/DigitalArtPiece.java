/*
    We will use the super class's attributes and methods in the Digital Art Piece class. In
addition, we add an instance blurred that says whether the owner blurs the art piece. Moreover,
we implement a new method that makes the art piece blurred or removes the blur of the art
piece.
 */
/*
package Classes;

import java.util.HashMap;

public class DigitalArtPiece extends ArtPiece implements Offerable {
    private boolean blurred;
    private boolean canOffered;
    private HashMap<User, Integer> offers;

    public DigitalArtPiece(String title, Seller artist, boolean canOffered) {
        super(title, artist);
        this.canOffered = canOffered;
        // By default, the art piece is not blurred
        this.blurred = false;
        this.offers = new HashMap<User, Integer>();
    }

    // Getter and Setter for the 'blurred' attribute
    public boolean isBlurred() {
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
    }

} */

