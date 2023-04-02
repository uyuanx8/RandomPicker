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

public class CoinFragment extends Fragment {

    ImageView coinImg[] = new ImageView[9];
    int coinHeads[] = new int[9];

    private int coinNum = 1;

    boolean isOnAnimation = false;
    Animation flipStartAnim;
    Animation flipEndAnim;
    Animation flipFirstAnim;

    private int coinResource[] = {R.drawable.coin_heads,
            R.drawable.coin_tails};

    public CoinFragment() {

    }

    public static CoinFragment newInstance(String param1, String param2) {
        CoinFragment fragment = new CoinFragment();
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
        View mainView = inflater.inflate(R.layout.fragment_coin, container, false);

        coinImg[0] = mainView.findViewById(R.id.coin_first);
        coinImg[1] = mainView.findViewById(R.id.coin_second);
        coinImg[2] = mainView.findViewById(R.id.coin_third);
        coinImg[3] = mainView.findViewById(R.id.coin_fourth);
        coinImg[4] = mainView.findViewById(R.id.coin_fifth);
        coinImg[5] = mainView.findViewById(R.id.coin_sixth);
        coinImg[6] = mainView.findViewById(R.id.coin_seventh);
        coinImg[7] = mainView.findViewById(R.id.coin_eighth);
        coinImg[8] = mainView.findViewById(R.id.coin_ninth);

        ImageButton coinBtn = mainView.findViewById(R.id.coin_flip);

        SeekBar seekBar = mainView.findViewById(R.id.coin_bar);

        LinearLayout miniLayout[] = {mainView.findViewById(R.id.coin_mini_layout_first),
                mainView.findViewById(R.id.coin_mini_layout_second),
                mainView.findViewById(R.id.coin_mini_layout_third)};

        flipFirstAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.coin_flip_start_anim);
        flipFirstAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isOnAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coinFlip(coinNum);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        flipStartAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.coin_flip_start_anim);
        flipEndAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.coin_flip_end_anim);
        flipEndAnim.setAnimationListener(new Animation.AnimationListener() {
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

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                                   coinNum = i;
                                                   if(i>=7){
                                                       miniLayout[1].setVisibility(View.VISIBLE);
                                                       miniLayout[2].setVisibility(View.VISIBLE);
                                                   }
                                                   else if(i>=4){
                                                       miniLayout[1].setVisibility(View.VISIBLE);
                                                       miniLayout[2].setVisibility(View.GONE);
                                                   }
                                                   else{
                                                       miniLayout[1].setVisibility(View.GONE);
                                                       miniLayout[2].setVisibility(View.GONE);
                                                   }
                                                   for(int j=0;j<i;j++){
                                                       coinImg[j].setVisibility(View.VISIBLE);
                                                   }
                                                   for(int j=i;j<9;j++){
                                                       coinImg[j].setVisibility(View.GONE);
                                                   }
                                                   switch(i){
                                                       case 4:
                                                           coinImg[4].setVisibility(View.INVISIBLE);
                                                       case 5:
                                                           coinImg[5].setVisibility(View.INVISIBLE);
                                                           break;
                                                       case 7:
                                                           coinImg[7].setVisibility(View.INVISIBLE);
                                                       case 8:
                                                           coinImg[8].setVisibility(View.INVISIBLE);
                                                           break;
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


                coinBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isOnAnimation) {
                            coinFlipAnim(coinNum);
                        }
                    }
                });


        return mainView;
    }

    public void coinFlipAnim(int n) {
        coinImg[0].startAnimation(flipFirstAnim);
        coinHeads[0] = (int)(Math.random()*2);
        for (int i = 1; i < n; i++) {
            coinImg[i].startAnimation(flipStartAnim);
            coinHeads[i] = (int)(Math.random()*2);
        }
    }

    public void coinFlip(int n) {
        for (int i = 0; i < n; i++) {
            coinImg[i].setImageResource(coinResource[coinHeads[i]]);
            coinImg[i].startAnimation(flipEndAnim);
        }
    }
}