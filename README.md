# 📱 AppThiThuATLD - Ứng dụng Thi Thử An Toàn Lao Động

**AppThiThuATLD** là ứng dụng Android hỗ trợ thi thử trắc nghiệm môn học **An toàn lao động**. Dự án được xây dựng dựa trên kiến trúc ứng dụng di động hiện đại, xử lý dữ liệu local an toàn và tối ưu hóa trải nghiệm người dùng bằng các tác vụ đa luồng.

---

## 🚀 Tính năng chính

* **Thi thử (Exam):** Tạo đề thi ngẫu nhiên (20 câu hỏi) kèm đồng hồ đếm ngược (10:00). Tự động nộp bài và khóa khi hết giờ.
* **Luyện tập (Practice):** Chế độ ôn tập không giới hạn thời gian, hỗ trợ kiểm tra đáp án trực quan theo thời gian thực (đổi màu lựa chọn Đúng/Sai). Chuyển câu hỏi bằng cử chỉ vuốt sang trái (Swipe Left).
* **Lịch sử làm bài:** Lưu trữ kết quả thi (điểm số, tổng số câu, ngày giờ làm bài) để người dùng dễ dàng theo dõi tiến độ.

---

## 🛠️ Công nghệ & Phần mềm sử dụng

* **Môi trường:** Android Studio Quail2 | AVD Pixel 7 (Android 13.0 - API 33).
* **Ngôn ngữ:** Java (JDK 21) | XML.
* **Cơ sở dữ liệu:** SQLite | Room Persistence Library.
* **Giao diện (UI/UX):** Google Material Design Components.
* **Đa luồng và cử chỉ:** Java Concurrency (ExecutorService / ThreadPool) | GestureDetector Component.

---

## 📊 Kiến trúc Dữ liệu (SQLite)

Hệ thống quản lý cơ sở dữ liệu local thông qua **Room Database** gồm 3 bảng thực thể:

### 1. Bảng `Categories` (Chủ đề học tập)
* `id` (INTEGER, PK, AutoIncrement): Mã chủ đề.
* `name` (TEXT): Tên chủ đề (Ví dụ: "An toàn điện").
* `description` (TEXT): Mô tả ngắn gọn.

### 2. Bảng `Questions` (Ngân hàng câu hỏi)
* `id` (INTEGER, PK, AutoIncrement): Mã câu hỏi.
* `category_id` (INTEGER, FK): Liên kết với bảng `Categories`.
* `question_text` (TEXT): Nội dung câu hỏi.
* `option_a` / `b` / `c` / `d` (TEXT): Các phương án lựa chọn.
* `correct_option` (INTEGER): Đáp án đúng (1: A, 2: B, 3: C, 4: D).
* `explanation` (TEXT): Giải thích đáp án.
* `is_bookmarked` (INTEGER): Trạng thái lưu câu hỏi (0 hoặc 1).

### 3. Bảng `Exam_History` (Lịch sử thi thử)
* `id` (INTEGER, PK, AutoIncrement): Mã lượt thi.
* `score` (INTEGER): Số câu trả lời đúng.
* `total_questions` (INTEGER): Tổng số câu trong đề thi.
* `date_taken` (TEXT): Ngày giờ thi (`YYYY-MM-DD HH:MM:SS`).

---

