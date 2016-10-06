package ucd.app.views.activities;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ucd.app.R;
import ucd.app.views.adapters.ViewPagerAdapter;
import ucd.app.views.fragments.InfoFragment;
import ucd.app.views.fragments.PhotoFragment;
import ucd.app.views.fragments.PlaceFragment;
import ucd.app.views.fragments.RankingFragment;
import ucd.app.views.fragments.TaskFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Booting the elements for the TabLayout in the Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        // Defines the number of tabs by setting appropriate fragment and tab name.
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InfoFragment());
        adapter.addFragment(new PhotoFragment());
        adapter.addFragment(new PlaceFragment());
        adapter.addFragment(new TaskFragment());
        adapter.addFragment(new RankingFragment());
        viewPager.setAdapter(adapter);

        // Assigns the ViewPager to TabLayout, Tabs with only Icons.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_info_dark);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_photo_dark);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_place_dark);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_task_dark);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_tab_ranking_dark);

        // Pushing MapView Fragment
//        Fragment fragment = Fragment.instantiate(this, PlaceFragment.class.getName());
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.container, fragment);
//        fragmentTransaction.commit();
    }
}
