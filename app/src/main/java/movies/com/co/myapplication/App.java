package movies.com.co.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import movies.com.co.myapplication.views.activities.DashBoardAppActivity;

public class App extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(App.this,DashBoardAppActivity.class);
                App.this.startActivity(mainIntent);
                App.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
