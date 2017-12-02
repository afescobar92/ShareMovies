package movies.com.co.myapplication.views.activities;


import java.util.List;

import movies.com.co.myapplication.model.Cinemas;
import movies.com.co.myapplication.views.IBaseView;

public interface IMoviesDetailView extends IBaseView {

    void setCinemas(List<Cinemas> cinemas);
}
