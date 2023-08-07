/* package Classes;

import java.util.Date;

public class AuctionArtPiece extends HardCopyArtPiece {
    private double startingPrice;
    private Date startingDate;
    private double highestOffer;
    private boolean auctionOver;

    public AuctionArtPiece(String title, Seller artist, double startingPrice, boolean canOffered, Date startingDate) {
        super(title, artist, -1, canOffered); // This is need to be fixed, because price will be -1. I mean it must be implemented
        this.startingPrice = startingPrice;
        this.startingDate = startingDate;
        this.highestOffer = startingPrice;
        this.auctionOver = false;
    }

    // Getters and Setters for the new attributes

    public double getStartingPrice() {
        return startingPrice;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public double getHighestOffer() {
        return highestOffer;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    // Other methods for controlling the auction

    public boolean isAuctionOver() {
        return auctionOver;
    }

    public void makeOffer(double offer) {
        if (offer > highestOffer) {
            highestOffer = offer;
        }
    }

    public void endAuction() {
        auctionOver = true;
    }
} */

