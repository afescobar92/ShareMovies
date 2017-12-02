package movies.com.co.myapplication.presenter;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import movies.com.co.myapplication.helper.ServicesFactory;
import movies.com.co.myapplication.model.Movie;
import movies.com.co.myapplication.model.Movies;
import movies.com.co.myapplication.repository.MoviesRepository;
import movies.com.co.myapplication.views.activities.IMoviesView;
import retrofit.RetrofitError;

public class MoviesPresenter extends BasePresenter<IMoviesView>{

    private static final String TAG = "MoviesPresenter";

    private MoviesRepository moviesRepository;

    public MoviesPresenter() {
        this.moviesRepository = new MoviesRepository();
    }

    public MoviesPresenter(ServicesFactory.TypeConverter type) {
        this.moviesRepository = new MoviesRepository();
    }

    public void LoadMovies(){
        if (getValidateInternet().isConnected()) {
            getAsyncCallMovies();
        } else {
           //TODO: message not internet
        }
    }

    private void getAsyncCallMovies() {
        getView().showProgress(0);
        Thread work = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Movies movies = moviesRepository.getCatalogMovies();
                    getView().setMoviesCatalog(movies);

                } catch (RetrofitError retrofitError) {
                    //TODO: catch error retorfit services
                    retrofitError.printStackTrace();
                    Log.e(TAG,retrofitError.getMessage(),retrofitError);
                }
            }
        });
        work.start();



    }

}
