package MovieProject.BE;

import java.time.LocalDate;

public class Movie {

    private int id;
    private String name;
    private double rating,imdb;
    private String fileLink;
    private LocalDate lastview;

    boolean toOld;
    public Movie(int id, String name, double rating,double imdb,String fileLink, LocalDate lastview, boolean toOld) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.imdb=imdb;
        this.fileLink = fileLink;
        this.lastview = lastview;
        this.toOld=toOld;

    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }


    public boolean getToOld() {
        return toOld;
    }

    public void setToOld(boolean toOld) {
        this.toOld = toOld;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public LocalDate getLastview() {
        return lastview;
    }

    public void setLastview(LocalDate lastview) {
        this.lastview = lastview;
    }

    @Override
    public String toString() {
        return "Movie{" +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", imdb=" + imdb +
                ", toOld='" + toOld + '\'' +
                '}';
    }
}