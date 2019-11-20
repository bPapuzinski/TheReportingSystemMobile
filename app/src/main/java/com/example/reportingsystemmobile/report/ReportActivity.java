package com.example.reportingsystemmobile.report;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reportingsystemmobile.R;

import java.nio.ByteBuffer;
import java.util.Base64;

public class ReportActivity extends AppCompatActivity {

    private ImageView reportImageView;
    private Button sendButton;
    private EditText descriptionEditText;
    private EditText streetEditText;
    private EditText houseNumberEditText;
    private EditText cityEditText;
    private Bitmap bitmap;
    private ReportService reportService;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        reportService = new ReportService(this);
        sendButton = findViewById(R.id.send_button);
        reportImageView = findViewById(R.id.reportImage_imageView);
        descriptionEditText = findViewById(R.id.description_editText);
        streetEditText = findViewById(R.id.street_editText);
        houseNumberEditText = findViewById(R.id.houseNumber_editText);
        cityEditText = findViewById(R.id.city_editText);
        reportImageView.setClickable(true);

        reportImageView.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        });

        sendButton.setOnClickListener(view -> {
            ReportData reportData = new ReportData();
            reportData.setDescription(descriptionEditText.getText().toString());
            reportData.setStreet(streetEditText.getText().toString());
            reportData.setHouseNumber(houseNumberEditText.getText().toString());
            reportData.setCity(cityEditText.getText().toString());

            int size = bitmap.getRowBytes() * bitmap.getHeight();
            ByteBuffer byteBuffer = ByteBuffer.allocate(size);
            bitmap.copyPixelsToBuffer(byteBuffer);
            reportData.setImage(Base64.getEncoder().encode(byteBuffer.array()));

            new Thread(() -> reportService.sendNewReport(reportData)).start();
            System.out.println(reportData.toString());
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap) data.getExtras().get("data");
        reportImageView.setImageBitmap(bitmap);
    }

    public void displayToast(String message) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }
}
