package ucd.app.views.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ucd.app.R;

public class TaskFragment extends Fragment {

    private View rootView;

    public TaskFragment() {
        // Required empty public constructor.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        rootView = inflater.inflate(R.layout.fragment_task, container, false);

        List<CardView> cardViews = new ArrayList<>();
        cardViews.add((CardView) rootView.findViewById(R.id.card_view_task1));
        cardViews.add((CardView) rootView.findViewById(R.id.card_view_task2));
        cardViews.add((CardView) rootView.findViewById(R.id.card_view_task3));
        cardViews.add((CardView) rootView.findViewById(R.id.card_view_task4));

        // Adicionando onClickListener para todos os CheckBoxes.
        for (final CardView cdview : cardViews) {

            // TODO: Futuramente verificar se o cardview está marcado ou não no banco.
            cdview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView t = (TextView) cdview.getChildAt(1);
                    String msg = (String) t.getText();
                    final CheckBox ck = (CheckBox) cdview.getChildAt(0);

                    new AlertDialog.Builder(cdview.getContext())

                            .setTitle(R.string.task_dialog_title)
                            .setMessage(msg)
                            .setPositiveButton(R.string.dialog_button_sim, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    cdview.setCardBackgroundColor(ContextCompat.getColor(rootView.getContext(), R.color.colorPrimaryDark));
                                    TextView text = (TextView) cdview.getChildAt(0);
                                    text.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    ck.setChecked(true);
                                }
                            })
                            .setNegativeButton(R.string.dialog_button_nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
        }
        return rootView;
    }
}
