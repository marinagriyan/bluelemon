package com.bluelemon.bluelemon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private Context context;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title[] = {
                context.getResources().getString(R.string.documents),
                context.getResources().getString(R.string.certificates),
        };
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new DocumentsMainFragment();
        if (position == 1)
            fragment = new CertificatesFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
