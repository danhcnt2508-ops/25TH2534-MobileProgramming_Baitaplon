package edu.ntu.Danh25TH2534_appthithuatld.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;
import java.util.List;
@Dao
public interface QuestionDao {
    // Sử dụng OnConflictStrategy.REPLACE để tránh lỗi trùng khóa chính (Primary Key) khi insert lại
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestion(Question question);

    //Bổ sung: chèn hàng loại câu hỏi
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllQuestions(List<Question> questions);

    //lấy câu hỏi theo từng chuyên mục bài học trong Category
    @Query("SELECT * FROM questions WHERE categoryID = :catId")
    List<Question> getQuestionsByCategory(int catId);

    //lấy ngẫu nhiên 20 câu hỏi để làm đề thi thử
    @Query("SELECT * FROM questions ORDER BY RANDOM() limit 20")
    List<Question> getRandomExamQuestions();

    //Bổ sung: xóa toàn bộ câu hỏi (dùng khi cập nhật lại ngân hàng câu hỏi)
    @Query("DELETE FROM questions")
    void deleteAllQuestions();
}
