package movies.com.co.myapplication.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import movies.com.co.myapplication.R;

public class FragmentMovies extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_movie,container,false);
        loadView(view);
        loadContent();
        return view;

    }

    private void loadView(View view) {
    }

    private void loadContent() {
        
    }
}
