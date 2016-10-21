package ucd.app.views.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ucd.app.R;

public class PlaceFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap googleMap;
    private MapView googleMapView;
    private GoogleApiClient googleApiClient;

    private Location location;
    private Double longitude;
    private Double latitude;



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
        View rootView = inflater.inflate(R.layout.fragment_place, container, false);

        googleMapView = (MapView) rootView.findViewById(R.id.mapView);
        googleMapView.onCreate(savedInstanceState);

        MapsInitializer.initialize(rootView.getContext());
        googleMapView.onResume();

        callConnection();

        googleMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {

                googleMap = mMap;
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map.
                googleMap.addMarker(new MarkerOptions().position(new LatLng(-19.7155493, -47.9670743)).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                // TODO: Change this usage to get the latitude and longitude from the current location.
                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-19.7155493, -47.9670743)).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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

    /**
     * Listener GPS. Pega a localização do GPS.
     *
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location == null) {

            // TODO: Change this for a more friendly message...
            Toast.makeText(this.getContext(), "Location is missing! Verify your GPS.", Toast.LENGTH_SHORT).show();
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
        googleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }
}