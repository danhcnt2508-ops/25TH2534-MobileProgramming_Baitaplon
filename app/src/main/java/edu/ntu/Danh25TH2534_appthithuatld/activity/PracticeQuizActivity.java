package edu.ntu.Danh25TH2534_appthithuatld.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
    private GestureDetector gestureDetector;

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
        gestureDetector = new GestureDetector(this, new SwipeGestureListener());
        View.OnTouchListener touchListener = (v, event) -> gestureDetector.onTouchEvent(event);






    }

    // Lớp nội bộ định nghĩa hành vi vuốt cử chỉ cử động
    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100; // Khoảng cách tối thiểu của cú vuốt tính bằng Pixel
        private static final int SWIPE_VELOCITY_THRESHOLD = 100; // Tốc độ vuốt tối thiểu

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 == null || e2 == null) return false;

            float diffX = e2.getX() - e1.getX();
            // Nếu khoảng cách kéo từ phải qua trái đủ lớn và tốc độ đủ nhanh
            if (diffX < -SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                onSwipeLeft();
                return true;
            }
            return false;
        }

        private void onSwipeLeft() {
        }
    }

}