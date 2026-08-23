package edu.ntu.Danh25TH2534_appthithuatld.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")    // tạo thực thể Câu hỏi
public class Question {
    //tạo thuộc tính
    @PrimaryKey(autoGenerate = true)
    public int id;      //khóa chính, tự tăng

    public int categoryID;
    public String questionText;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public int correctOption;   //1 : A, 2 : B, 3 : C, 4 : D
    public String explanation;
    public boolean isBookmarked;

    //tạo Constructor mặc định, không tham số
    public Question() {
    }

    //tạo Constructor đầy đủ tham số
    public Question(int categoryID, String questionText, String optionA,
                    String optionB, String optionC, String optionD, int correctOption,
                    String explanation, boolean isBookmarked) {
        this.categoryID = categoryID;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        this.explanation = explanation;
        this.isBookmarked = isBookmarked;
    }

    //tạo các Getter, Setter cho các thuộc tính

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
