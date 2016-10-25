package ucd.app.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.Complaint;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

import static ucd.app.views.activities.MainActivity.latitude;
import static ucd.app.views.activities.MainActivity.location;
import static ucd.app.views.activities.MainActivity.longitude;

public class PlaceFragment extends Fragment {

    // Service for access the RETROFIT API.
    private ApiService apiService;

    private List<Complaint> complaints;
    private ProgressBar progressBar;
    private GoogleMap googleMap;
    private MapView googleMapView;

    public PlaceFragment() {
        // Required empty public constructor.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        final View rootView = inflater.inflate(R.layout.fragment_place, container, false);

        // Booting the service API and the progressBar.
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        this.complaints = new ArrayList<>();

        // Start loading.
        progressBar.setVisibility(View.VISIBLE);

        // Fetching for all users.

        if (complaints.isEmpty()) {
            apiService.fetchComplaints().enqueue(new Callback<List<Complaint>>() {

                @Override
                public void onResponse(Call<List<Complaint>> call, Response<List<Complaint>> response) {
                    complaints = response.body();
                    setupMarkers();

                    // Finish the loading.
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<List<Complaint>> call, Throwable throwable) {
                    Toast.makeText(rootView.getContext(), throwable.toString(), Toast.LENGTH_SHORT).show();

                    // Finish the loading.
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }

        googleMapView = (MapView) rootView.findViewById(R.id.mapView);
        googleMapView.onCreate(savedInstanceState);

        MapsInitializer.initialize(rootView.getContext());
        googleMapView.onResume();

        googleMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {

                googleMap = mMap;
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map.
                setupMarkers();

                if (location != null) {

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        googleMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        googleMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        googleMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        googleMapView.onLowMemory();
    }

    private void setupMarkers() {
        if(googleMap != null) {
            for (Complaint complaint : complaints) {
                Double latitude = Double.parseDouble(complaint.getLatitude());
                Double longitude = Double.parseDouble(complaint.getLongitude());
                String title = complaint.getDescription();
                String description = complaint.getStatus();

                googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(title).snippet(description));
            }
        }
    }
}