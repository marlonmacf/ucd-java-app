package ucd.app.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Arrays;
import java.util.List;

import ucd.app.R;

/**
 * Created by Iago on 16/09/2016.
 */

public class ListViewAdapter extends BaseAdapter {


    public View view;
    public LayoutInflater inflater;
    private String[] values = new String[] { "Android List View",
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Android Example List View"
    };

    List listas = Arrays.asList(values);

    public ListViewAdapter(List lista, View view) {
        this.listas = lista;
        this.view = view;
    }

    @Override
    public int getCount() {
        return listas.size();
    }

    @Override
    public Object getItem(int position) {
        return listas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_ranking, container, false);
        String item = (String)listas.get(position);
        return null;
    }


}
