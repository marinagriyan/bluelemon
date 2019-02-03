package com.bluelemon.bluelemon.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.SingleDocument;
import com.bluelemon.bluelemon.Models.Responses.SingleDocumentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDocumentFragment extends Fragment implements View.OnClickListener{
    private MainActivity activity;
    private int id;
    private EditText title, sites, category;
    private TextView date;
    private Calendar calendar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getInt("id") != 0){
            id = bundle.getInt("id");
            getDocument();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_document, container, false);
        initViews(view);
        calendar = Calendar.getInstance();
        date.setOnClickListener(this);
        view.findViewById(R.id.close).setOnClickListener(this);
        view.findViewById(R.id.add).setOnClickListener(this);
        return view;
    }

    private void initViews(View view) {
        TextView document = view.findViewById(R.id.document);
        document.setBackground(getResources().getDrawable(R.drawable.button_blue));
        document.setTextColor(Color.WHITE);
        title = view.findViewById(R.id.title);
        sites = view.findViewById(R.id.sites);
        category = view.findViewById(R.id.category);
        date = view.findViewById(R.id.date);
    }

    private void getDocument(){
        JsonObject body = new JsonObject();
        body.addProperty("documentID", id);
        Call<SingleDocument> call = RetrofitClient
                .getInstance()
                .getApi()
                .getSingleDocument(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<SingleDocument>() {
            @Override
            public void onResponse(Call<SingleDocument> call, Response<SingleDocument> response) {
                if (response.code() == 401){
                    Utils.logout(activity);
                } else if (response.isSuccessful()){
                    if (response.body() != null && response.body().getBody() != null){
                        setData(response.body().getBody());
                    }
                    else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Utils.showError(activity, response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SingleDocument> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setData(SingleDocumentBody body){
        title.setText(body.getDocumentName());
        sites.setText(body.getSite());
        category.setText(body.getCategoryName());
        if (body.getSyncDateTime() != null){
            date.setText(Utils.dayFormatFromTimestamp(body.getSyncDateTime()));
        }
    }

    private void openCalendar(){
        new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year, month, day);
                SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH);
                date.setText(sdf.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void addDocument(){
        JsonObject body = new JsonObject();
        body.addProperty("comments", "test");
        body.addProperty("documentCategory", 43);
        body.addProperty("documentID", (Number) null);
        body.addProperty("documentName", title.getText().toString());
        body.addProperty("issueDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).format(calendar.getTime()));
        body.addProperty("renewalFrequency", 365);
        body.addProperty("siteID", "edafb0c8-83c5-43ba-9d8d-11bfd03bc53f");
        body.addProperty("live", true);
        body.addProperty("currentDocumentID", 81);
        body.addProperty("fileName", "");
        // add file as byte[]
        body.addProperty("file", "");
        Call<SingleDocument> call = RetrofitClient
                .getInstance()
                .getApi()
                .createDocument(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<SingleDocument>() {
            @Override
            public void onResponse(Call<SingleDocument> call, Response<SingleDocument> response) {
                if (response.code() == 401){
                    Utils.logout(activity);
                } else if (response.isSuccessful()){
                    if (response.body() != null && response.body().getBody() != null){

                    } else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Utils.showError(activity, response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SingleDocument> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.date:
                openCalendar();
                break;
            case R.id.add:
                addDocument();
            case R.id.close:
                activity.setFragment(new DocumentsMainFragment());
                break;
        }
    }
}
