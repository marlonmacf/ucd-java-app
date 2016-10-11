package ucd.app.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.User;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

public class RankingFragment extends Fragment {

    // Service for access the RETROFIT API.
    private ApiService apiService;

    private ProgressBar progressBar;

    private ListView listView;

    private List<String> values;

    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        // Booting the service API and the progressBar.
        this.apiService = ApiClient.getClient().create(ApiService.class);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.listView = (ListView) view.findViewById(R.id.ranking_list);
        this.values = new ArrayList<>();

        // Start loading
        progressBar.setVisibility(View.VISIBLE);

        // Fetching for all users.
        apiService.fetchRanking().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                // Make a object of users and show on the view.
                int count = 1;
                for (User user : response.body()) {
                    values.add((count++) + "º   " + user.getName() + " " + user.getScore() + "pts");
                }

                // Assign adapter to ListView
                listView.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values));

                // Finish the loading.
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable throwable) {
                Toast.makeText(view.getContext(), throwable.toString(), Toast.LENGTH_SHORT).show();

                // Finish the loading.
                progressBar.setVisibility(View.INVISIBLE);
            }

        });

        return view;
    }
}