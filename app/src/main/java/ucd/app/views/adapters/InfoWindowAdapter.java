package ucd.app.views.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import ucd.app.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View infoWindow;

    public InfoWindowAdapter(View view) {
        infoWindow = view;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ArrayList<Bitmap> photosBitmap = new ArrayList<>();
        List<String> complaintPhotosBase64 = Arrays.asList(marker.getSnippet().split(","));

        for (String complaintPhotoBase64 : complaintPhotosBase64) {
            byte[] decodedString = Base64.decode(complaintPhotoBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            photosBitmap.add(decodedByte);
        }

        try {
            ImageView complaintImage1 = (ImageView) infoWindow.findViewById(R.id.complaint_image1);
            complaintImage1.setImageBitmap(photosBitmap.get(0));
        } catch (Exception ignored) {}

        try {
            ImageView complaintImage2 = (ImageView) infoWindow.findViewById(R.id.complaint_image2);
            complaintImage2.setImageBitmap(photosBitmap.get(1));
        } catch (Exception ignored) {}

        try {
            ImageView complaintImage3 = (ImageView) infoWindow.findViewById(R.id.complaint_image3);
            complaintImage3.setImageBitmap(photosBitmap.get(2));
        } catch (Exception ignored) {}

        TextView complaintDescription = (TextView) infoWindow.findViewById(R.id.complaint_description);
        complaintDescription.setText(marker.getTitle());

        return infoWindow;
    }
}