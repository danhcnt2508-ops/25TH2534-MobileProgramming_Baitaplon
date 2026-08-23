package edu.ntu.Danh25TH2534_appthithuatld.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import edu.ntu.Danh25TH2534_appthithuatld.model.Category;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;

@Database(entities = {Question.class, Category.class}, version = 1)
public abstract class QuizDatabase extends RoomDatabase {

    private static QuizDatabase instance;
    public abstract QuestionDao questionDao();
    public static synchronized QuizDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "antoan_laodong_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()   //ở đây làm nhanh để thử nghiệm..Nên dùng Thread/AsyncTask cho dự án lớn
                    .build();
        }
        return instance;
    }
}
