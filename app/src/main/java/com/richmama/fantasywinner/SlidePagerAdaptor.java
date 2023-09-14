package com.richmama.fantasywinner;

import android.content.Context;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SlidePagerAdaptor extends FragmentStatePagerAdapter {
    private final Context mContext;
    public SlidePagerAdaptor(@NonNull FragmentManager fm, int behavior, Context mContext) {
        super(fm, behavior);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

            switch (position){
                case 0: return new UpcomingJackpots();
                case 1: return  new LiveJackpots();
                case 2: return  new CompletedJackpots();
            }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Upcoming";
            case 1: return "Live";
            case 2: return "Completed";
            default: return null;
        }
    }
}
