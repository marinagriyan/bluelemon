package com.bluelemon.bluelemon.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.provider.DocumentFile;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.Models.Responses.EquipmentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.fxn.pix.Pix;
import com.google.android.gms.common.util.IOUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class EditEquipmentFragment extends Fragment {
    private MainActivity activity;
    private EditText refNO, make;
    private EditText model, serialNumber;
    private TextView enteredBy;
    private EditText description, template;
    private Spinner sites;
    private EditText department, frequency;
    private TextView checkDate;

    private TextView requirementsList;
    private EquipmentBody equipmentBody;
    private Calendar calendar;
    private List<String> siteData = new ArrayList<>();
    private String selectedSite;
    public static final int ADD_IMAGE = 910;
    private String file = null;
    private ImageView image;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_equipment, container, false);

        initViews(view);
        calendar = Calendar.getInstance();
        Bundle bundle = getArguments();
        if (bundle != null){
            try {
                equipmentBody = new Gson().fromJson(bundle.getString("equipment"), EquipmentBody.class);
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        if (equipmentBody != null){
            setData();
        }

        activity.showBack(true);
        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEquipment();
            }
        });
        view.findViewById(R.id.upload_images).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pix.start(activity, ADD_IMAGE, 1);
            }
        });
        checkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });
        siteData.addAll(App.getInstance().getPreferences().getSiteNames().keySet());
        sites.setAdapter(new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, siteData));
        sites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSite = App.getInstance().getPreferences().getSiteNames().get(siteData.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void initViews(View view) {
        refNO = view.findViewById(R.id.ref_no);
        make = view.findViewById(R.id.make);
        model = view.findViewById(R.id.model);
        serialNumber = view.findViewById(R.id.serial_no);
        enteredBy = view.findViewById(R.id.entered_by);
        description = view.findViewById(R.id.description);
        template = view.findViewById(R.id.template);
        sites = view.findViewById(R.id.sites);
        department = view.findViewById(R.id.department);
        frequency = view.findViewById(R.id.frequency);
        checkDate = view.findViewById(R.id.check_date);
        requirementsList = view.findViewById(R.id.requirements);
        image = view.findViewById(R.id.image);
    }

    private void setData(){
        refNO.setText(equipmentBody.getRefno());
        make.setText(equipmentBody.getMake());
        model.setText(equipmentBody.getModel());
        serialNumber.setText(equipmentBody.getSerial());
        enteredBy.setText(equipmentBody.getUserName());
        description.setText(equipmentBody.getComments());
        template.setText(equipmentBody.getCategory());
        department.setText(equipmentBody.getDepartmentName());
        frequency.setText(String.valueOf(equipmentBody.getFrequency()));
        checkDate.setText(Utils.dayFormatFromTimestamp(equipmentBody.getCheckDate()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            requirementsList.setText(Html.fromHtml(equipmentBody.getCheckRequirements(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            requirementsList.setText(Html.fromHtml(equipmentBody.getCheckRequirements()));
        }
    }

    private void openCalendar(){
        new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year, month, day);
                SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH);
                checkDate.setText(sdf.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void addNewEquipment(){
        JsonObject body = new JsonObject();
        if (file != null) {
            body.addProperty("file", file);
        }
        body.addProperty("equipmentID", equipmentBody.getEquipmentID());
        body.addProperty("equipmentInstanceReference", equipmentBody.getEquipmentInstanceReference());
        body.addProperty("comments", description.getText().toString());
        body.addProperty("inUse", equipmentBody.getInUse());
        body.addProperty("theatreID", equipmentBody.getTheatreID());
        body.addProperty("departmentID", equipmentBody.getDepartmentID());
        body.addProperty("checkRequirements", equipmentBody.getCheckRequirements());
        try {
            body.addProperty("frequency", Integer.parseInt(frequency.getText().toString()));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        body.addProperty("awaitingRepair", equipmentBody.getAwaitingRepair());
        body.addProperty("quarantined", equipmentBody.getQuarantined());
        body.addProperty("make", make.getText().toString());
        body.addProperty("model", model.getText().toString());
        body.addProperty("serial", serialNumber.getText().toString());
        body.addProperty("refno", refNO.getText().toString());
        body.addProperty("retire", equipmentBody.getRetire());
        body.addProperty("folderID", equipmentBody.getFolderID());
        body.addProperty("lastCheckDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).format(calendar.getTime()));
        body.addProperty("createdBy", equipmentBody.getCreatedBy());

        Call<JsonObject> call = RetrofitClient
                .getInstance()
                .getApi()
                .createEquipment(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 401){
                    Utils.logout(activity);
                } else if (response.isSuccessful()){
                    activity.removeFragment();
                } else {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADD_IMAGE:
                if (resultCode == RESULT_OK){
                    try {
                        List<String> uriList = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                        setFile(new File(uriList.get(0)));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }



    private void setFile(File f) {
        int size = (int) f.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(f));
            buf.read(bytes, 0, bytes.length);
            buf.close();
            file = Base64.encodeToString(bytes, Base64.DEFAULT);
            Picasso.get().load(f).centerCrop().fit().into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
