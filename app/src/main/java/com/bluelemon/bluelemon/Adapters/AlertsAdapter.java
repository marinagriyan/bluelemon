package com.bluelemon.bluelemon.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bluelemon.bluelemon.Fragments.CertificatesFragment;
import com.bluelemon.bluelemon.Fragments.DocumentsFragment;
import com.bluelemon.bluelemon.Fragments.MyAlertsFragment;
import com.bluelemon.bluelemon.Fragments.ReceivedAlertsFragment;
import com.bluelemon.bluelemon.R;

public class AlertsAdapter extends FragmentPagerAdapter {
    private Context context;

    public AlertsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title[] = {
                context.getResources().getString(R.string.my_alerts),
                context.getResources().getString(R.string.received_alerts),
        };
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new MyAlertsFragment();
        if (position == 1)
            fragment = new ReceivedAlertsFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
