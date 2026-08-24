package edu.ntu.Danh25TH2534_appthithuatld.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import edu.ntu.Danh25TH2534_appthithuatld.R;
import edu.ntu.Danh25TH2534_appthithuatld.database.QuizDatabase;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestionText;
    private RadioGroup rgOptions;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button btnNext;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

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

        //2. Lấy dữ liệu từ Room Database (vd lấy ds thi thử ngẫn nhiên)
        QuizDatabase db = QuizDatabase.getInstance(this);
        questionList = db.questionDao().getRandomExamQuestions();

        if (questionList != null && !questionList.isEmpty()) {
            displayQuestion(currentQuestionIndex);
        } else {
            Toast.makeText(this, "Ngân hàng câu hỏi trống! hây thêm câu hỏi vào trước.", Toast.LENGTH_LONG).show();
            finish();
        }

        //3. Xử lý sự kiện khi bấm vào nút "Câu tiếp theo"
        btnNext.setOnClickListener(v -> {
            int selectedId = rgOptions.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(QuizActivity.this, "Vui lòng chọn 1 đáp án", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(QuizActivity.this,"Bạn đã hoàn thành bài kiểm tra với điểm số: " + score +"/" + questionList.size(), Toast.LENGTH_LONG).show();
                finish(); //Quay lại màn hình trước hoăc chuyển qua màn hình kết quả
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
}
