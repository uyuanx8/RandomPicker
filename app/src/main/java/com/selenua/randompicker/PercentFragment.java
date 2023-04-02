package com.selenua.randompicker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class PercentFragment extends Fragment {

    int curMax;
    Animation pickAnim;
    boolean isOnAnimation;

    public PercentFragment() {
        // Required empty public constructor
    }

    public static PercentFragment newInstance(String param1, String param2) {
        PercentFragment fragment = new PercentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_percent, container, false);

        TextView txtMain = mainView.findViewById(R.id.percent_main);
        EditText txtMax = mainView.findViewById(R.id.percent_barnum);
        SeekBar seekBarNum = mainView.findViewById(R.id.percent_bar);
        ImageButton pickBtn = mainView.findViewById(R.id.percent_btn);

        curMax = 1;
        isOnAnimation = false;
        txtMax.setFocusable(true);

        txtMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int selection = txtMax.getSelectionStart();
                String str = charSequence.toString();
                if (!str.isEmpty()) {
                    double db = Double.parseDouble(str);
                    while (!(db >= 0 && db <= 1)) {
                        db /= 10;
                        selection++;
                    }
                    curMax = (int) (db * 10000);
                    if (curMax < 0) {
                        curMax = 0;
                    }

                } else {
                    curMax = 0;
                }
                seekBarNum.setProgress(curMax);
                txtMax.setSelection(selection);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        pickAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.pick_anim);
        pickAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isOnAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isOnAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        seekBarNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                curMax = i;
                txtMax.setText(String.format("%.4f", (double) i / 10000) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnAnimation) {
                    Log.d("asdf","Crash 1");
                    String str = txtMax.getText().toString();
                    if (!str.isEmpty()) {
                        double db = Double.parseDouble(str);
                        while (!(db >= 0 && db <= 1)) {
                            db /= 10;
                        }
                        Log.d("asdf","Crash 2");
                        curMax = (int) (db * 10000);
                        if (curMax < 0) {
                            curMax = 0;
                        }

                    } else {
                        curMax = 0;
                    }
                    Log.d("asdf","Crash 3");

                    seekBarNum.setProgress(curMax);
                    txtMax.setText(String.format("%.4f", (double) curMax / 10000) + "");

                    Log.d("asdf","Crash 4");

                    int rand = (int) (Math.random() * 10000);
                    if (rand < curMax) {
                        txtMain.setText("O");
                    } else {
                        txtMain.setText("X");
                    }
                    Log.d("asdf","Crash 5");
                    txtMain.startAnimation(pickAnim);
                    Log.d("asdf","Crash 6");
                }
            }
        });

        return mainView;
    }
}