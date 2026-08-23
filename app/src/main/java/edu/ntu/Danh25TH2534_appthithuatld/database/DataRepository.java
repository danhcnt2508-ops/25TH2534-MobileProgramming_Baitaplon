package edu.ntu.Danh25TH2534_appthithuatld.database;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.Danh25TH2534_appthithuatld.model.Category;
import edu.ntu.Danh25TH2534_appthithuatld.model.Question;

public class DataRepository {

    //Hàm tạo danh mục bài học mẫu
    public static List<Category> getDummyCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Tổng quan về ATVSLĐ và vi khí hậu",  "Tổng hợp các kiến thức nền tảng pháp luật và các yếu tố môi trường nhà xưởng"));
        return categories;
    }

    //Hàm tạo câu hỏi trắc nghiệm
    public static List<Question> getDummyQuestions() {
        List<Question> questions = new ArrayList<>();

        //giả định CategoryID = 1
        int catID = 1;

        questions.add(new Question(catID,
                "1.Ý nghĩa nào của công tác Bảo hộ lao động (BHLĐ) thể hiện tính nhân đạo sâu sắc?",
                "A. Tăng năng suất lao động và lợi nhuận cho doanh nghiệp.",
                "B. Đảm bảo cuộc sống hạnh phúc, bảo toàn nguyên vẹn sức khỏe cho người lao động.",
                "C. Hạn chế hư hỏng và hao mòn máy móc thiết bị.",
                "D. Giảm tiền đóng bảo hiểm tai nạn xã hội.",
                2, "Công tác BHLĐ chăm lo sức khỏe và tính mạng cho người lao động, thể hiện tính nhân văn sâu sắc.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "2.Theo Luật ATVSLĐ, 'Yếu tố nguy hiểm' được định nghĩa là gì?",
                "A. Yếu tố gây suy giảm sức khỏe con người một cách từ từ.",
                "B. Yếu tố gây tai nạn, chấn thương tức thì hoặc tử vong cho con người trong quá trình lao động.",
                "C. Độ ồn vượt mức cho phép tại khu vực làm việc.",
                "D. Nồng độ hơi khí độc hại vượt ngưỡng tiêu chuẩn.",
                2, "Yếu tố nguy hiểm tác động gây hậu quả chấn thương hoặc tử vong ngay lập tức.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "'3.Yếu tố có hại' trong sản xuất được hiểu là:",
                "A. Yếu tố gây tai nạn lao động trực tiếp lập tức.",
                "B. Các sự cố sập đổ cấu kiện xây dựng công trình.",
                "C. Yếu tố gây bệnh tật, làm suy giảm sức khỏe con người một cách dần dần trong quá trình lao động.",
                "D. Hiện tượng phóng điện hồ quang đột ngột.",
                3, "Yếu tố có hại ngấm dần theo thời gian, là nguyên nhân trực tiếp gây ra bệnh nghề nghiệp.", false)); // Đáp án: C

        questions.add(new Question(catID,
                "4.Điều kiện vi khí hậu tại nơi làm việc bao gồm tập hợp các yếu tố nào sau đây?",
                "A. Ánh sáng, độ ồn, độ rung và bức xạ.",
                "B. Nhiệt độ, độ ẩm, tốc độ gió và bức xạ nhiệt.",
                "C. Nồng độ bụi, hơi khí độc và vi sinh vật.",
                "D. Áp suất khí quyển, trọng lực và điện từ trường.",
                2, "Nhiệt độ, độ ẩm, tốc độ gió và bức xạ nhiệt cấu thành trạng thái vi khí hậu môi trường.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "5.Khi nhiệt độ môi trường làm việc quá cao kết hợp với độ ẩm lớn hơn 80%, cơ thể người sẽ gặp hiện tượng gì?",
                "A. Thoát mồ hôi rất nhanh và làm mát cơ thể hiệu quả.",
                "B. Sự bay hơi mồ hôi bị cản trở, gây tích tụ nhiệt dẫn đến say nóng, mệt mỏi.",
                "C. Tăng huyết áp đột ngột làm co thắt mạch máu đầu ngón tay.",
                "D. Cơ thể tự động giảm nhịp tim để hạn chế sinh nhiệt.",
                2, "Độ ẩm cao cản trở quá trình bốc hơi mồ hôi qua da, gây ứ trệ nhiệt lượng cơ thể.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "6.Tốc độ lưu thông không khí trong nhà xưởng vào mùa hè tiêu chuẩn nên được khống chế ở mức nào là tốt nhất?",
                "A. Từ 0 đến 0,2 m/s", "B. Từ 1,0 đến 2,0 m/s", "C. Từ 5,0 đến 8,0 m/s", "D. Trên 10 m/s",
                2, "Mùa hè cần tốc độ gió lớn hơn (1,0 - 2,0 m/s) để tăng cường giải nhiệt cho người lao động.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "7.Tiếng ồn bắt đầu gây tổn thương cơ học cho màng nhĩ nếu cường độ vượt quá ngưỡng nào sau đây kéo dài?",
                "A. 50 dBA", "B. 70 dBA", "C. 85 dBA", "D. 110 dBA",
                3, "Ngưỡng 85 dBA là giới hạn bắt đầu gây tổn thương cơ quan thính giác khi tiếp xúc lâu dài.", false)); // Đáp án: C

        questions.add(new Question(catID,
                "8.Loại bụi nào có khả năng đi sâu vào tận phế nang phổi và gây ra bệnh phổi nhiễm bụi nguy hiểm?",
                "A. Bụi thô có kích thước hạt lớn hơn 10 µm.",
                "B. Bụi siêu mịn có kích thước hạt nhỏ hơn 5 µm.",
                "C. Bụi hữu cơ thực vật như bụi bông, gỗ.",
                "D. Bụi dệt từ các sợi bông tổng hợp.",
                2, "Hạt bụi mịn kích thước dưới 5 micron vượt qua được hàng rào lọc tự nhiên để vào phế nang.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "9.Tác động của điện từ trường tần số cao đối với cơ thể người chủ yếu gây rối loạn hệ cơ quan nào trực tiếp nhất?",
                "A. Hệ hô hấp và tiêu hóa.", "B. Hệ thần kinh trung ương và hệ tim mạch.",
                "C. Hệ xương khớp ngoại biên.", "D. Hệ bài tiết qua da.",
                2, "Sóng điện từ tần số cao tác động kích thích mạnh lên hệ tim mạch và thần kinh trung ương.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "10.Trong các biện pháp cải thiện vi khí hậu xưởng sản xuất, biện pháp nào mang tính chủ động và bền vững nhất?",
                "A. Trang bị quần áo bảo hộ dày cho công nhân.",
                "B. Thiết kế hệ thống thông gió tự nhiên kết hợp thông gió cưỡng bức khoa học.",
                "C. Cho công nhân uống nước muối loãng định kỳ giữa ca.",
                "D. Rút ngắn thời gian làm việc của mỗi ca xuống còn 2 giờ.",
                2, "Thông gió hệ thống là giải pháp kỹ thuật gốc rễ giúp làm sạch môi trường không khí xưởng.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "11.Tai nạn lao động xảy ra do 'thiết kế bệ máy không vững chắc gây rung động mạnh' thuộc nhóm nguyên nhân nào?",
                "A. Nguyên nhân kỹ thuật.", "B. Nguyên nhân tổ chức quản lý.",
                "C. Nguyên nhân chủ quan (bản thân).", "D. Nguyên nhân vệ sinh lao động.",
                1, "Thiết kế lỗi cơ khí bệ máy thuộc về lỗi kỹ thuật thiết bị công nghệ.", false)); // Đáp án: A

        questions.add(new Question(catID,
                "12.Ý nghĩa kinh tế của công tác BHLĐ thể hiện rõ nhất qua việc:",
                "A. Giảm chi phí bồi thường tai nạn và duy trì lực lượng lao động ổn định, tăng năng suất sản xuất.",
                "B. Giúp doanh nghiệp bán được sản phẩm với giá cao hơn.",
                "C. Cắt giảm hoàn toàn tiền đóng bảo hiểm tai nạn cho công nhân.",
                "D. Giảm thuế thu nhập doanh nghiệp hàng năm.",
                1, "Làm tốt an toàn sẽ tránh thất thoát tài chính do sự cố hư hỏng và tiền đền bù tai nạn.", false)); // Đáp án: A

        questions.add(new Question(catID,
                "13.Bệnh nghề nghiệp là gì?",
                "A. Bệnh phát sinh đột ngột do chấn thương cơ học tại xưởng làm việc.",
                "B. Bệnh đặc thù phát sinh do tác động của điều kiện lao động có hại đối với người lao động.",
                "C. Bệnh di truyền từ gia đình của người lao động.",
                "D. Bệnh cảm cúm thông thường do thay đổi thời tiết.",
                2, "Bệnh nghề nghiệp mang tính chất đặc thù, tích tụ lâu ngày do đặc thù công việc độc hại độc tố.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "14.Yếu tố rung động cơ khí truyền vào cơ thể người thông qua dụng cụ cầm tay lâu ngày sẽ gây ra bệnh lý gì?",
                "A. Bệnh điếc nghề nghiệp.", "B. Bệnh bụi phổi xơ hóa.",
                "C. Bệnh rung chuyển nghề nghiệp.", "D. Bệnh sạm da do hóa chất.",
                3, "Rung chuyển cục bộ tay gây rối loạn vận mạch, thoái hóa khớp cơ xương tay.", false)); // Đáp án: C
        questions.add(new Question(catID,
                "15.Theo tiêu chuẩn vệ sinh lao động, độ ẩm không khí tối ưu tại khu vực sản xuất lắp ráp linh kiện điện tử là:",
                "A. Từ 0% đến 20%.", "B. Từ 40% đến 70%.", "C. Từ 80% đến 90%.", "D. Trên 95%.",
                2, "Độ ẩm lý tưởng 40% - 70% vừa bảo vệ sức khỏe vừa ngăn tĩnh điện cho linh kiện.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "16.Biện pháp kỹ thuật nào sau đây giúp kiểm soát hiệu quả nhất nồng độ khí độc phát sinh trong quá trình hàn điện?",
                "A. Sử dụng quạt máy thổi trực tiếp vào mặt thợ hàn.",
                "B. Lắp đặt hệ thống chụp hút khói độc cục bộ ngay tại vị trí hàn.",
                "C. Yêu cầu thợ hàn nín thở trong suốt quá trình mồi hồ quang.",
                "D. Mở tất cả các cửa sổ xung quanh xưởng.",
                2, "Chụp hút khói tại chỗ thu gom triệt để hơi độc trước khi phát tán ra xung quanh.", false)); // Đáp án: B

        questions.add(new Question(catID,
                "17.Bụi vô cơ phát sinh từ đá granite chứa thành phần hóa học nguy hiểm nào gây xơ hóa phế nang?",
                "A. Oxit sắt (Fe2O3).", "B. Silic tự do (SiO2).",
                "C. Cacbonat canxi (CaCO3).", "D. Sulfat đồng (CuSO4).",
                2, "SiO2 (Thạch anh) thâm nhập phế nang tạo mô sẹo xơ cứng, làm giảm dung tích phổi.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "18.Khi tiếp xúc với nguồn bức xạ hồng ngoại cường độ cao trong các lò luyện kim, cơ quan nào của con người dễ bị tổn thương nhất?",
                "A. Thính giác.", "B. Thị giác (gây đục thủy tinh thể).","C. Hệ cơ bắp đùi.", "D. Hệ tiêu hóa.",
                2, "Tia hồng ngoại mang năng lượng nhiệt lớn phá hủy cấu trúc protein lăng kính mắt.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "19.Biện pháp cách ly nguồn phát sinh tiếng ồn bằng cabin cách âm thuộc nhóm biện pháp an toàn nào?",
                "A. Biện pháp bảo vệ cá nhân.", "B. Biện pháp kỹ thuật tập thể.",
                "C. Biện pháp hành chính - tổ chức.", "D. Biện pháp y tế dự phòng.",
                2, "Vách ngăn, cabin cách âm thuộc giải pháp bảo vệ kỹ thuật công trình tập thể.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "20.Đối tượng chịu tác động trực tiếp và cần được bảo vệ chính của Luật ATVSLĐ là ai?",
                "A. Chủ doanh nghiệp và ban hội đồng quản trị.","B. Người lao động (bao gồm cả người lao động không có hợp đồng lao động).",
                "C. Các thiết bị máy móc tự động hóa.","D. Các cơ quan thanh tra kiểm tra của Bộ Lao động.",
                2, "Bộ luật bảo vệ quyền lợi an toàn cho mọi cá nhân trực tiếp bỏ sức lao động sản xuất.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "21.Việc bố trí thời gian nghỉ ngơi hợp lý giữa các ca làm việc nặng nhọc là áp dụng biện pháp an toàn nào?",
                "A. Biện pháp kỹ thuật thi công.", "B. Biện pháp tổ chức - hành chính.",
                "C. Biện pháp bảo vệ cá nhân (PPE).", "D. Biện pháp xử lý sự cố.",
                2, "Điều phối thời gian làm việc và nghỉ ngơi thuộc quản lý tổ chức nhân sự hành chính.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "22.Khói hàn điện thường chứa các hạt bụi oxit kim loại siêu mịn của kim loại nào sau đây có thể gây nhiễm độc mãn tính?",
                "A. Nhôm và Silic.", "B. Chì, sắt, mangan và niken.","C. Canxi và Natri.", "D. Vàng và Bạc.",
                2, "Hơi kim loại nặng bốc lên khi cháy điện cực bám vào đường máu tích tụ độc tố.", false)); // Đáp án:
        questions.add(new Question(catID,
                "23.Bệnh điếc nghề nghiệp có đặc điểm lâm sàng nguy hiểm nào sau đây?",
                "A. Có thể tự hồi phục hoàn toàn sau 1 tuần nghỉ ngơi.","B. Tổn thương tế bào thần kinh thính giác không hồi phục, không thể chữa khỏi bằng thuốc.",
                "C. Chỉ xảy ra với người lớn tuổi.","D. Gây chảy máu tai liên tục hàng ngày.",
                2, "Tế bào ốc tai thần kinh đã chết thì không thể tái tạo lại bằng can thiệp y tế.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "24.Khái niệm 'Vệ sinh lao động' tập trung nghiên cứu vấn đề gì?",
                "A. Cách quét dọn rác thải nhà xưởng sạch sẽ.","B. Các yếu tố có hại của điều kiện lao động để đề xuất biện pháp phòng bệnh nghề nghiệp.",
                "C. Cách sửa chữa các máy móc cơ khí bị hỏng.","D. Chế độ lương bổng và bảo hiểm xã hội của công nhân.",
                2, "Mục tiêu tối thượng của vệ sinh lao động là phòng chống tối đa bệnh nghề nghiệp phát sinh.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "25.Bức xạ tử ngoại (UV) phát ra mạnh nhất từ nguồn nào sau đây trong xưởng cơ khí công nghiệp?",
                "A. Động cơ điện xoay chiều đang chạy.","B. Hồ quang của quá trình hàn điện hoặc cắt kim loại bằng plasma.",
                "C. Máy nén khí pit-tông.","D. Các lò sấy điện trở nhiệt.",
                2, "Tia lửa hồ quang điện chứa nồng độ năng lượng tử ngoại bức xạ cực kỳ đậm đặc.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "26.Khi dòng điện xoay chiều tần số 50 Hz chạy qua cơ thể người, trị số dòng điện bắt đầu gây ra trạng thái giật tê nhẹ là bao nhiêu?",
                "A. Khoảng 0,6 - 1,5 mA.", "B. Khoảng 10 - 15 mA.","C. Khoảng 100 mA.", "D. Trên 1 A.",
                1, "Mức dòng điện cảm nhận ngưỡng siêu nhỏ bắt đầu gây tê bì thần kinh kích thích.", false)); // Đáp án: A
        questions.add(new Question(catID,
                "27.Ý nghĩa chính trị của công tác BHLĐ thể hiện qua việc:","A. Thể hiện sự chăm lo của Đảng, Nhà nước đối với con người trong sự nghiệp công nghiệp hóa, hiện đại hóa.","B. Làm tăng giá trị xuất khẩu của hàng hóa.",
                "C. Tiết kiệm ngân sách chi trả trợ cấp xã hội cho gia đình nghèo.","D. Thúc đẩy tiến trình số hóa dữ liệu lao động quốc gia.",1, "Con người là vốn quý, bảo vệ sức khỏe lao động chứng minh tính ưu việt của chế độ chính trị.", false)); // Đáp án: A
        questions.add(new Question(catID,
                "28.Chất lỏng cực độc PCB trước đây thường được sử dụng làm chất gì trong các thiết bị điện cũ?",
                "A. Chất làm sạch tiếp điểm nút bấm.", "B. Dầu cách điện trong máy biến thế và tụ điện.",
                "C. Dung môi pha sơn tĩnh điện.", "D. Chất hàn kín cổ góp động cơ.",
                2, "PCB là hợp chất dầu biến thế có tính cách điện cực cao nhưng độc tính tồn lưu rất lâu hại môi trường.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "29.Nhóm nguyên nhân tai nạn lao động do 'người thợ mệt mỏi, ngủ gật trong ca trực' được phân loại vào:",
                "A. Nguyên nhân kỹ thuật công nghệ.", "B. Nguyên nhân chủ quan (bản thân người lao động).",
                "C. Nguyên nhân tổ chức sản xuất.", "D. Nguyên nhân bất khả kháng do thời tiết.",
                2, "Trạng thái mỏi mệt ngủ gật thuộc ý thức cơ địa chủ quan cá nhân hành vi.", false)); // Đáp án: B
        questions.add(new Question(catID,
                "30.Nhận định nào sau đây đúng về mối quan hệ giữa An toàn lao động và Vệ sinh lao động?",
                "A. An toàn lao động và Vệ sinh lao động là hai lĩnh vực hoàn toàn độc lập, không liên quan đến nhau.",
                "B. An toàn lao động tập trung vào việc phòng ngừa tai nạn, chấn thương; Vệ sinh lao động tập trung vào phòng ngừa bệnh nghề nghiệp, bảo vệ sức khỏe lâu dài.",
                "C. Vệ sinh lao động chỉ liên quan đến việc lau dọn vệ sinh máy móc thiết bị sau giờ làm việc.",
                "D. An toàn lao động chỉ áp dụng cho ngành xây dựng, Vệ sinh lao động áp dụng cho ngành y tế.",
                2, "Một bên chống tai nạn tức thời nguy hiểm, một bên chống độc hại tích tụ lâu dài.", false)); // Đáp án: B

        return questions;
    }

}
