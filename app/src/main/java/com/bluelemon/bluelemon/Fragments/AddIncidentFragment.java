package com.bluelemon.bluelemon.Fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bluelemon.bluelemon.Activities.MainActivity;
import com.bluelemon.bluelemon.App;
import com.bluelemon.bluelemon.Constants;
import com.bluelemon.bluelemon.PresetRadioGroup;
import com.bluelemon.bluelemon.R;
import com.bluelemon.bluelemon.RetrofitClient;
import com.fxn.pix.Pix;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddIncidentFragment extends Fragment implements View.OnClickListener{
    public static final int ADD_IMAGE = 715;
    public static final int CHANGE_IMAGE = 291;
    private MainActivity activity;
    private View generalInformationLayout;
    private View yourDetailsLayout;
    private TextView generalInformation;
    private TextView yourDetails;

    private EditText category, department;
    private TextView date, time;
    private EditText value, location, venue;

    private View addPhotos;

    private EditText firstName, lastName;
    private Spinner isRiddorSpinner;
    private boolean isRiddor;
    private EditText immediateAction;
    private EditText address, mobile, postCode, email;
    private EditText jobTitle, occupation;

    private PresetRadioGroup radioGroup;
    private String level = "0";
    private Calendar calendar;
    private LinearLayout images;
    private LinearLayout.LayoutParams layoutParams;
    private List<File> files = new ArrayList<>();
    private int changedImage = -1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_incident, container, false);
        activity.showBack(true);

        initViews(view);
        configureViewSizes();
        calendar = Calendar.getInstance();

        view.findViewById(R.id.complete).setOnClickListener(this);
        generalInformation.setOnClickListener(this);
        yourDetails.setOnClickListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        addPhotos.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new PresetRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View radioGroup, View radioButton, boolean isChecked, int checkedId) {
                switch (checkedId){
                    case R.id.level_0:
                        level = "0";
                        break;
                    case R.id.level_1:
                        level = "1";
                        break;
                    case R.id.level_2:
                        level = "2";
                        break;
                }
            }
        });

        isRiddorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isRiddor = position == 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void initViews(View view) {
        generalInformationLayout = view.findViewById(R.id.general_information_layout);
        yourDetailsLayout = view.findViewById(R.id.your_details_layout);
        generalInformation = view.findViewById(R.id.general_information);
        yourDetails = view.findViewById(R.id.your_details);

        category = view.findViewById(R.id.category);
        department = view.findViewById(R.id.department);
        date = view.findViewById(R.id.accident_date);
        time = view.findViewById(R.id.time);
        value = view.findViewById(R.id.value);
        venue = view.findViewById(R.id.location);
        location = view.findViewById(R.id.venue);

        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.last_name);
        isRiddorSpinner = view.findViewById(R.id.is_riddor);
        immediateAction = view.findViewById(R.id.immediate_action);
        address = view.findViewById(R.id.address);
        mobile = view.findViewById(R.id.mobile);
        postCode = view.findViewById(R.id.postal_code);
        email = view.findViewById(R.id.email);
        jobTitle = view.findViewById(R.id.job_title);
        occupation = view.findViewById(R.id.occupation);

        images = view.findViewById(R.id.images);
        addPhotos = view.findViewById(R.id.add_photos);

        radioGroup =  view.findViewById(R.id.radio);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.complete:
                setData();
                break;
            case R.id.general_information:
                switchLayoutVisibility(generalInformationLayout, generalInformation);
                break;
            case R.id.your_details:
                switchLayoutVisibility(yourDetailsLayout, yourDetails);
                break;
            case R.id.accident_date:
                openCalendar();
                break;
            case R.id.time:
                openTimePicker();
                break;
            case R.id.add_photos:
                Pix.start(activity, ADD_IMAGE, 3 - files.size());
                break;
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

    private void openTimePicker(){
        new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String AM_PM ;
                if(hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                    if (hourOfDay != 12){
                        hourOfDay -= 12;
                    }
                }
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                time.setText(String.format(Locale.ENGLISH, "%02d:%02d %s", hourOfDay, minute, AM_PM));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void configureViewSizes() {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        float density = Resources.getSystem().getDisplayMetrics().density;
        int width = (int) ((size.x - (90 * density)) / 3);
        layoutParams = new LinearLayout.LayoutParams(width,(int)(0.8 * width));
        layoutParams.setMarginStart((int) (5 * density));
        layoutParams.setMarginEnd((int) (5 * density));

        addPhotos.setLayoutParams(layoutParams);
    }

    private void setData(){
        JsonObject body = new JsonObject();
        String accidentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).format(calendar.getTime());
        body.addProperty("accidentDate", accidentDate);
        // get ID from category and department names
        body.addProperty("accidentCategoryID", 1);
        body.addProperty("accidentDepartmentID", 1);
        body.addProperty("accidentVenueLocation", location.getText().toString());
        body.addProperty("reportedByFirstName", firstName.getText().toString());
        body.addProperty("reportedBySurname", lastName.getText().toString());
        body.addProperty("isRiddorIncident", isRiddor);
        body.addProperty("immediateAction", immediateAction.getText().toString());
        body.addProperty("reportedByAddress1", address.getText().toString());
        body.addProperty("reportedByMobile", mobile.getText().toString());
        body.addProperty("reportedByPostCode", postCode.getText().toString());
        body.addProperty("reportedByEmail", email.getText().toString());
        body.addProperty("reportedByJobTitle", jobTitle.getText().toString());
        body.addProperty("reportedByOccupation", occupation.getText().toString());
        body.addProperty("accidentInvestigationLevel", level);

        create(body);
    }

    private void setImages(final File image, final int position){
        final ImageView imageView = (ImageView) LayoutInflater.from(activity).inflate(R.layout.image_layout, images, false);
        imageView.setLayoutParams(layoutParams);
        Picasso.get().load(image).centerCrop().fit().into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changedImage = position;
                Pix.start(activity, CHANGE_IMAGE, 1);
            }
        });

        images.addView(imageView);
        files.add(image);

        if (files.size() < 3){
            addPhotos.setVisibility(View.VISIBLE);
        } else {
            addPhotos.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANGE_IMAGE:
                if (resultCode == RESULT_OK){
                    try {
                        File file = new File(data.getStringArrayListExtra(Pix.IMAGE_RESULTS).get(0));
                        files.set(changedImage, file);
                        ImageView imageView = (ImageView) images.getChildAt(changedImage);
                        Picasso.get().load(file).centerCrop().fit().into(imageView);
                        changedImage = -1;
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case ADD_IMAGE:
                if (resultCode == RESULT_OK){
                    try {
                        List<String> uriList = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                        for (int i = 0; i < uriList.size(); i ++){
                            setImages(new File(uriList.get(i)), files.size() + i);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void create(JsonObject body){
        // send images
        MultipartBody.Part imagesList[] = new MultipartBody.Part[3];
        for (int  i = 0; i < files.size(); i++){
            if (files.get(i) != null){
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), files.get(i));
                MultipartBody.Part part = MultipartBody.Part.createFormData("images[]", files.get(i).getName(), reqFile);
                imagesList[i] = part;
            }
        }
        Call<JsonObject> call = RetrofitClient
                .getInstance()
                .getApi()
                .createAccident(Constants.ORIGIN, App.getInstance().getPreferences().getAccessToken(), body);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                activity.onBackPressed();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void switchLayoutVisibility(View layout, TextView title){
        if (layout.getVisibility() == View.VISIBLE){
            layout.setVisibility(View.GONE);
            title.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.plus, 0);
        } else {
            layout.setVisibility(View.VISIBLE);
            title.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.minus, 0);
        }
    }
}
