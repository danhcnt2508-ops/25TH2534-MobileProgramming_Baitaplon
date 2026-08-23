package edu.ntu.Danh25TH2534_appthithuatld.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import edu.ntu.Danh25TH2534_appthithuatld.model.Category;
import java.util.List;

@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCategories(List<Category> categories);

    //bổ sung
    @Query("SELECT * FROM categories")
    List<Category> getAllCategories();
}
