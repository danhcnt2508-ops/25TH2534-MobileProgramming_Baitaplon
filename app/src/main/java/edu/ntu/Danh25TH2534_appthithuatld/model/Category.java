package edu.ntu.Danh25TH2534_appthithuatld.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int id;                  //id khóa chính, tự tăng
    public String name;             //tên chủ đề
    public String description;      //mô tả ngắn gọn về chủ đề

    //Constructor mặc định, ko tham số
    public Category() {
    }

    //Constructor đầy đủ tham số
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //tạo getter, setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
