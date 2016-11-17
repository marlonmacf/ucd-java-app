package ucd.app.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ucd.app.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }

    public void manual(View view) {

        Uri uri = Uri.parse("https://www.dropbox.com/s/qai07fkmfchgsrz/Manual%20do%20Usu%C3%A1rio%20-%20UCD.pdf?dl=0");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
