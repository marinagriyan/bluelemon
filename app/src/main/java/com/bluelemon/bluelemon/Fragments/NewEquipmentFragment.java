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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.Models.Responses.EquipmentBody;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.Utils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.AbstractSequentialList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEquipmentFragment extends Fragment {
    private MainActivity activity;
    private EditText refNO, make;
    private EditText model, serialNumber;
    private TextView enteredBy;
    private EditText description, template;
    private EditText sites, department, frequency;
    private TextView checkDate;

    private TextView requirementsList;
    private EquipmentBody equipmentBody;
    private Calendar calendar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        Bundle bundle = getArguments();
        if (bundle != null){
            try {
                equipmentBody = new Gson().fromJson(bundle.getString("equipment"), EquipmentBody.class);
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_equipment, container, false);

        initViews(view);
        calendar = Calendar.getInstance();
        if (equipmentBody != null){
            setData();
        }
        activity.showBack(true);
        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        checkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
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
    }

    private void setData(){
        refNO.setText(equipmentBody.getRefno());
        make.setText(equipmentBody.getMake());
        model.setText(equipmentBody.getModel());
        serialNumber.setText(equipmentBody.getSerial());
        enteredBy.setText(equipmentBody.getUserName());
        description.setText(equipmentBody.getComments());
        template.setText(equipmentBody.getCategory());
        // no sites ?
        sites.setText(null);
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
}
