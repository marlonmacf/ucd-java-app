package ucd.app.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiService;
import ucd.app.rest.ApiClient;

import java.util.List;

public class UserAdapter {

    private ApiService apiService;
    private ProgressBar progressBar;
    private View view;

    public UserAdapter(Context context) {

        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.view = View.inflate(context, R.layout.activity_main, null);
        this.progressBar = (ProgressBar) this.view.findViewById(R.id.progressBar);
    }

    public void fetchUsers() {

        progressBar.setVisibility(View.VISIBLE);
        apiService.fetchUsers().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                TextView text_view_users = (TextView) view.findViewById(R.id.text_view_users);
                text_view_users.setText("USERS: \n" + response.body().toString());
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(view.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }

        });
    }
}
