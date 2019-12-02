package com.example.reportingsystemmobile.myreports;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reportingsystemmobile.R;
import com.example.reportingsystemmobile.menu.MainActivity;
import com.example.reportingsystemmobile.reportdetails.ReportDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class ReportListFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ReportListService reportListService;
    List<ReportListResponse> reportListResponseList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reportslist, container, false);

        reportListService = new ReportListService(this);

        reportListService.getReportList();

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        reportListResponseList = new ArrayList<>();
        adapter = new ReportListAdapter(reportListResponseList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }


    public void setReportListResponseList(List<ReportListResponse> reportListResponseList) {
        this.reportListResponseList = reportListResponseList;
        adapter = new ReportListAdapter(reportListResponseList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void changeFragmentToReportDetails(int id) {
        ((MainActivity) getActivity()).replaceFragmentWithReportDetails(id);
    }
}
