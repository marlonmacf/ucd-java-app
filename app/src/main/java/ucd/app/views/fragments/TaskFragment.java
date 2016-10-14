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

        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_task, container, false);

        List<CardView> cardViews = new ArrayList<>();

        cardViews.add((CardView) view.findViewById(R.id.card_view_task1));
        cardViews.add((CardView) view.findViewById(R.id.card_view_task2));

        //Percorre a lista de CheckBox adicionando onClickListener em cada um;
        for (final CardView cdview : cardViews) {
            //Coloca onClickListener em cada CheckBox;
            cdview.setOnClickListener(new View.OnClickListener() {
                @Override

                //FUTURAMENTE VERIFICAR SE O CARDVIEW ESTÁ MARCADO OU NÃO NO BANCO!!!!!!!!!!

                public void onClick(View v) {

                    //Pega o primeiro filho do determinado CardView (um TextView);
                    TextView t = (TextView) cdview.getChildAt(0);
                    //Pega o texto do TextView;
                    String msg = (String) t.getText();

                    new AlertDialog.Builder(cdview.getContext())

                            .setTitle(R.string.task_dialog_title)
                            .setMessage(msg)
                                    //Botão "Sim";
                            .setPositiveButton(R.string.dialog_button_sim, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Muda a Cor do CardView;
                                    cdview.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
                                    //Pega o TextView do Card;
                                    TextView t = (TextView) cdview.getChildAt(0);
                                    //Muda a cor do texto do CardView;
                                    t.setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimary));
                                }
                            })
                                    //Botão "Não";
                            .setNegativeButton(R.string.dialog_button_nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                                    //Adiciona Icone no AlertDialog;
                            .setIcon(android.R.drawable.ic_dialog_alert)
                                    //Mostra o AlertDialog;
                            .show();

                }//final do onClick;

            });//final do OnClickListener;
        }//final do for;
        return view;
    }

}
