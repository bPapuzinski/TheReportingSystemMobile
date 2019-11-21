package com.example.reportingsystemmobile.myreports;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reportingsystemmobile.R;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportListHolder> {

    private List<ReportListResponse> reportListResponses;

    public static class ReportListHolder extends RecyclerView.ViewHolder {
        private ImageView reportImage_ImageView;
        private TextView reportStreet_TextView;
        private TextView reportHouse_TextView;
        private TextView reportCity_TextView;

        public ReportListHolder(@NonNull View itemView) {
            super(itemView);
            reportImage_ImageView = itemView.findViewById(R.id.reportImage_imageView);
            reportStreet_TextView = itemView.findViewById(R.id.reportStreet_textView);
            reportHouse_TextView = itemView.findViewById(R.id.reportHouse_textView);
            reportCity_TextView = itemView.findViewById(R.id.reportCity_textView);
        }
    }

    public ReportListAdapter(List<ReportListResponse> reportListResponses){
        this.reportListResponses = reportListResponses;
    }

    @NonNull
    @Override
    public ReportListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_report,parent,false);
        ReportListHolder reportListHolder = new ReportListHolder(view);

        return reportListHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ReportListHolder holder, int position) {
        ReportListResponse currentItem = reportListResponses.get(position);

        byte[] img = Base64.getDecoder().decode(currentItem.getImage().getBytes());
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        holder.reportImage_ImageView.setImageBitmap(bitmap);
        holder.reportStreet_TextView.setText(currentItem.getStreet());
        holder.reportHouse_TextView.setText(currentItem.getHouseNumber());
        holder.reportCity_TextView.setText(currentItem.getCity());
    }

    @Override
    public int getItemCount() {
        return reportListResponses.size();
    }
}
