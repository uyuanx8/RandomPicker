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
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RpsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RpsFragment extends Fragment {

    private int cpuResource[] = {R.drawable.rps_cpu_rock,
        R.drawable.rps_cpu_scissor,
        R.drawable.rps_cpu_paper};

    private int playerResource[] = {R.drawable.rps_player_rock,
        R.drawable.rps_player_scissor,
        R.drawable.rps_player_paper};

    boolean isOnAnimation;
    Animation playerAnim;
    Animation cpuAnim;

    ImageView playerImg;
    ImageView cpuImg;
    TextView winnerTxt;


    int winloseText[] ={R.string.rps_win,
            R.string.rps_lose,
            R.string.rps_tie};

    public RpsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static RpsFragment newInstance() {
        RpsFragment fragment = new RpsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView =  inflater.inflate(R.layout.fragment_rps, container, false);

        ImageButton rockBtn = mainView.findViewById(R.id.rps_btn_rock);
        ImageButton scissorBtn = mainView.findViewById(R.id.rps_btn_scissors);
        ImageButton paperBtn = mainView.findViewById(R.id.rps_btn_paper);

        playerImg = mainView.findViewById(R.id.rps_img_player);
        cpuImg = mainView.findViewById(R.id.rps_img_cpu);
        winnerTxt = mainView.findViewById(R.id.rps_winlose);

        isOnAnimation=false;

        playerAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.rps_player_anim);
        playerAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isOnAnimation=true;
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                isOnAnimation=false;
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        cpuAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.rps_cpu_anim);
        cpuAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) { }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOnAnimation) {
                    playGame(0);
                }
            }
        });
        scissorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOnAnimation) {
                    playGame(1);
                }
            }
        });
        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOnAnimation) {
                    playGame(2);
                }
            }
        });

        return mainView;
    }

    public void playGame(int h) {
        int cpu = (int) (Math.random() * 3);
        int player = h;
        cpuImg.setImageResource(cpuResource[cpu]);
        playerImg.setImageResource(playerResource[player]);
        if (cpu == 2 && player == 0) {
            player += 3;
        } else if (player == 2 && cpu == 0) {
            cpu += 3;
        }

        cpuImg.startAnimation(cpuAnim);
        playerImg.startAnimation(playerAnim);

        if (cpu > player) {
            winnerTxt.setText(winloseText[0]);
        } else if (cpu < player) {
            winnerTxt.setText(winloseText[1]);
        } else {
            winnerTxt.setText(winloseText[2]);
        }


    }
}