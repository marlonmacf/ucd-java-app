package ucd.app.views.drawers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import ucd.app.R;

import static ucd.app.views.activities.MainActivity.*;

public class profileActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView score;
    private CheckBox inspect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        score = (TextView) findViewById(R.id.score);

        inspect = (CheckBox) findViewById(R.id.inspect);

        name.setText(loggedUser.getName());
        email.setText(loggedUser.getEmail());
        score.setText(String.valueOf(loggedUser.getScore()) + "pts");

        if(loggedUser.getInspector() == 1 ){
            inspect.setChecked(true);
        }
    }
}
