package com.bluelemon.bluelemon.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.MyIncidentsAdapter;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.Accidents;
import com.bluelemon.bluelemon.Models.Responses.SingleAccident;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BeginInvestigationFragment extends Fragment {
    private MainActivity activity;
    private int id;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getInt("id") != 0){
            id = bundle.getInt("id");
            getAccidentData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_begin_investigation, container, false);
        view.findViewById(R.id.complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
                activity.showMessageLayout();
            }
        });

        return view;
    }

    private void getAccidentData() {
        JsonObject body = new JsonObject();
        body.addProperty("accidentid", id);
        Call<SingleAccident> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAccident(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<SingleAccident>() {
            @Override
            public void onResponse(Call<SingleAccident> call, Response<SingleAccident> response) {
                if (response.isSuccessful()){
                    if (response.body() != null && response.body().getBody() != null){

                    }
                    else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Utils.showError(activity, response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SingleAccident> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
