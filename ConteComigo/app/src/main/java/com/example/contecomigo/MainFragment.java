package com.example.contecomigo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v(TAG, "onCreateView");
        // Get the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // set an adapter to the ViewPager allowing to swipe between activities
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.main_view_pager);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getActivity(), getChildFragmentManager()));

        /** Find the TabLayout and connect it with the viewPager. This will:
         *  1. Update the tab layout when the view pager is swiped
         *  2. Update the view pager when a tab is selected
         *  3. Set the tab layout's tab names with the view pager's adapter's titles
         *  by calling onPageTitle()
         */
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.navigation_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }
}
