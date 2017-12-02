package movies.com.co.myapplication.repository;

import java.util.List;

import movies.com.co.myapplication.helper.ServicesFactory;
import movies.com.co.myapplication.model.Cinemas;
import movies.com.co.myapplication.model.Movie;
import movies.com.co.myapplication.model.Movies;
import movies.com.co.myapplication.services.IServices;

public class MoviesRepository {

    private IServices services;

    /**
     * Constructor default converter type XML
     */
    public MoviesRepository() {
      this(ServicesFactory.TypeConverter.XML);
    }

    public MoviesRepository(ServicesFactory.TypeConverter type) {
        ServicesFactory servicesFactory = new ServicesFactory(type);
        services = (IServices) servicesFactory.getInstance(IServices.class);
    }

    public Movies getCatalogMovies() {
        return services.getCatalogMovies();
    }

    public List<Cinemas> getCinemas(){
        return services.getCinemas();
    }
}
