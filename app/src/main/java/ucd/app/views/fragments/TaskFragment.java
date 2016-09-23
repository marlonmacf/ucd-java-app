package ucd.app.views.fragments;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import ucd.app.R;

public class TaskFragment extends Fragment {

    private View view;

//    final int height = 400;

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
                        int minHeight = c.getHeight();

//                        expandView(minHeight, c);
                        c.setCardBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
                        TextView t = (TextView) c.getChildAt(1);
                        t.setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimary));
                        ckBox.setClickable(false);
//                        t.setText("teste com mais texto dento do mesmo cardView, teste com mais texto dento do mesmo cardView, teste com mais texto dento do mesmo cardView,teste com mais texto dento do mesmo cardView, teste com mais texto dento do mesmo cardView, teste com mais texto dento do mesmo cardView");


//                    } else {
//                        CardView c = (CardView) ckBox.getParent();
//                        collapseView(c);
                    }
                }
            });
        }
        return view;
    }

//    public void collapseView(final CardView c) {
//
//        int minHeight = c.getHeight();
//
//        ValueAnimator anim = ValueAnimator.ofInt(c.getMeasuredHeightAndState(),
//                minHeight);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int val = (Integer) valueAnimator.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams = c.getLayoutParams();
//                layoutParams.height = val;
//                c.setLayoutParams(layoutParams);
//
//            }
//        });
//        anim.start();
//    }
//
//    public void expandView(int minHeight, final CardView c) {
//
//        ValueAnimator anim = ValueAnimator.ofInt(c.getMeasuredHeightAndState(),
//                minHeight);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int val = (Integer) valueAnimator.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams =  c.getLayoutParams();
//                layoutParams.height = val;
//                c.setLayoutParams(layoutParams);
//            }
//        });
//        anim.start();
//
//    }

}
