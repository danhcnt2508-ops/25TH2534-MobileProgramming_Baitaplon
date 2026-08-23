package edu.ntu.Danh25TH2534_appthithuatld.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;
import java.util.List;
@Dao
public interface QuestionDao {
    @Insert
    void insertQuestion(Question question);

    //lấy câu hỏi theo từng chuyên mục bài học trong Category
    @Query("SELECT * FROM questions WHERE categoryID = : catId")
    List<Question> getQuestionsByCategory(int catId);

    //lấy ngẫu nhiên 20 câu hỏi để làm đề thi thử
    @Query("SELECT * FROM questions ORDER BY RANDOM() LIMIT 20")
    List<Question> getRandomExamQuestions();

}
