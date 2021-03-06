package com.example.reportingsystemmobile.report;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.*;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import com.example.reportingsystemmobile.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

public class ReportFragment extends Fragment {

    double latitude;
    double longitude;
    private View view;
    private ImageView reportImageView;
    private Button sendButton;
    private EditText descriptionEditText;
    private EditText streetEditText;
    private EditText houseNumberEditText;
    private EditText cityEditText;
    private Bitmap bitmap;
    private ReportService reportService;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_report, container, false);

        setupElements();

        try {
            GPSTracker gps = new GPSTracker(getContext());
            if (gps.canGetLocation() && gps.isGPSEnabled) {

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getContext(), Locale.getDefault());
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                addresses = geocoder.getFromLocation(latitude, longitude, 1);

                cityEditText.setText(addresses.get(0).getLocality());
                streetEditText.setText(addresses.get(0).getThoroughfare());
                houseNumberEditText.setText(addresses.get(0).getFeatureName());

            } else {
                gps.showSettingsAlert();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        reportImageView.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.getAction();
            startActivityForResult(intent, 0);
        });

        sendButton.setOnClickListener(view -> {
            if (descriptionEditText.getText().toString().equals("") ||
                    streetEditText.getText().toString().equals("") ||
                    houseNumberEditText.getText().toString().equals("") ||
                    cityEditText.getText().toString().equals("") ||
                    bitmap == null){
                displayToast("All fields must have values");

            } else {
                ReportData reportData = new ReportData();
                reportData.setCoordinate(longitude + ";" + latitude);
                reportData.setDescription(descriptionEditText.getText().toString());
                reportData.setStreet(streetEditText.getText().toString());
                reportData.setHouseNumber(houseNumberEditText.getText().toString());
                reportData.setCity(cityEditText.getText().toString());

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                reportData.setImage(Base64.getEncoder().encodeToString(out.toByteArray()));

                new Thread(() -> reportService.sendNewReport(reportData)).start();
                System.out.println(reportData.toString());
            }
        });
        return view;
    }

    public void setupElements() {
        reportService = new ReportService(this);
        sendButton = view.findViewById(R.id.send_button);
        reportImageView = view.findViewById(R.id.reportImage_imageView);
        descriptionEditText = view.findViewById(R.id.description_editText);
        streetEditText = view.findViewById(R.id.street_editText);
        houseNumberEditText = view.findViewById(R.id.houseNumber_editText);
        cityEditText = view.findViewById(R.id.city_editText);
        reportImageView.setClickable(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getExtras() != null) {
            bitmap = (Bitmap) data.getExtras().get("data");
            reportImageView.setImageBitmap(bitmap);
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
