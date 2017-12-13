package movies.com.co.myapplication.views.activities;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import movies.com.co.myapplication.R;
import movies.com.co.myapplication.views.BaseActivity;
import movies.com.co.myapplication.views.adapters.DashBoardAppAdapter;

public class DashBoardAppActivity extends BaseActivity{

    TabLayout dashTabLayout;
    ViewPager dashViewPager;
    CircleImageView profileImage;
    String imageProfile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board_app_activity);
        initComponents();
        initEvents();
        initAdapters();
    }

    private void initComponents() {
        this.dashTabLayout = findViewById(R.id.dash_tab_layout);
        this.dashViewPager = findViewById(R.id.dash_view_pager);
        this.profileImage  = findViewById(R.id.profileImage);
        Picasso.with(this)
                .load(R.drawable.ic_audiotrack_dark)
                .into(this.profileImage);

    }

    private void initEvents() {
        DashBoardAppAdapter dashBoardAdapter = new DashBoardAppAdapter(getSupportFragmentManager());
        dashViewPager.setAdapter(dashBoardAdapter);
        dashTabLayout.setupWithViewPager(dashViewPager);
        dashTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorGray));
        dashTabLayout.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.colorWhite)));

    }

    private void initAdapters() {

    }
}
