package edu.ntu.Danh25TH2534_appthithuatld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.ntu.Danh25TH2534_appthithuatld.activity.HistoryActivity;
import edu.ntu.Danh25TH2534_appthithuatld.activity.PracticeQuizActivity;
import edu.ntu.Danh25TH2534_appthithuatld.activity.QuizActivity;
import edu.ntu.Danh25TH2534_appthithuatld.database.QuizDatabase;

public class MainActivity extends AppCompatActivity {

    private Button btnStartQuiz;
    private Button btnHistory;
    private Button btnPracticeQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //1.Ánh xạ các nút bấm từ giao diện XML
        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnHistory = findViewById(R.id.btnHistory);
        btnPracticeQuiz = findViewById(R.id.btnPracticeQuiz);

        //2.Kích hoạt khởi tạo Database ngay khi mở App lần đầu

        QuizDatabase.getInstance(this);

        //3. Xử lý sự kiện bấm nút "Bắt đầu thi thử"
        btnStartQuiz.setOnClickListener(v -> {
            //Lệnh Intent dùng để chuyển từ MainActivity sang QuizActivity
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        //4. Xử lý sư kiện bấm nút "Xem Lịch sử thi"
        //chuyển sang màn hình xem lịch sử thi
        btnHistory.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Tính năng đang được cập nhật", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        //5. Xử lý sự kiến bấm nút "Luyện tập"
        btnPracticeQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PracticeQuizActivity.class);
            startActivity(intent);
        });


    }
}