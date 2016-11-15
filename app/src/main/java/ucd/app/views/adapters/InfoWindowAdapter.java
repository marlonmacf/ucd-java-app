package ucd.app.views.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import ucd.app.R;

import java.util.Arrays;
import java.util.List;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View infoWindow;

    public InfoWindowAdapter(View view) {
        infoWindow = view;
    }

    @Override
    public View getInfoWindow(final Marker marker) {
        if (marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
            marker.showInfoWindow();
        }
        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        updateInfoWindow(marker);
        return infoWindow;
    }

    private void updateInfoWindow(final Marker marker) {
        String title = "";
        String links = "";

        try {
            title = Arrays.asList(Arrays.asList(marker.getTitle().split("ID")).get(1).split("-")).get(0);
            links = Arrays.asList(Arrays.asList(marker.getTitle().split("ID")).get(1).split("-")).get(1);
        } catch (Exception exception) {
            title = Arrays.asList(Arrays.asList(marker.getTitle().split("ID")).get(1).split("-")).get(0);
        }

        TextView complaintDescription = (TextView) infoWindow.findViewById(R.id.complaint_description);
        complaintDescription.setText(title);

        List<String> complaintPhotosBase64 = Arrays.asList(links.split(","));

        ImageView complaintImage1 = (ImageView) infoWindow.findViewById(R.id.complaint_image1);
        ImageView complaintImage2 = (ImageView) infoWindow.findViewById(R.id.complaint_image2);
        ImageView complaintImage3 = (ImageView) infoWindow.findViewById(R.id.complaint_image3);

        Picasso picasso = Picasso.with(infoWindow.getContext());
        try {
            picasso.load(complaintPhotosBase64.get(0)).into(complaintImage1, new Callback() {
                @Override
                public void onSuccess() {
                    if (marker.isInfoWindowShown()) {
                        marker.hideInfoWindow();
                        marker.showInfoWindow();
                    }
                }

                @Override
                public void onError() {}
            });
        } catch (Exception exception) {
            complaintImage1.setImageBitmap(null);
        }

        try {
            picasso.load(complaintPhotosBase64.get(1)).into(complaintImage2, new Callback() {
                @Override
                public void onSuccess() {
                    if (marker.isInfoWindowShown()) {
                        marker.hideInfoWindow();
                        marker.showInfoWindow();
                    }
                }

                @Override
                public void onError() {}
            });
        } catch (Exception exception) {
            complaintImage2.setImageBitmap(null);
        }

        try {
            picasso.load(complaintPhotosBase64.get(2)).into(complaintImage3, new Callback() {
                @Override
                public void onSuccess() {
                    if (marker.isInfoWindowShown()) {
                        marker.hideInfoWindow();
                        marker.showInfoWindow();
                    }
                }

                @Override
                public void onError() {}
            });
        } catch (Exception exception) {
            complaintImage3.setImageBitmap(null);
        }
    }
}