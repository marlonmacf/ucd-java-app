package ucd.app.views.drawers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import it.sephiroth.android.library.tooltip.Tooltip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;
import ucd.app.views.activities.LoginActivity;

import static ucd.app.views.activities.MainActivity.*;

public class profileActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView score;
    private CheckBox inspect;

    private ApiService apiService;

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

        if (loggedUser.getInspector() == 1) {
            inspect.setChecked(true);
        }

        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void deleteUser(final View view) {

        new AlertDialog.Builder(view.getContext())
                .setTitle("Atenção!")
                .setMessage("Deseja realmente excluir o usuario " + loggedUser.getName() + "?")
                .setPositiveButton(R.string.dialog_button_sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final EditText input = new EditText(view.getContext());

                        input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        input.setHint(R.string.label_password);
                        input.setWidth(50);
                        input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        new android.app.AlertDialog.Builder(view.getContext())
                                .setTitle("Digite a senha.")
                                .setView(input)
                                .setPositiveButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (input.getText().toString().equals(loggedUser.getPassword())) {
                                            apiService.deleteUser(loggedUser.getId().toString()).enqueue(new Callback<User>() {
                                                @Override
                                                public void onResponse(Call<User> call, Response<User> response) {
                                                    new AlertDialog.Builder(view.getContext())
                                                            .setTitle("Erro ao excluir usuario.")
                                                            .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                }
                                                            })
                                                            .show();
                                                }

                                                @Override
                                                public void onFailure(Call<User> call, Throwable throwable) {
                                                    new AlertDialog.Builder(view.getContext())
                                                            .setTitle(R.string.msg_excluido_sucesso)
                                                            .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            })
                                                            .show();
                                                }
                                            });


                                        } else {
                                            new AlertDialog.Builder(view.getContext())
                                                    .setTitle(R.string.msg_Erro)
                                                    .setMessage(R.string.msg_senha_incorreta)
                                                    .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                        }
                                                    })
                                                    .show();
                                        }
                                    }
                                })
                                .show();
                    }
                })
                .setNegativeButton(R.string.dialog_button_nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }
}
