package ucd.app.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import ucd.app.R;

public class TaskFragment extends Fragment {

    private View view;

    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_task, container, false);

        List<CheckBox> checkBoxList = new ArrayList<>();

        checkBoxList.add((CheckBox) view.findViewById(R.id.ckBox1));
        checkBoxList.add((CheckBox) view.findViewById(R.id.ckBox2));

        for (final CheckBox ckBox : checkBoxList) {
            ckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox) ckBox).isChecked()) { //Clicou no CheckBox
                        CardView c = (CardView) ckBox.getParent();
                        c.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
                        TextView t = (TextView) c.getChildAt(1);
                        t.setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimary));
                        ckBox.setClickable(false);
                    }
                }
            });
        }
        return view;
    }

}
