package ucd.app.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ucd.app.R;

public class RankingFragment extends Fragment {

    // Service for access the RETROFIT API.
    //private ApiService apiService;

    // ProgressBar for loading the requests.
    //private ProgressBar progressBar;

    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Booting the service API and the progressBar.
        //this.apiService = ApiClient.getClient().create(ApiService.class);
        //this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Start loading
        //progressBar.setVisibility(View.VISIBLE);

        // Fetching for all users.
        //apiService.fetchUsers().enqueue(new Callback<List<User>>() {

        //@Override
        //public void onResponse(Call<List<User>> call, Response<List<User>> response) {

        // Make a objecto of users and show on the view.
        //TextView text_view_users = (TextView) findViewById(R.id.text_view_users);
        //text_view_users.setText("USERS: \n" + response.body().toString());

        // Finish the loading.
        //progressBar.setVisibility(View.INVISIBLE);
        //}

        //@Override
        //public void onFailure(Call<List<User>> call, Throwable t) {

        // Throw friendly exception.
        //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

        // Finish the loading.
        //progressBar.setVisibility(View.INVISIBLE);
        //}

        //});
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

}