package ucd.app.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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

    // ProgressBar for loading the requests.
    //private ProgressBar progressBar;

    private ListView listView ;

    private String[] values;

    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        // Get ListView object from xml
        this.listView = (ListView)view.findViewById(R.id.ranking_list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Booting the service API and the progressBar.
        this.apiService = ApiClient.getClient().create(ApiService.class);
        //this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        // Start loading
        //progressBar.setVisibility(View.VISIBLE);

        // Fetching for all users.
//        apiService.fetchUsers().enqueue(new Callback<List<User>>() {

        //@Override
//        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//
//        // Make a objecto of users and show on the view.
////        TextView text_view_users = (TextView) findViewById(R.id.text_view_users);
////        text_view_users.setText("USERS: \n" + response.body().toString());
//
//            values[0] = response.body().toString();
//
//        // Finish the loading.
//        //progressBar.setVisibility(View.INVISIBLE);
//        }
//
//        //@Override
//        public void onFailure(Call<List<User>> call, Throwable t) {
//
//        // Throw friendly exception.
//        //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
//
//        // Finish the loading.
//        //progressBar.setVisibility(View.INVISIBLE);
//        }
//
//        });








        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data



        return view;
    }



}