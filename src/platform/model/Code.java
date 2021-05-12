package platform.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

    private String code = "public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
    private LocalDateTime date = LocalDateTime.parse("2020-01-01T12:00:00");

    public Code() {
    }

    public Code(String code, LocalDateTime date) {
        this.code = code;
        this.date = date;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
