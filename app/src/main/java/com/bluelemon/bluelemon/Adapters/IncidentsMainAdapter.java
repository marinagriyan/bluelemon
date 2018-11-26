package com.bluelemon.bluelemon.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bluelemon.bluelemon.Fragments.AssignedIncidentsFragment;
import com.bluelemon.bluelemon.Fragments.MyIncidentsFragment;
import com.bluelemon.bluelemon.R;

public class IncidentsMainAdapter extends FragmentPagerAdapter {
    private Context context;

    public IncidentsMainAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title[] = {
                context.getResources().getString(R.string.my_incidents),
                context.getResources().getString(R.string.assigned_incidents),
        };
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new MyIncidentsFragment();
        if (position == 1)
            fragment = new AssignedIncidentsFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
