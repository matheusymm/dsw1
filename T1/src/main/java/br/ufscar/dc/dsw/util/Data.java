package br.ufscar.dc.dsw.util;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Data {
    public static String localDateTimeToString(LocalDateTime time) {
        String dataHoraFormatada = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        return dataHoraFormatada;
    }

    public static LocalDateTime stringToLocalDateTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public static String formataData(Date date) {
        String dataFormatada = date.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return dataFormatada;
    }

    public static String formataHora(LocalDateTime date) {
        String data = date.toString();
        data.lastIndexOf(":");
        data = data.substring(0, data.lastIndexOf(":"));
        return data;
    }

    public static LocalDateTime setHoraCheia(LocalDateTime data) {
        if(data.getSecond() > 0 || data.getMinute() > 0) {
            data = data.plusHours(1);
            data = data.withMinute(0);
            data = data.withSecond(0);
        }
        return data;
    }
}