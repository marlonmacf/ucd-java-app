package ucd.app.views.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import ucd.app.R;

public class PhotoFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView addImageButton;
    ImageView mainPhoto;
    Button submitComplaint;
    View view;


    private GoogleApiClient mGoogleApiClient;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);
        this.imageView1 = (ImageView) view.findViewById(R.id.image_view1);
        this.imageView2 = (ImageView) view.findViewById(R.id.image_view2);
        this.imageView3 = (ImageView) view.findViewById(R.id.image_view3);
        this.mainPhoto = (ImageView) view.findViewById(R.id.main_photo);
        this.addImageButton = (ImageView) view.findViewById(R.id.add_image_botton);
        this.submitComplaint = (Button) view.findViewById(R.id.submit_complaint);

        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageCapture(v);
            }
        });

        submitComplaint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });

        imageView1.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                ImageView imageView1 = (ImageView) v.findViewById(R.id.image_view1);
                ImageView imageView2 = (ImageView) v.findViewById(R.id.image_view2);
                ImageView imageView3 = (ImageView) v.findViewById(R.id.image_view3);
                ImageView imageViewMain = (ImageView) v.findViewById(R.id.main_photo);

                if (imageView1.getDrawable() == null) {
                    return false;
                } else {
                    imageView1.setImageBitmap(null);

                    if (imageView2.getDrawable() == null) {
                        imageViewMain.setImageBitmap(null);
                        return false;
                    } else {
                        imageView1.setImageDrawable(imageView2.getDrawable());
                        if (imageView3.getDrawable() == null) {
                            imageViewMain.setImageBitmap(null);
                            return false;
                        } else {
                            imageView2.setImageDrawable(imageView3.getDrawable());
                            imageViewMain.setImageBitmap(null);
                            return false;
                        }
                    }
                }
            }
        });

        imageView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        imageView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        callConnection();

        return view;
    }

    public void imageCapture(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get("data");
                this.mainPhoto.setImageBitmap(bitmap);

                if (this.imageView1.getDrawable() == null) {
                    this.imageView1.setImageBitmap(bitmap);
                } else {
                    if (this.imageView2.getDrawable() == null) {
                        this.imageView2.setImageBitmap(bitmap);
                    } else {
                        if (this.imageView3.getDrawable() == null) {
                            this.imageView3.setImageBitmap(bitmap);
                            this.addImageButton.setEnabled(false);
                        }
                    }
                }

                view.findViewById(R.id.obs).setEnabled(true);
                this.submitComplaint.setEnabled(true);
                this.submitComplaint.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
            }
        }
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    //LISTENER
    @Override
    public void onConnected(Bundle bundle) {


        Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (l != null) {

            Double la = l.getLatitude();
            Double lo = l.getLongitude();

        } else {
            Toast.makeText(PhotoFragment.this.getContext(), "GPS desligado", Toast.LENGTH_SHORT).show();
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
}