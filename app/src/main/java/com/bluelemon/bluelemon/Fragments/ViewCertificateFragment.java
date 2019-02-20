package com.bluelemon.bluelemon.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Models.Responses.DocumentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.Gson;

public class ViewCertificateFragment extends Fragment implements View.OnClickListener{
    private MainActivity activity;
    private TextView title, category;
    private TextView sites;
    private TextView date;
    private DocumentBody documentBody;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_document, container, false);
        initViews(view);
        Bundle bundle = getArguments();
        if (bundle != null){
            try {
                documentBody = new Gson().fromJson(bundle.getString("body"), DocumentBody.class);
                setData();
            } catch (NullPointerException e){
                e.printStackTrace();
                activity.removeFragment();
            }
        }

        view.findViewById(R.id.close).setOnClickListener(this);
        return view;
    }

    private void initViews(View view) {
        TextView certificate = view.findViewById(R.id.certificate);
        certificate.setBackground(getResources().getDrawable(R.drawable.button_blue));
        certificate.setTextColor(Color.WHITE);
        title = view.findViewById(R.id.title);
        sites = view.findViewById(R.id.sites);
        category = view.findViewById(R.id.category);
        date = view.findViewById(R.id.date);
    }

    private void setData(){
        title.setText(documentBody.getDocumentName());
        if (App.getInstance().getPreferences().getSites().containsKey(documentBody.getSiteID())) {
            sites.setText(App.getInstance().getPreferences().getSites().get(documentBody.getSiteID()));
        }
        category.setText(documentBody.getCategoryName());
        if (documentBody.getDocumentDate() != null){
            date.setText(Utils.dayFormatFromTimestamp(documentBody.getDocumentDate()));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                activity.onBackPressed();
                break;
        }
    }
}
