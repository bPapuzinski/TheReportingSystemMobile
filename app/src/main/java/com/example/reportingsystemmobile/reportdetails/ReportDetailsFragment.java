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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reportingsystemmobile.R;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReportDetailsFragment extends Fragment {

    private ReportDetailsService reportDetailsService;
    private View view;
    private ImageView image;
    private TextView description;
    private TextView address;
    private int reportId;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ReplayResponse> replayResponseList;

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

        recyclerView = view.findViewById(R.id.replyRecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        replayResponseList = new ArrayList<>();
        adapter = new ReplayAdapter(replayResponseList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }


    public void updateData(ReportDetailsResponse reportDetailsResponse) {
        description.setText(reportDetailsResponse.getDescription());
        address.setText(reportDetailsResponse.getStreet() + " " + reportDetailsResponse.getHouseNumber() + " " + reportDetailsResponse.getCity());

        byte[] img = Base64.getDecoder().decode(reportDetailsResponse.getImage().getBytes());
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        image.setImageBitmap(bitmap);

        this.replayResponseList = reportDetailsResponse.getReplayList();
        adapter = new ReplayAdapter(replayResponseList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
