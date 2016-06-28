package ucd.app.views.activities;

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
    
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_info_dark,
            R.drawable.ic_tab_photo_dark,
            R.drawable.ic_tab_place_dark,
            R.drawable.ic_tab_ranking_dark,
            R.drawable.ic_tab_task_dark
    };

    // Service for access the RETROFIT API.
    //private ApiService apiService;

    // ProgressBar for loading the requests.
    //private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Booting the elements for the TabLayout.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        // Defines the number of tabs by setting appropriate fragment and tab name.
        setupViewPager(viewPager);
 
        // Assigns the ViewPager to TabLayout.
        tabLayout.setupWithViewPager(viewPager);
        
        // Tabs with only Icons.
        setupTabIcons();

        // Booting the service API and the progressBar.
        //this.apiService = ApiClient.getClient().create(ApiService.class);
        //this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Start loading
        //progressBar.setVisibility(View.VISIBLE);

        // Fetching for all users.
        //apiService.fetchUsers().enqueue(new Callback<List<User>>() {

            //@Override
            //public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                // Make a objecto of users and show on the view.
                //TextView text_view_users = (TextView) findViewById(R.id.text_view_users);
                //text_view_users.setText("USERS: \n" + response.body().toString());

                // Finish the loading.
                //progressBar.setVisibility(View.INVISIBLE);
            //}

            //@Override
            //public void onFailure(Call<List<User>> call, Throwable t) {

                // Throw friendly exception.
                //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                // Finish the loading.
                //progressBar.setVisibility(View.INVISIBLE);
            //}

        //});
    }
    
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }
    
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InfoFragment(), "INFO");
        adapter.addFragment(new PhotoFragment(), "PHOTO");
        adapter.addFragment(new PlaceFragment(), "PLACE");
        adapter.addFragment(new RankingFragment(), "RANKING");
        adapter.addFragment(new TaskFragment(), "TASK");
        viewPager.setAdapter(adapter);
    }
}
