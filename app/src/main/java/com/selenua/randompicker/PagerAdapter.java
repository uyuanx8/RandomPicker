package com.selenua.randompicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    public int count;
    public PagerAdapter(FragmentActivity fa, int count){
        super(fa);
        this.count = count;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch(position){
            case 0:
                return new NumberFragment();
            case 1:
                return new DiceFragment();
            case 2:
                return new CoinFragment();
            default:
                return new PickerFragment();
        }
    }
    @Override
    public int getItemCount(){
        return 4;
    }
}
