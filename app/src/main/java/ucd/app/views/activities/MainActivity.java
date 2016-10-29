package ucd.app.views.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import ucd.app.R;
import ucd.app.entities.Complaint;
import ucd.app.entities.User;
import ucd.app.views.adapters.ViewPagerAdapter;
import ucd.app.views.fragments.InfoFragment;
import ucd.app.views.fragments.PhotoFragment;
import ucd.app.views.fragments.PlaceFragment;
import ucd.app.views.fragments.RankingFragment;
import ucd.app.views.fragments.TaskFragment;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private final android.support.v4.app.Fragment infoFragment = new InfoFragment();
    private final android.support.v4.app.Fragment photoFragment = new PhotoFragment();
    private final android.support.v4.app.Fragment placeFragment = new PlaceFragment();
    private final android.support.v4.app.Fragment taskFragment = new TaskFragment();
    private final android.support.v4.app.Fragment rankingFragment = new RankingFragment();

    private GoogleApiClient mGoogleApiClient;

    public static Location location;
    public static Double latitude;
    public static Double longitude;
    public static User loggedUser;
    public static List<Complaint> complaints;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Chama a função para pegar as coordenadas do GPS.
        callConnection();
        createUser();

        // Booting the elements for the TabLayout in the Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        // Defines the number of tabs by setting appropriate fragment and tab name.
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(this.infoFragment);
        adapter.addFragment(this.photoFragment);
        adapter.addFragment(this.placeFragment);
        adapter.addFragment(this.taskFragment);
        adapter.addFragment(this.rankingFragment);
        viewPager.setAdapter(adapter);

        // Assigns the ViewPager to TabLayout, Tabs with only Icons.
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_info_dark);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_photo_dark);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_place_dark);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_task_dark);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_tab_ranking_dark);

    }

    /**
     * Listener GPS.
     *
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            showGpsErrorMessage(this);
        } else {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectionSuspended(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG", "onConnectionFailed(" + connectionResult + ")");
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void createUser() {
        loggedUser = (User) getIntent().getSerializableExtra("pessoas");
//        loggedUser = new User(1, "Marlon Andrel", "marlonmacf@gmail.com", "123456", Byte.parseByte("2"), Byte.parseByte("99"));
    }

    /**
     * Mostra menssagem de error se o GPS estiver desligado.
     *
     * @param context
     */
    public void showGpsErrorMessage(Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.gps_desligado)
                .setMessage(R.string.status_gps)

                .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
