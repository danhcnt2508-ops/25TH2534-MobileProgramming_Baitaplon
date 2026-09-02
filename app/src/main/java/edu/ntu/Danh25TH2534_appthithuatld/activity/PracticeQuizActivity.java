package edu.ntu.Danh25TH2534_appthithuatld.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.ntu.Danh25TH2534_appthithuatld.R;
import edu.ntu.Danh25TH2534_appthithuatld.database.QuizDatabase;
import edu.ntu.Danh25TH2534_appthithuatld.model.ExamHistory;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;

public class PracticeQuizActivity extends AppCompatActivity {

    private TextView tvProgress, tvQuestionText, tvSwipeHint;
    private RadioGroup rgOptions;
    private RadioButton rbA, rbB, rbC, rbD;
    private ConstraintLayout layoutRoot;
    private Button btnCheck, btnFinish;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private boolean isAnswerChecked = false; // Trạng thái đã kiểm tra đáp án chưa
    private QuizDatabase db;
    private GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
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
        View.OnTouchListener touchListener = (v, event)
                                                -> gestureDetector.onTouchEvent(event);
        //đăng ký nhận diện vuốt trên toàn vùng không gian màn hình nền
        //layoutRoot.setOnTouchListener(touchListener); tạm xóa dòng lệnh này

        //3. Tải câu hỏi ngẫu nhiên từ SQLite bằng luồng phụ
        db = QuizDatabase.getInstance(this);
        QuizDatabase.databaseWriteExecutor.execute(() -> {
            questionList = db.questionDao().getRandomExamQuestions();
            runOnUiThread(() -> {
                if (questionList != null && !questionList.isEmpty()) {
                    displayQuestion(currentQuestionIndex);
                } else {
                    Toast.makeText(this, "Ngân hàng câu hỏi trống", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        });

        //4. Xử lý sự kiện nút "Kiểm tra đáp án"
        btnCheck.setOnClickListener(v -> {
            int selectedId = rgOptions.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Vui lòng chọn 1 đáp án trước.", Toast.LENGTH_SHORT).show();
                return;
            }

            int answerOrder = 1;
            RadioButton selectedRb = findViewById(selectedId);
            if (selectedId == R.id.rbOptionB) answerOrder = 2;
            else if (selectedId == R.id.rbOptionC) answerOrder = 3;
            else if (selectedId == R.id.rbOptionD) answerOrder = 4;

            int correctOption = questionList.get(currentQuestionIndex).getCorrectOption();

            //Thực hiện chuyển đổi màu sắc văn bản
            if (answerOrder == correctOption) {
                score++;
                selectedRb.setTextColor(Color.parseColor("#2E7D32"));
            } else {
                selectedRb.setTextColor(Color.parseColor("#C62828"));
                highlightCorrectOption(correctOption);  //tô xanh đáp án đúng
            }

            setOptionsEnabled(false);  //khóa click chọn la
            isAnswerChecked = true;
            btnCheck.setEnabled(false);   //vô hiệu hóa nút kiểm tra, bắt buộc phải vuốt hoặc kết thúc
            tvSwipeHint.setVisibility(View.VISIBLE);   // Hiện chỉ dẫn vuốt

            // Nếu đây là câu hỏi cuối cùng, hiển thị ngay nút hoàn thành bài thi
            if (currentQuestionIndex == questionList.size() - 1) {
                btnFinish.setVisibility(View.VISIBLE);
                tvSwipeHint.setText("Đã hết câu hỏi, bấm hoàn thành bên dưới");
            }
        });

        //5. Xử lý sự kiện nút Hoàn thành
        btnFinish.setOnClickListener(v -> saveExamHistoryAndFinish());
    }

    private void saveExamHistoryAndFinish() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

        ExamHistory history = new ExamHistory(score, questionList.size(), currentDateAndTime);

        QuizDatabase.databaseWriteExecutor.execute(() -> {
            db.examHistoryDao().insertHistory(history);
            runOnUiThread(() -> {
                Toast.makeText(PracticeQuizActivity.this, "Đã hoàn thành! Điểm số luyện tập"
                                + score + "/" + questionList.size(), Toast.LENGTH_LONG).show();
                finish();  //trở về trang chủ
            });
        });
    }

    private void setOptionsEnabled(boolean enabled) {
        rbA.setEnabled(enabled);
        rbB.setEnabled(enabled);
        rbC.setEnabled(enabled);
        rbD.setEnabled(enabled);
    }

    private void highlightCorrectOption(int correctOption) {
        switch (correctOption) {
            case 1: rbA.setTextColor(Color.parseColor("#2E7D32")); break;
            case 2: rbB.setTextColor(Color.parseColor("#2E7D32")); break;
            case 3: rbC.setTextColor(Color.parseColor("#2E7D32")); break;
            case 4: rbD.setTextColor(Color.parseColor("#2E7D32")); break;
        }
    }

    private void displayQuestion(int index) {
       Question q = questionList.get(index);
       tvProgress.setText("Tiến độ: " + (index + 1) + "/" + questionList.size());
       tvQuestionText.setText("Câu " + (index + 1) + ": " + q.getQuestionText());
       rbA.setText(q.getOptionA());
       rbB.setText(q.getOptionB());
       rbC.setText(q.getOptionC());
       rbD.setText(q.getOptionD());
    }

    // Lớp nội bộ định nghĩa hành vi vuốt cử chỉ cử động
    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100; // Khoảng cách tối thiểu của cú vuốt tính bằng Pixel
        private static final int SWIPE_VELOCITY_THRESHOLD = 100; // Tốc độ vuốt tối thiểu

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 == null || e2 == null) return false;

            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();
            // Nếu khoảng cách kéo từ phải qua trái đủ lớn và tốc độ đủ nhanh

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (diffX < -SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    onSwipeLeft();
                    return true;
                }
            }
            return false;
        }
    }
        // Xử lý khi phát hiện hành động vuốt sang trái thành công
        private void onSwipeLeft() {
            if (!isAnswerChecked) {
                Toast.makeText(this, "Bạn phải bấm nút kiểm tra đáp án trước khi qua câu mới", Toast.LENGTH_SHORT).show();
                return;
            }

            if (currentQuestionIndex < questionList.size() - 1) {
                currentQuestionIndex++;
                isAnswerChecked = false;
                btnCheck.setEnabled(true);
                tvSwipeHint.setVisibility(View.INVISIBLE);
                rgOptions.clearCheck();
                setOptionsEnabled(true);
                resetButtonColor();
                displayQuestion(currentQuestionIndex);
            }
        }

    private void resetButtonColor() {
        int defaultColor = Color.BLACK;
        rbA.setTextColor(defaultColor);
        rbB.setTextColor(defaultColor);
        rbC.setTextColor(defaultColor);
        rbD.setTextColor(defaultColor);
    }
    //bổ sung hàm:
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}