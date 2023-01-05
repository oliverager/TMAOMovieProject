package MovieProject.BE;

import java.time.LocalDate;

public class Movie {

    private int id;
    private String name;
    private double rating;
    private String fileLink;
    private LocalDate lastview;

    public Movie(int id, String name, double rating, String fileLink, LocalDate lastview) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.lastview = lastview;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", fileLink='" + fileLink + '\'' +
                ", lastview='" + lastview + '\'' +
                '}';
    }
}