package com.example.reportingsystemmobile.reportdetails;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.reportingsystemmobile.R;

import java.util.Base64;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReportDetailsFragment extends Fragment {

    private ReportDetailsService reportDetailsService;
    private View view;
    private ImageView image;
    private TextView description;
    private TextView address;
    private int reportId;

    public ReportDetailsFragment(int reportId) {
        this.reportId = reportId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reportdetails, container, false);
        image = view.findViewById(R.id.reportDetailsImage_imageView);
        description = view.findViewById(R.id.description_textView);
        address = view.findViewById(R.id.address_textView);

        reportDetailsService = new ReportDetailsService(this);
        reportDetailsService.getReportDetails(reportId);

        return view;
    }


    public void updateData(ReportDetailsResponse reportDetailsResponse) {
        description.setText(reportDetailsResponse.getDescription());
        address.setText(reportDetailsResponse.getStreet() + " " + reportDetailsResponse.getHouseNumber() + " " + reportDetailsResponse.getCity());

        byte[] img = Base64.getDecoder().decode(reportDetailsResponse.getImage().getBytes());
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        image.setImageBitmap(bitmap);
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
