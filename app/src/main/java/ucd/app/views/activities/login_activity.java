package ucd.app.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import java.io.Serializable;
import java.util.ArrayList;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

public class login_activity extends AppCompatActivity implements Serializable {

    public static ApiService apiService;
    public static User user;
    public static String email;
    public static String password;

    //Array temporário. somente para testes staticos.
    public ArrayList<User> pessoas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);

        apiService = ApiClient.getClient().create(ApiService.class);

        pessoas.add(new User(1, "Marlon", "marlonmacf@gmail.com", "123456", Byte.parseByte("2"), Byte.parseByte("26")));
        pessoas.add(new User(1, "Iago", "iago.olvr@gmail.com", "123456", Byte.parseByte("2"), Byte.parseByte("18")));

//        TODO: Verificar o WebService. Rota "/login" não passa nenhum parametro;
//        apiService.login(email, password).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                user = response.body();
//                startActivity(it);
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(login_activity.this, "FAIL !", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    public void btn_entrar(View v) {

        email = ((EditText) findViewById(R.id.email)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();

        /**
         * Verifica se é o 1º usuario (Marlon) ou 2º usuario (Iago).
         * Verifição statica.
         * Caso nenhum abre o AlertDialog.
         */
        if (email.equals(pessoas.get(0).getEmail()) && password.equals(pessoas.get(0).getPassword())) {
            Intent it = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            it.putExtra("pessoas", pessoas.get(0));
            startActivity(it);
        } else {
            if (email.equals(pessoas.get(1).getEmail()) && password.equals(pessoas.get(1).getPassword())) {
                Intent it = new Intent(this, MainActivity.class);
                Bundle bundle = new Bundle();
                it.putExtra("pessoas", pessoas.get(1));
                startActivity(it);
            } else {
                new AlertDialog.Builder(v.getContext())
                        .setTitle(R.string.login_fail)
                        .setMessage(R.string.login_fail_msg)
                        .setNeutralButton(R.string.dialog_neutral, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        }
    }

    public void btn_register(View v){
        Intent it = new Intent(this, register_acitivity.class);
        startActivity(it);
    }

}
