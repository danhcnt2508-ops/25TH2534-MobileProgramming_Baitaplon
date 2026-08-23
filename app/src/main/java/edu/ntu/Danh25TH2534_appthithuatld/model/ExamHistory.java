package edu.ntu.Danh25TH2534_appthithuatld.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exam_history")
public class ExamHistory {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int score;              //số câu trả lời đúng
    private int totalQuestions;     //tổng số câu trong đề thi
    private String dateTaken;       //ngày giờ thi

    public ExamHistory() {
    }

    public ExamHistory(int score, int totalQuestions, String dateTaken) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.dateTaken = dateTaken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }
}
