package movies.com.co.myapplication.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import movies.com.co.myapplication.R;
import movies.com.co.myapplication.model.Movie;
import movies.com.co.myapplication.model.Movies;
import movies.com.co.myapplication.presenter.MoviesPresenter;
import movies.com.co.myapplication.views.activities.IMoviesView;
import movies.com.co.myapplication.views.adapters.MoviesAdapter;

public class FragmentMovies extends BaseFragment<MoviesPresenter> implements IMoviesView{

    RecyclerView listMovies;
    RecyclerView.LayoutManager layoutManager;
    MoviesAdapter moviesAdapter;

    Movies movieListService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_movie,container,false);
        setPresenter(new MoviesPresenter());
        getPresenter().inject(this, getValidateInternet());
        loadView(view);
        return view;

    }

    private void loadView(View view) {

        listMovies = view.findViewById(R.id.listMovies);
        listMovies.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getBaseActivityFragment());
        listMovies.setLayoutManager(layoutManager);
        loadContent(false);
    }

    private void loadContent(boolean isRefreshed) {
        if(!isRefreshed) {
            getPresenter().LoadMovies();
        }
    }

    public Movies getMovies() {
        return movieListService;
    }

    public void setMovies(Movies movies) {
        this.movieListService = movies;
    }

    @Override
    public void setMoviesCatalog(final Movies movies) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setMovies(movies);
                moviesAdapter = new MoviesAdapter(getBaseActivityFragment().getBaseContext(),getMovies());
                moviesAdapter.notifyDataSetChanged();
                listMovies.setAdapter(moviesAdapter);
                hideProgress();
            }
        });
    }
}
