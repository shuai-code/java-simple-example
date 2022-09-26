package org.shuai.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class SimpleLocalDate {

    /**
     * 输出当前日期
     * */
    public static void doPrintNow() {
        LocalDate date = LocalDate.now();
        // 输出2022-09-26
        System.out.println(date);
    }

    /**
     * 输出日期对应月份的年份
     * */
    public static void doPrintYear(LocalDate date) {
        // 输出2022
        System.out.println(date.getYear());
    }

    /**
     * 输出日期对应月份的单词
     * */
    public static void doPrintMonth(LocalDate date) {
        // 输出SEPTEMBER
        System.out.println(date.getMonth());
    }

    /**
     * 输出日期对应月份的数字
     * */
    public static void doPrintMonthValue(LocalDate date) {
        // 输出9
        System.out.println(date.getMonthValue());
    }

    /**
     * 输出日期对应月份的第几天
     * */
    public static void doPrintDayOfMonth(LocalDate date) {
        // 输出26
        System.out.println(date.getDayOfMonth());
    }

    /**
     * 输出日期对应周的星期几, 以英文单词返回
     * */
    public static void doPrintDayOfWeek(LocalDate date) {
        // 输出MONDAY
        System.out.println(date.getDayOfWeek());
    }

    /**
     * 输出日期对应年的第几天
     * */
    public static void doPrintDayOfYear(LocalDate date) {
        // 输出269
        System.out.println(date.getDayOfYear());
    }

    /**
     * 是否为闰年
     * */
    public static void doPrintIsLeapYear(LocalDate date) {
        // 输出false
        System.out.println(date.isLeapYear());
    }

    /**
     * 初始化date的天为指定天
     * */
    public static void doPrintWithDayOfMonth(LocalDate date) {
        LocalDate localDate = date.withDayOfMonth(2);
        // 输出2022-09-02
        System.out.println(localDate);
    }

    /**
     * 初始化date的月份为指定月
     * */
    public static void doPrintWithMonth() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2022-03-26
        System.out.println(date.withMonth(3));
    }

    /**
     * 初始化date的年份为指定年
     * */
    public static void doPrintWithYear() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出1990-09-26
        System.out.println(date.withYear(1990));
    }

    /**
     * 打印当前系统使用的日历系统
     * */
    public static void doPrintChronology() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出ISO
        System.out.println(date.getChronology());
    }

    /**
     * 当前日期减多少天
     * */
    public static void doPrintMinusDays() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2022-09-21
        System.out.println(date.minusDays(5));
    }

    /**
     * 当前日期加多少天
     * */
    public static void doPrintPlusDays() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2022-10-01
        System.out.println(date.plusDays(5));
    }

    /**
     * 当前日期减多少年
     * */
    public static void doPrintMinusYears() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2024-09-26
        System.out.println(date.minusYears(2));
    }

    /**
     * 当前日期减多少月
     * */
    public static void doPrintMinusMonths() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2022-09-12
        System.out.println(date.minusMonths(2));
    }

    /**
     * 当前日期加多少月
     * */
    public static void doPrintPlusMonths() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2022-07-26
        System.out.println(date.plusMonths(2));
    }

    /**
     * 当前日期加多少周
     * */
    public static void doPrintPlusWeeks() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2022-10-10
        System.out.println(date.plusWeeks(2));
    }

    /**
     * 当前日期加多少年
     * */
    public static void doPrintPlusYears() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2024-09-26
        System.out.println(date.plusYears(2));
    }

    /**
     * 当前日期减多少周
     * */
    public static void doPrintMinusWeeks() {
        // 输出2022-09-26
        LocalDate date = LocalDate.now();
        // 输出2022-09-12
        System.out.println(date.minusWeeks(2));
    }

    /**
     * 日期大于判断, 传入日志大于当前日期
     * */
    public static void doPrintIsAfter(LocalDate date) {
        LocalDate now = LocalDate.now();
        System.out.println(date.isAfter(now));
    }

    /**
     * 以指定格式输出LocalDateTime
     * */
    public static void doPrintLocalDateTimeFormat() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 输出2022-09-26 16:25:19
        System.out.println(format);
    }

    /**
     * LocalDateTime以指定时区转成时间戳
     * */
    public static void doPrintLocalDateTimec() {
        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        // 输出1664180808
        System.out.println(second);
    }

    /**
     * 输出LocalDateTime的纳秒数
     * */
    public static void doPrintLocalDateTimeNano() {
        LocalDateTime now = LocalDateTime.now();
        // 输出832000000
        System.out.println(now.getNano());
    }

    /**
     * 输出LocalDateTime的秒数
     * */
    public static void doPrintLocalDateTimeSecond() {
        LocalDateTime now = LocalDateTime.now();
        // 输出20
        System.out.println(now.getSecond());
    }

    /**
     * 输出LocalDateTime的分钟数
     * */
    public static void doPrintLocalDateTimeMinute() {
        LocalDateTime now = LocalDateTime.now();
        // 输出29
        System.out.println(now.getMinute());
    }

    /**
     * 输出LocalDateTime的小时数
     * */
    public static void doPrintLocalDateTimeHour() {
        LocalDateTime now = LocalDateTime.now();
        // 输出20
        System.out.println(now.getHour());
    }

    /**
     * 将LocalDateTime转成LocalDate
     * */
    public static void doPrintLocalDateTimeToLocalDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();
    }

    /**
     * 将LocalDate转成LocalDateTime
     * 使用LocalDate和LocalTime构建LocalDateTime
     * */
    public static void doPrintLocalDateToLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 使用字符串格式的日期初始化LocalDate
     * */
    public static void doPrintLocalDateWithString() {
        LocalDate parse = LocalDate.parse("2002-01-05");
        // 输出2002-01-05
        System.out.println(parse);
    }

    /**
     * 使用字符串格式的日期初始化LocalDateTime
     * */
    public static void doPrintLocalDateTimeWithString() {
        LocalDateTime parse = LocalDateTime.parse("2002-01-05T05:03:01");
        // 输出2002-01-05T05:03:01
        System.out.println(parse);
    }
}
