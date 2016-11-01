package ucd.app.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

public class register_acitivity extends AppCompatActivity implements Serializable {


    private ProgressBar progressBar;

    public static ApiService apiService;
    public static User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register_acitivity);
        apiService = ApiClient.getClient().create(ApiService.class);
        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

        // Finish the loading.
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void btn_register(final View view) {
        // Start loading.
        progressBar.setVisibility(View.VISIBLE);

        int inspec;

        final String name = ((EditText) findViewById(R.id.name)).getText().toString();
        final String email = ((EditText) findViewById(R.id.email)).getText().toString();
        final String password = ((EditText) findViewById(R.id.password)).getText().toString();
        final Boolean inspector = ((CheckBox) findViewById(R.id.ck_inspector)).isChecked();

        if (inspector) {
            inspec = 1;
        } else {
            inspec = 0;
        }

        byte score = 0;

        apiService.insertUser(email, name, password, inspec, score).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loggedUser = response.body();
                Intent intent = new Intent(view.getContext(), login_activity.class);

                // Finish the loading.
                progressBar.setVisibility(View.INVISIBLE);

                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                // Finish the loading.
                progressBar.setVisibility(View.INVISIBLE);

                new AlertDialog.Builder(view.getContext())
                        .setTitle(R.string.login_fail)
                        .setMessage(R.string.login_fail_msg)
                        .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }

}

