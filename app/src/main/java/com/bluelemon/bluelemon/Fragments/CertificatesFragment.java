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
import com.bluelemon.bluelemon.Adapters.CertificatesAdapter;
import com.bluelemon.bluelemon.Adapters.DocumentsAdapter;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.DocumentModel;
import com.bluelemon.bluelemon.Models.Responses.DocumentBody;
import com.bluelemon.bluelemon.Models.Responses.Documents;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CertificatesFragment extends Fragment {
    private MainActivity activity;
    private DocumentsMainFragment fragment;
    private RecyclerView recyclerView;
    private List<DocumentBody> list = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        fragment = (DocumentsMainFragment) getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certificates, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        getCertificates();

        view.findViewById(R.id.add_document).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setFragment(new NewDocumentFragment());
            }
        });
        return view;
    }

    private void getCertificates() {
        JsonObject body = new JsonObject();
        body.add("sites", App.getInstance().getPreferences().getSites());
        Call<Documents> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCertificates(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<Documents>() {
            @Override
            public void onResponse(Call<Documents> call, Response<Documents> response) {
                if (response.isSuccessful()){
                    if (response.body() != null && response.body().getBody() != null){
                        list = response.body().getBody();
                        recyclerView.setAdapter(new CertificatesAdapter(activity, list));
                    }
                    else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Utils.showError(activity, response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Documents> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
