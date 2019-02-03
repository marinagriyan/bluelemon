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
import com.bluelemon.bluelemon.Adapters.DocumentsAdapter;
import com.bluelemon.bluelemon.Adapters.RisksAdapter;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.DocumentModel;
import com.bluelemon.bluelemon.Models.Responses.Documents;
import com.bluelemon.bluelemon.Models.Responses.RiskBody;
import com.bluelemon.bluelemon.Models.Responses.Risks;
import com.bluelemon.bluelemon.Models.RiskModel;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RisksFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private List<RiskBody> list = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_risks, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        getRisks();

        return view;
    }

    private void getRisks() {
        JsonObject body = new JsonObject();
        body.add("sites", App.getInstance().getPreferences().getSites());
        Call<Risks> call = RetrofitClient
                .getInstance()
                .getApi()
                .getRisks(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<Risks>() {
            @Override
            public void onResponse(Call<Risks> call, Response<Risks> response) {
                if (response.code() == 401){
                    Utils.logout(activity);
                } else if (response.isSuccessful()){
                    if (response.body() != null && response.body().getBody() != null){
                        list = response.body().getBody();
                        recyclerView.setAdapter(new RisksAdapter(activity, list));
                    }
                    else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Utils.showError(activity, response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Risks> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
