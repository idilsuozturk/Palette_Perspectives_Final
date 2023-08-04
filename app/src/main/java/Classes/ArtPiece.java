package Classes;



public abstract class ArtPiece {
    private int id;
    private int price;
    private String title;
    private Seller artist;
    private double rank;



    //GETTERS AND SETTERS
    public int getArtPieceId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Seller getArtist() {
        return artist;
    }

    public double getRank() {
        return rank;
    }

    public void setArtPieceId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(Seller artist) {
        this.artist = artist;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

}
