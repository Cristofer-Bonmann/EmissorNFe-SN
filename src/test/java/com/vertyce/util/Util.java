package com.vertyce.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Util {

    /**
     * Converte uma data/hora em um objeto LocalDateTime. Ex.: 2022-01-01 12:30:00
     * @param strLocalDateTime String que será convertida.
     * @return objeto LocalTimeDate da String convertida.
     */
    public static LocalDateTime strToLocalDateTime(String strLocalDateTime){
        final String pattern = "yyyy-MM-dd HH:mm:ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.of("America/Sao_Paulo"));
        final LocalDateTime dateTime = LocalDateTime.parse(strLocalDateTime, formatter);

        return dateTime;
    }
}
