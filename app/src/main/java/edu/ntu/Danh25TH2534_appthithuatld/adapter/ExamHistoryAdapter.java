package edu.ntu.Danh25TH2534_appthithuatld.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ntu.Danh25TH2534_appthithuatld.R;
import edu.ntu.Danh25TH2534_appthithuatld.model.ExamHistory;

public class ExamHistoryAdapter extends RecyclerView.Adapter<ExamHistoryAdapter.HistoryViewHolder> {
    private final List<ExamHistory> historyList;
    public ExamHistoryAdapter(List<ExamHistory> historyList) {
        this.historyList = historyList;
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_history,parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        ExamHistory history = historyList.get(position);
        holder.tvScore.setText("Điểm số: " + history.getScore() + "/" + history.getTotalQuestions());
        holder.tvDate.setText("Ngày thi; " + history.getDateTaken());
    }

    @Override
    public int getItemCount() {
        return historyList != null ? historyList.size() : 0;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvScore, tvDate;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScore = itemView.findViewById(R.id.tvHistoryScore);
            tvDate = itemView.findViewById(R.id.tvHistoryDate);

        }
    }
}
