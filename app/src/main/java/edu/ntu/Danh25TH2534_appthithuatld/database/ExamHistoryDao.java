package edu.ntu.Danh25TH2534_appthithuatld.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.ntu.Danh25TH2534_appthithuatld.model.ExamHistory;

@Dao
public interface ExamHistoryDao {
    //1. tạo hàm lưu kết quả lượt thi vào SQLite
    @Insert
    void insertHistory(ExamHistory examHistory);

    //2. tạo hàm lấy toàn bộ lịch sử thi, xếp theo id giảm dần (lượt thi mới ở trên cùng)
    @Query("SELECT * FROM exam_history ORDER BY id DESC")
    List<ExamHistory> getAllHistory();

}
