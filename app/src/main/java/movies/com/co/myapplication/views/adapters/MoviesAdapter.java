package movies.com.co.myapplication.views.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import movies.com.co.myapplication.R;
import movies.com.co.myapplication.helper.Constants;
import movies.com.co.myapplication.model.Movies;
import movies.com.co.myapplication.views.activities.DetailActivityMovie;

class ListMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ItemClickListener itemClickListener ;

    CircleImageView movieImage;
    TextView movieName;
    RatingBar ratingMovie;

    public ListMoviesViewHolder(View itemView) {
        super(itemView);

        movieImage =  itemView.findViewById(R.id.movieImage);
        movieName  =  itemView.findViewById(R.id.movieName);
        ratingMovie = itemView.findViewById(R.id.ratingMovie);
        itemView.setOnClickListener(this);
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

public class MoviesAdapter extends RecyclerView.Adapter<ListMoviesViewHolder>{

    private Context context;
    private Movies movies;

    public MoviesAdapter(Context context, Movies movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ListMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_movie,parent,false);
        return new ListMoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final ListMoviesViewHolder holder, final int position) {

        Picasso.with(context)
                .load(movies.getMovies().get(position).getPosterMovie().getPosterSmall())
                .into(holder.movieImage);

        if(movies.getMovies().get(position).getMovieInfo().getTitle().length() >65){
            holder.movieName.setText(movies.getMovies().get(position).getMovieInfo().getTitle().substring(0,65)+"...");
        }else{
            holder.movieName.setText(movies.getMovies().get(position).getMovieInfo().getTitle());
        }

        holder.ratingMovie.setRating(new Random().nextInt(9));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Intent intent = new Intent(context, DetailActivityMovie.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constants.ITEM_MOVIE,movies.getMovies().get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.getMovies().size();
    }
}
