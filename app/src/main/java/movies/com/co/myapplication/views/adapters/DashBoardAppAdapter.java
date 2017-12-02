package movies.com.co.myapplication.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import movies.com.co.myapplication.helper.Constants;
import movies.com.co.myapplication.views.fragments.FragmentMovies;
import movies.com.co.myapplication.views.fragments.FragmentProfile;

public class DashBoardAppAdapter extends FragmentStatePagerAdapter {

    public DashBoardAppAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new FragmentMovies();
            case 1:
                return new FragmentProfile();
            case 2:
                return new FragmentMovies();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return Constants.TITLE_MOVIES;
            case 1:
                return Constants.TITLE_PROFILE;
            default:
                return Constants.TITLE_MOVIES;
        }
    }
}
