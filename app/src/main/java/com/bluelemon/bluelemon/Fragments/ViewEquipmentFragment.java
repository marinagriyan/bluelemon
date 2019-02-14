package com.bluelemon.bluelemon.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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
import com.bluelemon.bluelemon.Models.Responses.EquipmentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewEquipmentFragment extends Fragment {
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
        view.findViewById(R.id.done).setVisibility(View.GONE);
        view.findViewById(R.id.upload_images).setVisibility(View.GONE);

        checkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });
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
        return view;
    }

    private void initViews(View view) {
        refNO = view.findViewById(R.id.ref_no);
        refNO.setEnabled(false);
        make = view.findViewById(R.id.make);
        make.setEnabled(false);
        model = view.findViewById(R.id.model);
        model.setEnabled(false);
        serialNumber = view.findViewById(R.id.serial_no);
        serialNumber.setEnabled(false);
        description = view.findViewById(R.id.description);
        description.setEnabled(false);
        template = view.findViewById(R.id.template);
        template.setEnabled(false);
        sites = view.findViewById(R.id.sites);
        sites.setEnabled(false);
        department = view.findViewById(R.id.department);
        department.setEnabled(false);
        frequency = view.findViewById(R.id.frequency);
        frequency.setEnabled(false);

        enteredBy = view.findViewById(R.id.entered_by);
        checkDate = view.findViewById(R.id.check_date);

        requirementsList = view.findViewById(R.id.requirements);
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
        body.addProperty("file", "");
        body.addProperty("equipmentID", "1589169d-00d1-4e4a-a46a-a247d082e6e0");
        body.addProperty("equipmentInstanceReference", "");
        body.addProperty("comments", description.getText().toString());
        body.addProperty("inUse", false);
        body.addProperty("theatreID", "6620be9d-b8af-4149-bc59-095c9f4fe8c3");
        body.addProperty("departmentID", "e1a7551d-7879-4dff-997d-2fe3422aa94e");
        body.addProperty("checkRequirements", "");
        try {
            body.addProperty("frequency", Integer.parseInt(frequency.getText().toString()));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        body.addProperty("awaitingRepair", false);
        body.addProperty("quarantined", false);
        body.addProperty("make", make.getText().toString());
        body.addProperty("model", model.getText().toString());
        body.addProperty("serial", serialNumber.getText().toString());
        body.addProperty("refno", refNO.getText().toString());
        body.addProperty("retire", false);
        body.addProperty("folderID", 5);
        body.addProperty("lastCheckDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).format(calendar.getTime()));
        body.addProperty("createdBy", "6FCBB8D6-8C19-4718-8E02-30F62181D819");

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
                    activity.onBackPressed();
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
