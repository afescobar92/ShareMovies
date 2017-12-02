package movies.com.co.myapplication.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "records" , strict = false)
public class Movies implements Serializable{

    @ElementList(entry="movieinfo", required = false, inline = true)
    List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
