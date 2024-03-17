package com.uyuanx.randompicker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


public class NumberFragment extends Fragment {


    int curMax;

    boolean isOnAnimation;
    Animation pickAnim;

    public NumberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static NumberFragment newInstance() {
        NumberFragment fragment = new NumberFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_number, container, false);

        TextView txtMain = mainView.findViewById(R.id.number_main);
        EditText txtMax = mainView.findViewById(R.id.number_barnum);
        SeekBar seekBarNum = mainView.findViewById(R.id.number_bar);
        ImageButton pickBtn = mainView.findViewById(R.id.number_btn);

        curMax = 1;
        isOnAnimation = false;
        txtMax.setFocusable(true);

        txtMax.addTextChangedListener(new TextWatcher() {
                                          @Override
                                          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                          }

                                          @Override
                                          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                              String str = charSequence.toString();
                                              if(!str.isEmpty()) {
                                                  curMax = Integer.parseInt(str);
                                                  if(curMax<=0){
                                                      curMax=1;
                                                  }
                                              }
                                              else{
                                                  curMax = 1;
                                              }
                                              seekBarNum.setProgress(curMax);
                                              txtMax.setSelection(txtMax.length());
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
                txtMax.setText(i+"");
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
                    String str = txtMax.getText().toString();
                    if(!str.isEmpty()) {
                        curMax = Integer.parseInt(str);
                        if(curMax<=0){
                            curMax=1;
                        }
                    }
                    else{
                        curMax = 1;
                    }
                    seekBarNum.setProgress(curMax);
                    txtMax.setText(curMax+"");

                    int rand = (int) (Math.random() * curMax)+1;
                    txtMain.setText(rand+"");
                    txtMain.startAnimation(pickAnim);
                }
            }
        });

        return mainView;
    }
}