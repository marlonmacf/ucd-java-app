package ucd.app.views.drawers;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

import static ucd.app.views.activities.MainActivity.loggedUser;


public class settingsActivity extends AppCompatActivity {

    private EditText old_pass;
    private EditText new_pass1;
    private EditText new_pass2;

    private ApiService apiService;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        old_pass = (EditText) findViewById(R.id.old_pass);
        new_pass1 = (EditText) findViewById(R.id.new_pass1);
        new_pass2 = (EditText) findViewById(R.id.new_pass2);

        this.apiService = ApiClient.getClient().create(ApiService.class);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar.setVisibility(View.INVISIBLE);

        Button btn = (Button) findViewById(R.id.btn_alterar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser(view);
            }
        });
    }


    public void updateUser(final View view) {
        String senha = old_pass.getText().toString();
        String nova_senha1 = new_pass1.getText().toString();
        String nova_senha2 = new_pass2.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        if (senha.equals(loggedUser.getPassword())) {
            if (!senha.equals(nova_senha1)) {
                if (nova_senha1.equals(nova_senha2)) {

                    apiService.updateUser(loggedUser.getId().toString(), loggedUser.getEmail(),
                            loggedUser.getName(), nova_senha1, loggedUser.getInspector(),
                            loggedUser.getScore()).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            progressBar.setVisibility(View.INVISIBLE);
                            loggedUser = response.body();
                            Toast.makeText(settingsActivity.this, loggedUser.getPassword(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable throwable) {
                            progressBar.setVisibility(View.INVISIBLE);

                            new AlertDialog.Builder(view.getContext())
                                    .setTitle(R.string.msg_Erro)
                                    .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .show();
                        }
                    });
                } else {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle(R.string.msg_Erro)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setMessage(R.string.msg_senhas_diferentes)
                            .setPositiveButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            } else {
                new AlertDialog.Builder(view.getContext())
                        .setTitle(R.string.msg_Erro)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage(R.string.msg_nova_senha_igual)
                        .setPositiveButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                progressBar.setVisibility(View.INVISIBLE);

            }
        } else {
            new AlertDialog.Builder(view.getContext())
                    .setTitle(R.string.msg_Erro)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage(R.string.msg_senha_incorreta)
                    .setPositiveButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }

    }
}


