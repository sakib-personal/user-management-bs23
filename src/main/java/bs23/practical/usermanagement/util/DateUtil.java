package bs23.practical.usermanagement.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    public static final DateTimeFormatter STANDARD_DATE_FORMAT_WITH_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter STANDARD_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String toDateTimeString(LocalDateTime date) {
        return Objects.nonNull(date) ? date.format(STANDARD_DATE_FORMAT_WITH_TIME) : "";
    }

    public static String toDateString(LocalDate date) {
        return Objects.nonNull(date) ? date.format(STANDARD_DATE_FORMAT) : "";
    }

    public static LocalDate toLocalDate(String date) {
        try {
            return Objects.nonNull(date) ? LocalDate.parse(date, STANDARD_DATE_FORMAT) : null;
        } catch (Exception exception) {
            log.error("Exception occurred while parsing date. Stacktrace: \n{}", exception.getMessage());
            return null;
        }
    }

    public static Instant getStandardCurrentTimeStamp() {
        return Instant.now();
    }
}
