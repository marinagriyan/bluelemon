package com.bluelemon.bluelemon.Fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.LoginActivity;
import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Adapters.MyIncidentsAdapter;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.AccidentBody;
import com.bluelemon.bluelemon.Models.IncidentsModel;
import com.bluelemon.bluelemon.Models.SingleIncident;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyIncidentsFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_my_incidents, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        getAccidents();

        view.findViewById(R.id.report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new AddIncidentFragment());
            }
        });
        return view;
    }

    private void getAccidents(){
        Call<JsonObject> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAccidentsList(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        JsonArray body = response.body().getAsJsonArray("Body");
                        Type listType = new TypeToken<List<AccidentBody>>() {}.getType();
                        list = new Gson().fromJson(body, listType);
                        recyclerView.setAdapter(new MyIncidentsAdapter(activity, list));
                    }
                    else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Utils.showError(activity, response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
