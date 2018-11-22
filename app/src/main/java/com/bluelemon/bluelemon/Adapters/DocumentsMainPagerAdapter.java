package com.bluelemon.bluelemon.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bluelemon.bluelemon.Fragments.CertificatesFragment;
import com.bluelemon.bluelemon.Fragments.DocumentsFragment;
import com.bluelemon.bluelemon.R;

public class DocumentsMainPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public DocumentsMainPagerAdapter(FragmentManager fm, Context context) {
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
            fragment = new DocumentsFragment();
        if (position == 1)
            fragment = new CertificatesFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