## 📁 Cấu trúc thư mục mã nguồn (Chế độ Android View)
```text
app/
├── manifests/
│   └── AndroidManifest.xml             # Cấu hình hệ thống & đăng ký Activity
├── kotlin+java/
│   └── edu.ntu.Danh25TH2534_appthithuatld/
│       ├── activity/
│       │   ├── HistoryActivity.java    # Điều khiển màn hình Xem lịch sử thi
│       │   ├── PracticeQuizActivity.java # Xử lý chức năng luyện tập & cử chỉ vuốt
│       │   └── QuizActivity.java       # Điều khiển màn hình Làm bài thi
│       ├── adapter/
│       │   └── ExamHistoryAdapter.java # Bộ quản lý và ánh xạ dữ liệu danh sách cuộn
│       ├── database/
│       │   ├── CategoryDao.java        # Giao tiếp dữ liệu bảng Chủ đề (Categories)
│       │   ├── DataRepository.java     # Chứa danh sách câu hỏi gốc và xử lý nạp dữ liệu
│       │   ├── ExamHistoryDao.java     # Giao tiếp dữ liệu bảng Lịch sử (Exam_History)
│       │   ├── QuestionDao.java        # Giao tiếp dữ liệu bảng Câu hỏi (Questions)
│       │   └── QuizDatabase.java       # Cổng kết nối trung tâm Room Database
│       ├── model/
│       │   ├── Category.java           # Định nghĩa thực thể bảng Categories
│       │   ├── ExamHistory.java        # Định nghĩa thực thể bảng Exam_History
│       │   └── Question.java           # Định nghĩa thực thể bảng Questions
│       └── MainActivity.java           # Điều khiển màn hình chính (Menu điều hướng)
└── res/
    ├── drawable/                       # Chứa tài nguyên hình ảnh, icon tĩnh
    ├── layout/                         # Chứa các file giao diện XML
    │   ├── activity_history.xml        # Giao diện màn hình danh sách lịch sử (RecyclerView)
    │   ├── activity_main.xml           # Giao diện menu chính môn học
    │   ├── activity_practice_quiz.xml  # Giao diện màn hình luyện tập
    │   ├── activity_quiz.xml           # Giao diện cấu trúc câu hỏi và đồng hồ đếm ngược
    │   └── item_exam_history.xml       # Giao diện thiết kế cho từng dòng hiển thị kết quả
    ├── mipmap/                         # Chứa icon ứng dụng theo các độ phân giải
    ├── values/                         # Chứa tệp cấu hình màu sắc, chuỗi chữ (strings.xml)
    └── xml/                            # Chứa các cấu hình XML bổ trợ khác


```

---

## ⚙️ Cơ chế kỹ thuật & Mã nguồn nổi bật

### 1. Nạp dữ liệu tự động (Seed Data)
Sử dụng `roomCallback` trong `QuizDatabase.java` kết hợp với `DataRepository.java`. Hệ thống tự động kích hoạt luồng phụ để nạp toàn bộ ngân hàng câu hỏi vào SQLite trong lần đầu tiên ứng dụng khởi chạy.

### 2. Xử lý bất đồng bộ an toàn (Asynchronous Processing)
Mọi thao tác đọc/ghi vào cơ sở dữ liệu SQLite đều được đẩy vào luồng phụ ngầm thông qua `databaseWriteExecutor` nhằm giải phóng **UI Thread (Main Thread)**, ngăn chặn hoàn toàn tình trạng đơ nghẽn màn hình. Dữ liệu sau khi tải xong được trả về giao diện thông qua phương thức `runOnUiThread`.

### 3. Tối ưu vòng đời cập nhật (Lifecycle Optimization)
Tại `HistoryActivity.java`, logic tải danh sách dữ liệu lịch sử thi được đặt trong hàm `onResume()` thay vì `onCreate()`. Điều này đảm bảo mỗi khi người dùng hoàn thành bài thi và quay lại màn hình lịch sử, danh sách kết quả luôn tự động làm mới.

---
## 💻 Các đoạn mã nguồn quan trọng

### 1. Truy vấn SQLite ngẫu nhiên để tạo đề thi (`QuestionDao.java`)
```java
// Lấy ngẫu nhiên 20 câu hỏi để làm đề thi thử
@Query("SELECT * FROM questions ORDER BY RANDOM() limit 20")
List<Question> getRandomExamQuestions();
```

### 2. Đọc dữ liệu từ SQLite bằng luồng phụ ngầm (`QuizActivity.java`)
```java
// Đẩy lệnh đọc dữ liệu vào luồng phụ đã khai báo trong file QuizDatabase.java
QuizDatabase.databaseWriteExecutor.execute(() -> {
    
    // Đọc dữ liệu từ SQLite ngầm bên dưới
    questionList = db.questionDao().getRandomExamQuestions();
    
    // Sau khi luồng phụ lấy xong dữ liệu, phải quay về luồng chính (Main Thread) để cập nhật giao diện UI
    runOnUiThread(() -> {
        if (questionList != null && !questionList.isEmpty()) {
            displayQuestion(currentQuestionIndex);
            startTimer(); // Bổ sung hàm đếm ngược
        } else {
            Toast.makeText(this, "Ngân hàng câu hỏi trống! Hãy thêm câu hỏi vào trước", Toast.LENGTH_LONG).show();
            finish();
        }
    });
});
```

