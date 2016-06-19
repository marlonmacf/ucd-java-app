package ucd.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Service for access the RETROFIT API.
    private ApiService apiService;

    //ProgressBar for loading the requests.
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Booting the service API and the progressBar.
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Start loading
        progressBar.setVisibility(View.VISIBLE);

        //Fetching for all users.
        apiService.fetchUsers().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                //Make a objecto of users and show on the view.
                TextView text_view_users = (TextView) findViewById(R.id.text_view_users);
                text_view_users.setText("USERS: \n" + response.body().toString());

                //Finish the loading.
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                //Throw friendly exception.
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                //Finish the loading.
                progressBar.setVisibility(View.INVISIBLE);
            }

        });
    }
}
