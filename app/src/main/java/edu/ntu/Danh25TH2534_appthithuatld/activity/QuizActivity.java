package edu.ntu.Danh25TH2534_appthithuatld.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.ntu.Danh25TH2534_appthithuatld.R;
import edu.ntu.Danh25TH2534_appthithuatld.database.QuizDatabase;
import edu.ntu.Danh25TH2534_appthithuatld.model.ExamHistory;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestionText;
    private RadioGroup rgOptions;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button btnNext;
    private List<Question> questionList;
    private TextView tvTimer;
    private CountDownTimer countDownTimer;
    private static final long START_TIME_IN_MILLIS = 600000; //10 phút  = 600000ms
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private QuizDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //1. Ánh xạ các thành phần trên giao diện
        tvQuestionText = findViewById(R.id.tvQuestionText);
        rgOptions = findViewById(R.id.rgOptions);
        rbA = findViewById(R.id.rbOptionA);
        rbB = findViewById(R.id.rbOptionB);
        rbC = findViewById(R.id.rbOptionC);
        rbD = findViewById(R.id.rbOptionD);
        btnNext = findViewById(R.id.btnNext);
        tvTimer = findViewById(R.id.tvTimer);

        //2. Lấy dữ liệu từ Room Database bằng luồng phụ
        db = QuizDatabase.getInstance(this);

            //đẩy lệnh đọc dữ liệu vào luồng phụ đã khai báo trong file QuizDatabase.java
            QuizDatabase.databaseWriteExecutor.execute(() -> {

                //đọc dữ liệu từ SQLite ngầm bên dưới
                questionList = db.questionDao().getRandomExamQuestions();

                //sau khi luồng phụ lấy xong dữ liệu, phải quay về luồng chính (Main Thread) để cập nhật giao diện UI
                runOnUiThread(() -> {
                    if (questionList != null && !questionList.isEmpty()) {
                        displayQuestion(currentQuestionIndex);
                        startTimer();   //Bổ sung hàm đếm ngược
                    } else {
                        Toast.makeText(this, "Ngân hàng câu hỏi trống! hây thêm câu hỏi vào trước.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            });

        //3. Xử lý sự kiện khi bấm vào nút "Câu tiếp theo"
        btnNext.setOnClickListener(v -> {
            int selectedId = rgOptions.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(QuizActivity.this, "Vui lòng chọn 1 đáp án",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            //Kiểm tra đáp án người dùng chọn
            int answerOrder = 1;        //lựa chọn mặc định A
            if (selectedId == R.id.rbOptionB) {
                answerOrder = 2;
            }
            else if (selectedId == R.id.rbOptionC) {
                answerOrder = 3;
            } else if (selectedId == R.id.rbOptionD) {
                answerOrder = 4;
            }

            //cộng điểm khi chọn đúng, chỉnh: Sử dụng hàm getter .getCorrectOption()
            if (answerOrder == questionList.get(currentQuestionIndex).getCorrectOption()) {
                score++;
            }

            //chuyển sang câu tiếp
            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                rgOptions.clearCheck();     //Xóa lựa chọn cũ
                displayQuestion(currentQuestionIndex);

            } else {
                //Kết thúc bài thi và hiển thị kết quả
                //xóa tạm trước 3.1 Toast.makeText(QuizActivity.this,"Bạn đã hoàn thành bài kiểm tra với điểm số: " + score +"/" + questionList.size(), Toast.LENGTH_LONG).show();
                //xóa tạm trước 3.1 finish(); //Quay lại màn hình trước hoăc chuyển qua màn hình kết quả
                saveExamHistory();
            }
        });
    }

    // Hàm hiển thị câu hỏi hiện tại lên giao diện
    private void displayQuestion(int index) {
        Question q = questionList.get(index);
        //Sử dụng các hàm getter công khai (.getQuestionText, .getOptionA,...) để lấy dữ liệu an toàn
        tvQuestionText.setText("Câu " + (index + 1) + ": " + q.getQuestionText());
        rbA.setText(q.getOptionA());
        rbB.setText(q.getOptionB());
        rbC.setText(q.getOptionC());
        rbD.setText(q.getOptionD());
    }

    //Hàm xử lý đồng hồ đếm ngược
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();

                //Xử lý khi hết giờ
                Toast.makeText(QuizActivity.this, "Hết giờ làm bài!", Toast.LENGTH_LONG).show();
                saveExamHistory();
            }
        }.start();
    }

    //hàm chuyển đổi ms thành định dạng phút:giây và hiển thị trên TextView tvTimer
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTimer.setText("Thời gian: " + timeFormatted);

        //thời gian dưới 1 phút, đổi chữ sang màu đỏ
        if (timeLeftInMillis < 60000) {
            tvTimer.setTextColor(Color.RED);
        }
    }

    //hàm hủy đồng hồ
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    private void saveExamHistory() {
        //3.1 lấy ngày giờ hiện tại của hệ thống để lưu vào lịch sử
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

        //3.2 tạo đối tượng ExamHistory mới với điểm số thực tế
        ExamHistory history = new ExamHistory(score, questionList.size(), currentDateAndTime);

        //3.3 dùng luồng phụ databaseWriteExecutor để lưu ngầm dữ liệu
        QuizDatabase.databaseWriteExecutor.execute(() -> {
            //ghi vào SQLite
            db.examHistoryDao().insertHistory(history);
            //trở về luồng chính để hiển thị kết quả và đóng màn hình
            runOnUiThread(() -> {
                Toast.makeText(QuizActivity.this,
                        "Kết thúc bài thi! Đã lưu kết quả: " + score + "/" + questionList.size(),
                        Toast.LENGTH_LONG).show();
                finish();  //đóng màn hình làm bài, quay về trang chính
            });
        });
    }

}