### 3. Đồng hồ đếm ngược tự động khóa bài làm (`QuizActivity.java`)
```java
// Hàm xử lý đồng hồ đếm ngược
private void startTimer() {
    countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
        @Override
        public void onTick(long l) {
            timeLeftInMillis = l;
            updateCountDownText();
        }

        @Override
        public void onFinish() {
            timeLeftInMillis = 0;
            updateCountDownText();
            
            // Xử lý khi hết giờ
            Toast.makeText(QuizActivity.this, "Hết giờ làm bài!", Toast.LENGTH_LONG).show();
            saveExamHistory();
        }
    }.start();
}
```

### 4. Đóng gói dữ liệu bài thi và ghi điểm xuống SQLite (`QuizActivity.java`)
```java
private void saveExamHistory() {
    // 3.1 Lấy ngày giờ hiện tại của hệ thống để lưu vào lịch sử
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
    String currentDateAndTime = sdf.format(new Date());
    
    // 3.2 Tạo đối tượng ExamHistory mới với điểm số thực tế
    ExamHistory history = new ExamHistory(score, questionList.size(), currentDateAndTime);
    
    // 3.3 Dùng luồng phụ databaseWriteExecutor để lưu ngầm dữ liệu
    QuizDatabase.databaseWriteExecutor.execute(() -> {
        // Ghi vào SQLite
        db.examHistoryDao().insertHistory(history);
        
        // Trở về luồng chính để hiển thị kết quả và đóng màn hình
        runOnUiThread(() -> {
            Toast.makeText(QuizActivity.this, "Kết thúc bài thi! Đã lưu kết quả: " + score + "/" + questionList.size(), Toast.LENGTH_LONG).show();
            finish(); // Đóng màn hình làm bài, quay về trang chính
        });
    });
}
```

### 5. Truy vấn và sắp xếp lịch sử thi mới nhất lên đầu (`ExamHistoryDao.java`)
```java
// Tạo hàm lấy toàn bộ lịch sử thi, xếp theo id giảm dần (lượt thi mới ở trên cùng)
@Query("SELECT * FROM exam_history ORDER BY id DESC")
List<ExamHistory> getAllHistory();
```

### 6. Tải lại lịch sử tự động theo vòng đời màn hình (`HistoryActivity.java`)
```java
// Sử dụng onResume
@Override
protected void onResume() {
    super.onResume();
    loadExamHistory(); // Tự động quét lại Database mỗi khi màn hình hiển thị
}
```

### 7. Kiểm tra dữ liệu an toàn tránh lỗi hiển thị (`HistoryActivity.java`)
```java
// Cập nhật lại giao diện luồng chính
runOnUiThread(() -> {
    if (historyList != null && !historyList.isEmpty()) {
        adapter = new ExamHistoryAdapter(historyList);
        rvHistory.setAdapter(adapter);
    } else {
        Toast.makeText(HistoryActivity.this, "Bạn chưa thực hiện bài thi thử nào.", Toast.LENGTH_LONG).show();
    }
});
```

### 8. Ánh xạ dữ liệu động vào giao diện RecyclerView (`ExamHistoryAdapter.java`)
```java
@Override
public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
    ExamHistory history = historyList.get(position);
    holder.tvScore.setText("Điểm số: " + history.getScore() + "/" + history.getTotalQuestions());
    holder.tvDate.setText("Ngày thi: " + history.getDateTaken());
}
```
### 9. Bộ nhận diện và đánh chặn cử chỉ vuốt toàn cục (`PracticeQuizActivity.java`)
Để kích hoạt tương tác vuốt đổi câu hỏi linh hoạt mà không làm mất đi tính năng nhấn chọn của các thành phần con (`RadioButton`, `Button`), hệ thống triển khai kỹ thuật đánh chặn sự kiện chạm toàn màn hình thông qua phương thức `dispatchTouchEvent`:
```java
@Override
public boolean dispatchTouchEvent(MotionEvent ev) {
    // 1. Chuyển tiếp dữ liệu tọa độ chạm vào bộ nhận diện cử chỉ vuốt
    gestureDetector.onTouchEvent(ev);
    // 2. Giữ nguyên luồng sự kiện để các nút bấm con vẫn nhận được sự kiện Click thông thường
    return super.dispatchTouchEvent(ev);
}
```

Mô hình phân tích cử chỉ vuốt sang trái kiểm tra độ lệch khoảng cách kéo và gia tốc vuốt tối thiểu (`onFling`):
```java
private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_THRESHOLD = 100;         // Khoảng cách tối thiểu tính bằng Pixel
    private static final int SWIPE_VELOCITY_THRESHOLD = 100; // Tốc độ vuốt tối thiểu

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1 == null || e2 == null) return false;
        
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();
        
        // Xác định thao tác kéo theo phương ngang từ phải qua trái
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (diffX < -SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                onSwipeLeft(); // Gọi hàm dịch chỉ mục sang câu tiếp theo
                return true;
            }
        }
        return false;
    }
}
```

