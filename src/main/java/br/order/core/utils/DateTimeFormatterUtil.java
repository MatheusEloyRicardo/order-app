package br.order.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeFormatterUtil {
    private static final DateTimeFormatter horaMinuto = DateTimeFormatter.ofPattern("HH:mm");

    public static String converteHoraMinutoEmString(LocalTime localTime){
        return localTime.format(horaMinuto);
    }

    public static LocalTime converteHoraMinutoEmLocalTime(String string){
        return LocalTime.parse(string, horaMinuto);
    }
}
