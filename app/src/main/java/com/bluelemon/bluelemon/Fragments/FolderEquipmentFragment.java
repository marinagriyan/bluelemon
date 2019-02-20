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
import com.bluelemon.bluelemon.Adapters.EquipmentsAdapter;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.EquipmentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FolderEquipmentFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private View move;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder_equipment, container, false);

        activity.showBack(true);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        move = view.findViewById(R.id.move);
        final Bundle bundle = getArguments();

        view.findViewById(R.id.add_equipment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewEquipmentFragment fragment = new NewEquipmentFragment();
                fragment.setArguments(bundle);
                activity.addFragment(fragment, 33);
            }
        });

        getEquipment();

        return view;
    }

    private void getEquipment(){
        Call<List<EquipmentBody>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getEquipment(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken());
        call.enqueue(new Callback<List<EquipmentBody>>() {
            @Override
            public void onResponse(Call<List<EquipmentBody>> call, Response<List<EquipmentBody>> response) {
                if (response.code() == 401){
                    Utils.logout(activity);
                } else if (response.isSuccessful()){
                    if (response.body() != null){
                        recyclerView.setAdapter(new EquipmentsAdapter(activity,FolderEquipmentFragment.this, response.body()));
                    }
                    else {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<EquipmentBody>> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void move(){
        move.setVisibility(View.VISIBLE);
    }
}
