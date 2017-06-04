package com.example.contecomigo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // next two methods need to be overridden
    @Override
    public Fragment getItem(int position) {
        // Create a new fragment according to the position of the ViewPager
        if (position == 0) {
            return new BlogFragment();
        } else if (position == 1) {
            return new MapFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // return the number of activities in the ViewPager
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Blog";
        } else {
            return "Mapa";
        }
    }
}