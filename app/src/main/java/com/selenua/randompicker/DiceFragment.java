package com.selenua.randompicker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceFragment extends Fragment {

    private int diceResource[] = {R.drawable.dice_one,
        R.drawable.dice_two,
        R.drawable.dice_three,
        R.drawable.dice_four,
        R.drawable.dice_five,
        R.drawable.dice_six};

    ImageView diceFirst, diceSecond, diceThird, diceFourth;
    private int diceNum = 0;

    boolean isOnAnimation;
    Animation rollStartAnim;
    Animation rollEndAnim;
    Animation rollFirstAnim;

    public DiceFragment() {
        // Required empty public constructor
    }

    public static DiceFragment newInstance() {
        DiceFragment fragment = new DiceFragment();
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
        View mainView = inflater.inflate(R.layout.fragment_dice, container, false);


        diceFirst = mainView.findViewById(R.id.dice_first);
        diceSecond = mainView.findViewById(R.id.dice_second);
        diceThird = mainView.findViewById(R.id.dice_third);
        diceFourth = mainView.findViewById(R.id.dice_fourth);

        TextView hiddenL = mainView.findViewById(R.id.dice_hiddenl);
        TextView hiddenR = mainView.findViewById(R.id.dice_hiddenr);

        SeekBar seekBar = mainView.findViewById(R.id.dice_bar);
        ImageButton rollBtn = mainView.findViewById(R.id.dice_roll);
        LinearLayout diceLayer = mainView.findViewById(R.id.dice_layer);

        rollFirstAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.dice_roll_start_anim);
        rollFirstAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isOnAnimation=true;
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                rollDice(diceNum);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        rollStartAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.dice_roll_start_anim);
        rollEndAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.dice_roll_end_anim);
        rollEndAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isOnAnimation=true;
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                isOnAnimation=false;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                                   diceNum = i;
                                                   diceSecond.setVisibility(View.GONE);
                                                   diceThird.setVisibility(View.GONE);
                                                   diceFourth.setVisibility(View.GONE);
                                                   hiddenL.setVisibility(View.GONE);
                                                   hiddenR.setVisibility(View.GONE);
                                                   if(diceNum>=3){
                                                       diceLayer.setVisibility(View.VISIBLE);
                                                   }
                                                   else{
                                                       diceLayer.setVisibility(View.GONE);
                                                   }
                                                   switch(diceNum){
                                                       case 4:
                                                           diceFourth.setVisibility(View.VISIBLE);
                                                       case 3:
                                                           diceThird.setVisibility(View.VISIBLE);
                                                           if(diceNum!=4){
                                                               hiddenL.setVisibility(View.VISIBLE);
                                                               hiddenR.setVisibility(View.VISIBLE);
                                                           }
                                                       case 2:
                                                           diceSecond.setVisibility(View.VISIBLE);
                                                       default:
                                                           break;
                                                   }

                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {

                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {

                                               }
                                           });
                rollBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!isOnAnimation){
                        rollDiceAnim(diceNum);
                        }
                    }
                });

        return mainView;
    }
    public void rollDiceAnim(int n){
        diceFirst.startAnimation(rollFirstAnim);
        switch(n){
            case 4:
                diceFourth.startAnimation(rollStartAnim);
            case 3:
                diceThird.startAnimation(rollStartAnim);
            case 2:
                diceSecond.startAnimation(rollStartAnim);
            default:
                break;
        }
    }
    public void rollDice(int n){
        int rand = (int)(Math.random()*6);
        diceFirst.setImageResource(diceResource[rand]);
        diceFirst.startAnimation(rollEndAnim);
        switch(n){
            case 4:
                rand = (int)(Math.random()*6);
                diceFourth.setImageResource(diceResource[rand]);
                diceFourth.startAnimation(rollEndAnim);
            case 3:
                rand = (int)(Math.random()*6);
                diceThird.setImageResource(diceResource[rand]);
                diceThird.startAnimation(rollEndAnim);
            case 2:
                rand = (int)(Math.random()*6);
                diceSecond.setImageResource(diceResource[rand]);
                diceSecond.startAnimation(rollEndAnim);
            default:
                break;
        }
    }
}