/*
    The Auction Art Piece class will use most attributes of the superclass, but no need
for the price attribute. Therefore, we will add starting price rather than price attribute and also
add starting date. In addition to that, we will add methods for getting the highest offer, and
controlling whether the auction is over.
 */

/* package Classes;

import java.util.Date;

public class AuctionArtPiece extends HardCopyArtPiece {
    // necessary instances
    private double startingPrice;
    private Date startingDate;
    private Date endDate;
    private double highestOffer;
    private boolean auctionOver;

    public AuctionArtPiece(String title, Seller artist, double startingPrice, boolean canOffered, Date startingDate) {
        super(title, artist, -1, canOffered); // This is need to be fixed, because price will be -1. I mean it must be implemented
        this.startingPrice = startingPrice;
        this.startingDate = startingDate;
        this.highestOffer = startingPrice;
        this.auctionOver = false;

        long oneDayInMillis = 24 * 60 * 60 * 1000; // One day in milliseconds
        long endDateInMillis = startingDate.getTime() + oneDayInMillis;
        this.endDate = new Date(endDateInMillis);
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
        return auctionOver || new Date().after(endDate);
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

