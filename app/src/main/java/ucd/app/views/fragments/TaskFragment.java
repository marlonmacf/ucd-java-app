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


        view = inflater.inflate(R.layout.fragment_task, container, false);// ------------------------------------------------------------------------ Inflate the layout for this fragment

        List<CardView> cardViews = new ArrayList<>();

        cardViews.add((CardView) view.findViewById(R.id.card_view_task1));
        cardViews.add((CardView) view.findViewById(R.id.card_view_task2));

        for (final CardView cdview : cardViews) { // ------------------------------------------------------------------------------------------------- Percorre a lista de CheckBox adicionando onClickListener em cada um;
            cdview.setOnClickListener(new View.OnClickListener() { // -------------------------------------------------------------------------------- Coloca onClickListener em cada CheckBox;
                @Override

                //FUTURAMENTE VERIFICAR SE O CARDVIEW ESTÁ MARCADO OU NÃO !!!!!!!!!!

                public void onClick(View v) {

                    TextView t = (TextView) cdview.getChildAt(0); // --------------------------------------------------------------------------------- Pega o primeiro filho do determinado CardView (um TextView);
                    String msg = (String) t.getText(); // -------------------------------------------------------------------------------------------- Pega o texto do TextView;

                    new AlertDialog.Builder(cdview.getContext())

                            .setTitle(R.string.task_dialog_title)
                            .setMessage(msg)
                            .setPositiveButton(R.string.task_dialog_button2, new DialogInterface.OnClickListener() { // ------------------------------ Botão "Sim";
                                public void onClick(DialogInterface dialog, int which) {
                                    cdview.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark)); //----------- Muda a Cor do CardView;
                                    TextView t = (TextView) cdview.getChildAt(0); // ---------------------------------------------------------------- Pega o TextView do Card;
                                    t.setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimary)); // ----------------------------- Muda a cor do texto do CardView;
                                }
                            })
                            .setNegativeButton(R.string.task_dialog_button1, new DialogInterface.OnClickListener() { // ----------------------------- Botão "Não";
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert) // ------------------------------------------------------------------------- Adiciona Icone no AlertDialog;
                            .show(); // ------------------------------------------------------------------------------------------------------------- Mostra o AlertDialog;

                }//final do onClick;

            });//final do OnClickListener;
        }//final do for;
        return view;
    }

}