### 10. Logic xử lý phản hồi Đúng/Sai và chuyển câu hỏi (`PracticeQuizActivity.java`)
Khi bấm nút "Kiểm tra đáp án", hệ thống tự động khóa lựa chọn và áp dụng mã màu trực quan: Xanh lục `#2E7D32` (Đúng) và Đỏ `#C62828` (Sai):
```
java
int correctOption = questionList.get(currentQuestionIndex).getCorrectOption();

if (answerOrder == correctOption) {
    score++;
    selectedRb.setTextColor(Color.parseColor("#2E7D32"));
} else {
    selectedRb.setTextColor(Color.parseColor("#C62828"));
    highlightCorrectOption(correctOption); // Tô xanh đáp án gốc đúng
}

setOptionsEnabled(false); // Khóa click chọn lại
isAnswerChecked = true;
```
Khi phát hiện cử chỉ vuốt sang trái hợp lệ, hệ thống kiểm tra điều kiện nghiêm ngặt đảm bảo người dùng đã kiểm tra đáp án câu cũ trước khi tiến sang câu tiếp theo:
```
java
private void onSwipeLeft() {
    if (!isAnswerChecked) {
        Toast.makeText(this, "Bạn phải bấm nút kiểm tra đáp án trước khi qua câu mới", Toast.LENGTH_SHORT).show();
        return;
    }

    if (currentQuestionIndex < questionList.size() - 1) {
    currentQuestionIndex++;isAnswerChecked = false;
    btnCheck.setEnabled(true);
    tvSwipeHint.setVisibility(View.INVISIBLE);
    rgOptions.clearCheck();
    setOptionsEnabled(true);
    resetButtonColor();
    displayQuestion(currentQuestionIndex);}}
```
---
## 📸 Hình ảnh minh họa sản phẩm
| Giao diện chính | Chức năng Thi thử | Chức năng Luyện tập | Lịch sử làm bài |
| :---: | :---: |  :---: |:---: |
| <img src="./screenshots/main.png" width="250" alt="Giao diện chính"> | <img src="./screenshots/quiz.png" width="250" alt="Giao diện thi thử"> | <img src="./screenshots/practice.png" width="250" alt="Giao diện Luyện tập"> | <img src="./screenshots/history.png" width="250" alt="Giao diện lịch sử"> |
 
---
## 💻 Hướng dẫn cài đặt nhanh (Installation Guide)

Làm theo các bước sau để sao chép (clone) và khởi chạy dự án này trên máy tính cá nhân của bạn:

### 1. Điều kiện tiên quyết
* Đã cài đặt **Android Studio** (Khuyến nghị phiên bản Quail hoặc mới hơn).
* Đã cài đặt **Java Development Kit (JDK 21)**.
* Đã cấu hình một thiết bị ảo **Android Virtual Device (AVD / Emulator)** chạy Android 13.0 (API 33) hoặc thiết bị thật kết nối qua USB Debugging.

### 2. Các bước thực hiện

**Bước 1: Clone kho lưu trữ về máy**
Mở Terminal/Git Bash trên máy tính và chạy lệnh sau:
```bash
git clone [https://github.com](https://github.com/danhcnt2508-ops/25TH2534-MobileProgramming_Baitaplon)
```

**Bước 2: Mở dự án bằng Android Studio**
1. Khởi động **Android Studio**.
2. Chọn **File** > **Open** (hoặc chọn **Open** ở màn hình chào mừng).
3. Di chuyển đến thư mục dự án `AppThiThuATLD` vừa clone và nhấn **OK**.

**Bước 3: Đồng bộ dự án với Gradle**
Hệ thống sẽ tự động tải các thư viện cần thiết. Nếu không, hãy nhấn vào biểu tượng **Sync Project with Gradle Files** (Hình con voi) trên thanh công cụ.

**Bước 4: Chạy ứng dụng**
1. Chọn thiết bị ảo (Emulator) hoặc thiết bị thật đã kết nối trên thanh công cụ.
2. Nhấn nút **Run** (Biểu tượng tam giác màu xanh lá cây hoặc phím tắt `Shift + F10`) để tiến hành biên dịch và cài đặt ứng dụng.

---
