package com.bluelemon.bluelemon.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bluelemon.bluelemon.Fragments.CertificatesFragment;
import com.bluelemon.bluelemon.Fragments.DocumentsFragment;
import com.bluelemon.bluelemon.Fragments.ExpiredFragment;
import com.bluelemon.bluelemon.R;

public class TrainingsPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public TrainingsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title[] = {
                context.getResources().getString(R.string.expiring_soon),
                context.getResources().getString(R.string.expired),
        };
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new ExpiredFragment();
        if (position == 1)
            fragment = new ExpiredFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
