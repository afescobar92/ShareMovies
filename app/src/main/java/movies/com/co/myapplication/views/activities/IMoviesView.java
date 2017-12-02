package movies.com.co.myapplication.views.activities;


import java.util.List;

import movies.com.co.myapplication.model.Movie;
import movies.com.co.myapplication.model.Movies;
import movies.com.co.myapplication.views.IBaseView;

public interface IMoviesView extends IBaseView{

    void setMoviesCatalog(Movies movies);

}
