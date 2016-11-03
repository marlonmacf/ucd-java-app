package ucd.app.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import static ucd.app.views.activities.MainActivity.loggedUser;

public class RegisterActivity extends AppCompatActivity implements Serializable {

    private ProgressBar progressBar;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        this.progressBar.setVisibility(View.INVISIBLE);

        Button btnLogin = (Button) findViewById(R.id.btnSignup);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeSignup(view);
            }
        });
    }

    private void makeSignup(final View view) {
        progressBar.setVisibility(View.VISIBLE);

        final String name = ((EditText) findViewById(R.id.name)).getText().toString();
        final String email = ((EditText) findViewById(R.id.email)).getText().toString();
        final String password = ((EditText) findViewById(R.id.password)).getText().toString();
        final Boolean inspector = ((CheckBox) findViewById(R.id.ck_inspector)).isChecked();

        apiService.insertUser(email, name, password, ((inspector) ? 1 : 0), (byte) 0).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBar.setVisibility(View.INVISIBLE);
                loggedUser = response.body();

                Intent intent = new Intent(RegisterActivity.this, ContentActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
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