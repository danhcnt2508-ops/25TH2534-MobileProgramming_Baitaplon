package edu.ntu.Danh25TH2534_appthithuatld.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.ntu.Danh25TH2534_appthithuatld.R;

public class PracticeQuizActivity extends AppCompatActivity {

    private TextView tvProgress, tvQuestionText, tvSwipeHint;
    private RadioGroup rgOptions;
    private RadioButton rbA, rbB, rbC, rbD;
    private ConstraintLayout layoutRoot;
    private Button btnCheck, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practice_quiz);

        //Mở nút Back Home chuẩn trên toolbar
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Luyện tập theo câu hỏi");
        }
        
        //1.Ánh xạ các thành phần textview, button
        layoutRoot = findViewById(R.id.layoutPracticeRoot);
        tvProgress = findViewById(R.id.tvProgress);
        tvQuestionText = findViewById(R.id.tvQuestionText);
        rgOptions = findViewById(R.id.rgOptions);
        rbA = findViewById(R.id.rbOptionA);
        rbB = findViewById(R.id.rbOptionB);
        rbC = findViewById(R.id.rbOptionC);
        rbD = findViewById(R.id.rbOptionD);
        tvSwipeHint = findViewById(R.id.tvSwipeHint);
        btnCheck = findViewById(R.id.btnCheck);
        btnFinish = findViewById(R.id.btnFinish);

        //2. Khởi tạo bộ lắng nghe cử chỉ vuốt màn hình







    }
}