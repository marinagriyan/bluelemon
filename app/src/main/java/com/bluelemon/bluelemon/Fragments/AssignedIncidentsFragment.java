package com.bluelemon.bluelemon.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.AssignedIncidentsAdapter;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.AccidentBody;
import com.bluelemon.bluelemon.Models.Responses.Accidents;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignedIncidentsFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private List<AccidentBody> list = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assigned_incidents, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        getAccidents();

        return view;
    }

    private void getAccidents(){
        JsonObject body = new JsonObject();
        body.add("site", null);
        Call<Accidents> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAccidentsList(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<Accidents>() {
            @Override
            public void onResponse(Call<Accidents> call, Response<Accidents> response) {
                if (response.code() == 401){
                    Utils.logout(activity);
                } else if (response.isSuccessful()){
                    if (response.body() != null && response.body().getBody() != null){
                        list = response.body().getBody();
                        recyclerView.setAdapter(new AssignedIncidentsAdapter(activity, list));
                    }
                    else {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Accidents> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}
