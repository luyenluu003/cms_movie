package cms.cms.common;

import cms.cms.dto.AjaxSearchDto;
import io.micrometer.common.util.StringUtils;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
public class Helper {

    // Hàm chuyển đổi từ List<String[]> thành List<AjaxSearchDto>
    public static List<AjaxSearchDto> listAjax(List<Object[]> input, int type) {
        List<AjaxSearchDto> listAjax = new ArrayList<>();
        for (Object[] strings : input) {
            AjaxSearchDto dto = new AjaxSearchDto();
            dto.setId((String) strings[0]);  // category_code
            if (type == 0) {
                dto.setText(strings[0] + " - " + strings[1]);  // category_code - name
            } else {
                dto.setText((String) strings[1]);  // chỉ lấy name
            }
            listAjax.add(dto);
        }
        return listAjax;
    }

    // Hàm xử lý chuỗi tìm kiếm
    public static String processStringSearch(String input) {
        if (StringUtils.isNotEmpty(input)) {
            return input.trim();  // Loại bỏ khoảng trắng đầu và cuối
        }
        return "";
    }

    public static Date convertDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return java.sql.Timestamp.valueOf(dateTime);
    }

    public static Date standardDateV2(String input) {
        if (input.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) {
            input += ":00";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Chuỗi thời gian không hợp lệ: " + input);
        }
    }
}
