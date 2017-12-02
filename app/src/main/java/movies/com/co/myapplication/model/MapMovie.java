package movies.com.co.myapplication.model;


import java.io.Serializable;
import java.util.List;

public class MapMovie implements Serializable{
    Movie movie;
    List<Cinemas> cinemas;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Cinemas> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinemas> cinemas) {
        this.cinemas = cinemas;
    }
}
