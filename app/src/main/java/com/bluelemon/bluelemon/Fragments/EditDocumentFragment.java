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

public class EditDocumentFragment extends Fragment implements View.OnClickListener{
    private static final int REQUEST_CODE_ATTACH = 842;
    private MainActivity activity;
    private int id;
    private EditText title, category;
    private Spinner sites;
    private TextView date;
    private View upload;
    private TextView fileName;
    private Calendar calendar;
    private List<String> siteData = new ArrayList<>();
    private String selectedSite;
    private String file;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_document, container, false);
        initViews(view);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getInt("id") != 0){
            id = bundle.getInt("id");
            getDocument();
        }
        calendar = Calendar.getInstance();
        date.setOnClickListener(this);
        upload.setOnClickListener(this);

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
        upload = view.findViewById(R.id.upload);
        fileName = view.findViewById(R.id.file_name);
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
//        sites.setSelection();
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
        body.addProperty("siteID", selectedSite);
        body.addProperty("live", true);
        body.addProperty("currentDocumentID", 81);
        body.addProperty("fileName", fileName.getText().toString());
        // add file as byte[]
        body.addProperty("file", file);
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
                        activity.onBackPressed();
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
            case R.id.upload:
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("*/*");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(i, REQUEST_CODE_ATTACH);
                break;
            case R.id.add:
                addDocument();
                break;
            case R.id.close:
                activity.onBackPressed();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ATTACH && resultCode == RESULT_OK){
            setFile(data.getData());
        }
    }

    private void setFile(Uri data) {
        DocumentFile documentFile = DocumentFile.fromSingleUri(activity, data);
        if (documentFile != null && documentFile.getName() != null){
            fileName.setText(documentFile.getName());
        }
        InputStream inputStream = null;
        try {
            inputStream = activity.getContentResolver().openInputStream(data);
            byte[] fileContent = IOUtils.toByteArray(inputStream);
            file = Base64.encodeToString(fileContent, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
