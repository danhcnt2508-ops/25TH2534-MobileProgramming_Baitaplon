package edu.ntu.Danh25TH2534_appthithuatld.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.ntu.Danh25TH2534_appthithuatld.R;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestionText;
    private RadioGroup radioGroup;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button btnNext;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        
    }
}
