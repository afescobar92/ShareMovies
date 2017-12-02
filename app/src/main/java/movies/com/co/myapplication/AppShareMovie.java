package movies.com.co.myapplication;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;


public class AppShareMovie extends Application {

    private Tracker tracker;

    synchronized public Tracker getDefaultTracker(){
       if(tracker == null){
           GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
           //tracker = analytics.newTracker(R.xml.global_tracker);
       }

       return tracker;
    }

}
