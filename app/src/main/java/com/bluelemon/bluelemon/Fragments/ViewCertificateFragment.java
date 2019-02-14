package com.bluelemon.bluelemon.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.provider.DocumentFile;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.google.android.gms.common.util.IOUtils;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ViewCertificateFragment extends Fragment implements View.OnClickListener{
    private static final int REQUEST_CODE_ATTACH = 908;
    private MainActivity activity;
    private int id;
    private EditText title, category;
    private Spinner sites;
    private TextView date;
    private View upload;
    private TextView fileName;
    private List<String> siteData = new ArrayList<>();
    private String selectedSite;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getInt("id") != 0){
            id = bundle.getInt("id");
            getCertificate();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_document, container, false);
        initViews(view);

        siteData.addAll(App.getInstance().getPreferences().getSites().keySet());
        sites.setAdapter(new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, siteData));
        sites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSite = App.getInstance().getPreferences().getSites().get(siteData.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        view.findViewById(R.id.close).setOnClickListener(this);
        view.findViewById(R.id.add).setVisibility(View.GONE);
        upload.setVisibility(View.GONE);
        return view;
    }

    private void initViews(View view) {
        TextView certificate = view.findViewById(R.id.certificate);
        certificate.setBackground(getResources().getDrawable(R.drawable.button_blue));
        certificate.setTextColor(Color.WHITE);
        title = view.findViewById(R.id.title);
        title.setEnabled(false);
        sites = view.findViewById(R.id.sites);
        sites.setEnabled(false);
        category = view.findViewById(R.id.category);
        category.setEnabled(false);
        date = view.findViewById(R.id.date);
        upload = view.findViewById(R.id.upload);
        fileName = view.findViewById(R.id.file_name);
    }

    private void getCertificate(){
        JsonObject body = new JsonObject();
        body.addProperty("documentID", id);
        Call<SingleDocument> call = RetrofitClient
                .getInstance()
                .getApi()
                .getSingleCertificate(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
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
        //sites.setText(body.getSite());
        category.setText(body.getCategoryName());
        if (body.getSyncDateTime() != null){
            date.setText(Utils.dayFormatFromTimestamp(body.getSyncDateTime()));
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
