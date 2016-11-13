package ucd.app.views.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.Complaint;
import ucd.app.entities.ComplaintPhoto;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;
import ucd.app.views.adapters.InfoWindowAdapter;

import static ucd.app.views.activities.MainActivity.latitude;
import static ucd.app.views.activities.MainActivity.location;
import static ucd.app.views.activities.MainActivity.longitude;

public class PlaceFragment extends Fragment {

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
        final View rootView = inflater.inflate(R.layout.fragment_place, container, false);
        final View infoView = inflater.inflate(R.layout.layout_marker, container, false);

        // Booting the service API and the progressBar.
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        this.progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        this.progressBar.setVisibility(View.VISIBLE);
        this.complaints = new ArrayList<>();

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
                if (ActivityCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.setInfoWindowAdapter(new InfoWindowAdapter(infoView));
                setupMarkers();

                if (location != null) {

                    // Zooming automatically to the location of the user.
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
        if (googleMap != null) {
            for (Complaint complaint : complaints) {
                Double latitude = Double.parseDouble(complaint.getLatitude());
                Double longitude = Double.parseDouble(complaint.getLongitude());

                String complaintPhotosBase64 = "";
                for (ComplaintPhoto complaintPhoto : complaint.getComplaintPhotos()) {
                    complaintPhotosBase64 += complaintPhoto.getPath() + ",";
                }

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(latitude, longitude));
                markerOptions.title(complaint.getDescription() + "-" + complaintPhotosBase64);

                switch (complaint.getStatus()) {
                    case "STARTED":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
                        break;
                    case "INSPECTED":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
                        break;
                    case "CHECKED":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
                        break;
                    case "DENOUNCED":
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
                        break;
                    default:
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
                }

                googleMap.addMarker(markerOptions);
            }
        }
    }
}