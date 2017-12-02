package movies.com.co.myapplication.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import movies.com.co.myapplication.R;
import movies.com.co.myapplication.helper.Constants;
import movies.com.co.myapplication.helper.ServicesFactory;
import movies.com.co.myapplication.model.Cinemas;
import movies.com.co.myapplication.model.MapMovie;
import movies.com.co.myapplication.model.Movie;
import movies.com.co.myapplication.presenter.DetailMoviesPresenter;
import movies.com.co.myapplication.views.BaseActivity;

public class DetailActivityMovie extends BaseActivity<DetailMoviesPresenter> implements IMoviesDetailView{

    private static final String TAG = "DetailActivityMovie";
    DiagonalLayout diagonalLayout;
    KenBurnsView   largeImageMovie;
    TextView       titleMovie,nameAuthor,nameGenre,releaseDate,txtSynopsis,txtRuntime,nameCast,nameStudio;
    Movie movie;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);
        setPresenter(new DetailMoviesPresenter(ServicesFactory.TypeConverter.JSON));
        getPresenter().inject(this, getValidateInternet());
        loadView();
    }

    private void loadView() {
        diagonalLayout = findViewById(R.id.diagonalLayout);
        largeImageMovie = findViewById(R.id.largeImageMovie);
        titleMovie = findViewById(R.id.titleMovie);
        txtSynopsis = findViewById(R.id.txtSynopsis);
        txtSynopsis.setMovementMethod(new ScrollingMovementMethod());
        nameAuthor = findViewById(R.id.nameAuthor);
        nameGenre = findViewById(R.id.nameGenre);
        releaseDate = findViewById(R.id.releaseDate);
        txtRuntime = findViewById(R.id.txtRuntime);
        nameCast = findViewById(R.id.nameCast);
        nameStudio = findViewById(R.id.nameStudio);

        if(getIntent() != null){
            movie = (Movie)getIntent().getSerializableExtra(Constants.ITEM_MOVIE);
            Picasso.with(getBaseContext())
                    .load(movie.getPosterMovie().getPosterLarge())
                    .into(largeImageMovie);
            titleMovie.setText(movie.getMovieInfo().getTitle());
            txtSynopsis.setText(movie.getMovieInfo().getDescription());
            nameAuthor.setText(movie.getMovieInfo().getDirector());
            txtRuntime.setText(movie.getMovieInfo().getRuntime());
            nameStudio.setText(movie.getMovieInfo().getStudio());
            if(movie.getGenreMovie().getGenreList()!= null) {
                int k = 1;
                StringBuilder builderCast = new StringBuilder();
                for(String genre: movie.getGenreMovie().getGenreList()){
                    builderCast.append(genre);
                    if(k < movie.getGenreMovie().getGenreList().size()){
                        builderCast.append(", ");
                    }
                    ++k;
                }
                nameGenre.setText(builderCast.toString());
            }else{
                nameGenre.setText("...");
            }
            if(movie.getCast().getCastList()!= null) {
                int k = 1;
                StringBuilder builderCast = new StringBuilder();
                for(String cast: movie.getCast().getCastList()){
                    builderCast.append(cast);
                    if(k < movie.getCast().getCastList().size()){
                        builderCast.append(", ");
                    }
                    ++k;
                }
                nameCast.setText(builderCast.toString());
            }else{
                nameCast.setText("[...]");
            }
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE);
            releaseDate.setText(formatter.format(movie.getMovieInfo().getReleasedate()));
        }

    }

    public void back(View view) {
        finish();
    }

    public void onLocationMovie(View view) {
        getPresenter().loadCinemas();
    }

    @Override
    public void setCinemas(List<Cinemas> cinemas) {

        if(cinemas != null & !cinemas.isEmpty()) {
            MapMovie mapMovie = new MapMovie();
            mapMovie.setMovie(this.movie);
            mapMovie.setCinemas(cinemas);
            Intent intent = new Intent(this, MovieLocationActivity.class);
            intent.putExtra(Constants.LIST_CINEMA, mapMovie);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Not found location cinema movie", Toast.LENGTH_LONG).show();
        }
    }
}
