package movies.com.co.myapplication.services;


import java.util.List;

import movies.com.co.myapplication.model.Cinemas;
import movies.com.co.myapplication.model.Movie;
import movies.com.co.myapplication.model.Movies;
import retrofit.http.GET;

public interface IServices {

    @GET("/current.xml")
    Movies getCatalogMovies();

    @GET("/cinemas")
    List<Cinemas> getCinemas();

}
