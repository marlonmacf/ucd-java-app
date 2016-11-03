package ucd.app.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.Serializable;

import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

import static ucd.app.views.activities.MainActivity.loggedUser;

public class LoginActivity extends AppCompatActivity implements Serializable {

    private ApiService apiService;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar.setVisibility(View.INVISIBLE);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                makeLogin(email, password);
            }
        });

        TextView btnRegister = (TextView) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRegister();
            }
        });

        // TODO: get the user from the shared preferences and make the login.
        // String email = "sharedEmail";
        // String password = "sharedPassword";
        // if (email != null || password != null) {
        //     makeLogin(email, password);
        // }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void makeLogin(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);

        apiService.login(email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loggedUser = response.body();

                Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                progressBar.setVisibility(View.INVISIBLE);

                new AlertDialog.Builder(LoginActivity.this)
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

    private void makeRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
