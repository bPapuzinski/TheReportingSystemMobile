package com.example.reportingsystemmobile.reportdetails;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reportingsystemmobile.R;

import java.util.List;

public class ReplayAdapter extends RecyclerView.Adapter<ReplayAdapter.ReplayHolder> {

    private List<ReplayResponse> replayResponses;

    public ReplayAdapter(List<ReplayResponse> replayResponses) {
        this.replayResponses = replayResponses;
    }

    @NonNull
    @Override
    public ReplayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_replay, parent, false);
        ReplayHolder replayHolder = new ReplayHolder(view);

        return replayHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ReplayHolder holder, int position) {
        ReplayResponse currentItem = replayResponses.get(position);

        holder.author_textView.setText(currentItem.getAuthor());
        holder.context_TextView.setText(currentItem.getContext());

    }

    @Override
    public int getItemCount() {
        return replayResponses.size();
    }

    public static class ReplayHolder extends RecyclerView.ViewHolder {
        private TextView author_textView;
        private TextView context_TextView;

        public ReplayHolder(@NonNull View itemView) {
            super(itemView);
            author_textView = itemView.findViewById(R.id.author_textView);
            context_TextView = itemView.findViewById(R.id.replayContext_textView);
        }
    }
}
