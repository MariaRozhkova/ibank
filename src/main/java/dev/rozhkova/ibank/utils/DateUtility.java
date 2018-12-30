package dev.rozhkova.ibank.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtility {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Возвращает полученную дату в виде хорошо отформатированной строки.
     *
     * @param date - дата, которая будет возвращена в виде строки
     * @return отформатированную строку
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return formatter.format(date);
    }

    /**
     * Преобразует строку, которая отформатирована по правилам
     * в объект {@link LocalDate}.
     * <p>
     * Возвращает null, если строка не может быть преобразована.
     *
     * @param dateString - дата в виде String
     * @return объект даты или null, если строка не может быть преобразована
     */
    public static LocalDate parse(String dateString) {
        try {
            return formatter.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
