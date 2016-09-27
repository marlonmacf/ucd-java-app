package ucd.app.views.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ucd.app.R;

public class PhotoFragment extends Fragment {

    ImageView imageTeste;
//    ImageButton addImageButton;
    ImageView addImageButton;
    Button submitComplaint;
    View view;

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
        this.imageTeste = (ImageView) view.findViewById(R.id.image_test);
//        this.addImageButton = (ImageButton) view.findViewById(R.id.add_image_botton);
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
            public void onClick(View v) {}
        });

        return view;
    }

    public void imageCapture(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null) {
            Bundle bundle = data.getExtras();
            if(bundle != null){
                Bitmap bitmap = (Bitmap) bundle.get("data");
                this.imageTeste.setImageBitmap(bitmap);
                view.findViewById(R.id.obs).setEnabled(true);
                this.submitComplaint.setEnabled(true);
                this.submitComplaint.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
            }
        }
    }
}