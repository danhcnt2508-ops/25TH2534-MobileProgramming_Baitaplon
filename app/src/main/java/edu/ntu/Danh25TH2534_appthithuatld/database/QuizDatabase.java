package edu.ntu.Danh25TH2534_appthithuatld.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.ntu.Danh25TH2534_appthithuatld.model.Category;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;
import edu.ntu.Danh25TH2534_appthithuatld.model.ExamHistory;

@Database(entities = {Question.class, Category.class, ExamHistory.class}, version = 1,exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {

    // Biến static lưu trữ instance duy nhất của database
    private static QuizDatabase instance;
    public abstract QuestionDao questionDao();
    public abstract CategoryDao categoryDao(); //đã bổ sung sau khi tạo thêm file CategoryDao.java

    // Tạo luồng phụ để xử lý database ngầm, thay thế cho allowMainThreadQueries()
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);
    public static synchronized QuizDatabase getInstance(Context context) {
        // Nếu database chưa từng được khởi tạo, tiến hành tạo mới
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "antoan_laodong_db")
                    .fallbackToDestructiveMigration()
                    //.allowMainThreadQueries()  --- bỏ thử dòng code này
                    // và thay đổi cách lấy câu hỏi trong QuizActivity.java
                    .addCallback(roomCallback) // BỔ SUNG: Tự động nạp data mẫu
                    .build();
        }
        return instance;
    }

    //callback xử lý khi tạo CSDL lần đầu
    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Chạy luồng nền (Background Thread) để thêm dữ liệu mẫu tránh làm treo máy
            Executors.newSingleThreadExecutor().execute(() -> {
                CategoryDao categoryDao = instance.categoryDao();
                QuestionDao questionDao = instance.questionDao();

                //Vòng lặp nạp toàn bộ câu hỏi từ DataRepository vào SQLite
                for (Question q : DataRepository.getDummyQuestions()) {
                    questionDao.insertQuestion(q);
                }
            });
        }
    };
}
