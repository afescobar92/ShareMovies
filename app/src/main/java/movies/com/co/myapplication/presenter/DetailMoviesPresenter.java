package movies.com.co.myapplication.presenter;

import android.util.Log;

import movies.com.co.myapplication.helper.ServicesFactory;
import movies.com.co.myapplication.repository.MoviesRepository;
import movies.com.co.myapplication.views.activities.IMoviesDetailView;
import retrofit.RetrofitError;

public class DetailMoviesPresenter extends BasePresenter<IMoviesDetailView> {

    private static final String TAG = "DetailMoviesPresenter";

    private MoviesRepository moviesRepository;

    public DetailMoviesPresenter() {
        this.moviesRepository = new MoviesRepository();
    }

    public DetailMoviesPresenter(ServicesFactory.TypeConverter type) {
        this.moviesRepository = new MoviesRepository(type);
    }

    public void loadCinemas(){
        if (getValidateInternet().isConnected()) {
            getAsyncCallCinemas();
        } else {
            //TODO: message not internet
        }
    }

    private void getAsyncCallCinemas() {
        getView().showProgress(0);
        Thread work = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getView().setCinemas(moviesRepository.getCinemas());
                } catch (RetrofitError retrofitError) {
                    //TODO: catch error retorfit services
                    retrofitError.printStackTrace();
                    Log.e(TAG,retrofitError.getMessage(),retrofitError);
                }finally {
                    getView().hideProgress();
                }
            }
        });
        work.start();



    }


}
