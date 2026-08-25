package edu.ntu.Danh25TH2534_appthithuatld.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ntu.Danh25TH2534_appthithuatld.R;
import edu.ntu.Danh25TH2534_appthithuatld.adapter.ExamHistoryAdapter;
import edu.ntu.Danh25TH2534_appthithuatld.database.QuizDatabase;
import edu.ntu.Danh25TH2534_appthithuatld.model.ExamHistory;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rvHistory;
    private ExamHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));

        //gọi cơ sở dữ liệu ngầm bằng luồng phụ databaseWriteExecutỏ
        QuizDatabase db = QuizDatabase.getInstance(this);
        QuizDatabase.databaseWriteExecutor.execute(() -> {
            //lấy toa bộ lịch sử thi
            List<ExamHistory> historyList = db.examHistoryDao().getAllHistory();

            //quay lại luồng giao diện chính để hiển thị dữ liệu lên RecycleView
            runOnUiThread(() -> {
                if (historyList != null && historyList.isEmpty()) {
                    adapter = new ExamHistoryAdapter(historyList);
                    rvHistory.setAdapter(adapter);
                } else {
                    Toast.makeText(HistoryActivity.this, "Bạn chưa thực hiện bài thi thử nào.", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
