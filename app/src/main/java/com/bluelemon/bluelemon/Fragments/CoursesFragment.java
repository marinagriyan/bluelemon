package com.bluelemon.bluelemon.Fragments;


import android.app.Activity;
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
import com.bluelemon.bluelemon.Adapters.CourseAdapter;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.CourseBody;
import com.bluelemon.bluelemon.Models.Responses.Courses;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesFragment extends Fragment {
    private MainActivity activity;
    private RecyclerView recyclerView;
    private List<CourseBody> list;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        getCourses();

        return view;
    }

    private void getCourses() {
        JsonObject body = new JsonObject();
        body.addProperty("staffID", 295);
        Call<Courses> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCourses(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                if (response.code() == 401){
                    Utils.logout(activity);
                } else if (response.isSuccessful()){
                    if (response.body() != null && response.body().getBody() != null){
                        list = response.body().getBody();
                        recyclerView.setAdapter(new CourseAdapter(activity, list));
                    }
                    else {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Courses> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}
