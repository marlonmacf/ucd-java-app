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

        List<CheckBox> checkBoxList = new ArrayList<>();// ------------------------------------------------------------------------------------------ Lista para todos os CheckBox;

        checkBoxList.add((CheckBox) view.findViewById(R.id.ckBox1)); // ----------------------------------------------------------------------------- Adicionando os CheckBox na lista;
        checkBoxList.add((CheckBox) view.findViewById(R.id.ckBox2)); //

        for (final CheckBox ckBox : checkBoxList) { // ---------------------------------------------------------------------------------------------- Percorre a lista de CheckBox adicionando onClickListener em cada um;
            ckBox.setOnClickListener(new View.OnClickListener() { // -------------------------------------------------------------------------------- Coloca onClickListener em cada CheckBox;
                @Override

                public void onClick(View v) {
                    if (((CheckBox) ckBox).isChecked()) {// ----------------------------------------------------------------------------------------- Clicou no CheckBox;

                        CardView c = (CardView) ckBox.getParent(); //-------------------------------------------------------------------------------- Pega o CardView do determinado CheckBox;
                        TextView t = (TextView) c.getChildAt(1); // --------------------------------------------------------------------------------- Pega o primeiro filho do determinado CardView (um TextView);
                        String msg =  (String) t.getText(); // -------------------------------------------------------------------------------------- Pega o texto do TextView;

                        new AlertDialog.Builder(c.getContext())

                                .setTitle(R.string.task_dialog_title)
                                .setMessage(msg)
                                .setPositiveButton(R.string.task_dialog_button2, new DialogInterface.OnClickListener() { // ------------------------ Botão "Sim";
                                    public void onClick(DialogInterface dialog, int which) {
                                        CardView c = (CardView) ckBox.getParent(); // -------------------------------------------------------------- Pega o CardView do determinado CheckBox;
                                        c.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark)); //----------- Muda a Cor do CardView;
                                        TextView t = (TextView) c.getChildAt(1); // ---------------------------------------------------------------- Pega o TextView do Card;
                                        t.setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimary)); // ------------------------ Muda a cor do texto do CardView;
                                        ckBox.setClickable(false); // ------------------------------------------------------------------------------ Desabilita o click no CheckBox;
                                    }
                                })
                                .setNegativeButton(R.string.task_dialog_button1, new DialogInterface.OnClickListener() { // ------------------------ Botão "Não";
                                    public void onClick(DialogInterface dialog, int which) {
                                        ckBox.setChecked(false); // -------------------------------------------------------------------------------- Desmarca o CheckBox;
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert) // -------------------------------------------------------------------- Adiciona Icone no AlertDialog;
                                .show(); // -------------------------------------------------------------------------------------------------------- Mostra o AlertDialog;

                    }//final do if;
                }//final do onClick;

            });//final do OnClickListener;
        }//final do for;
        return view;
    }

}
