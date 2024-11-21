package br.com.api_order.use_case.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void testGenerateExpirationDatePayment() {
        String expirationDate = DateTimeUtils.generateExpirationDatePayment();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ZonedDateTime parsedDate = ZonedDateTime.parse(expirationDate, formatter);

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC).plus(30, ChronoUnit.MINUTES).withZoneSameInstant(ZoneOffset.ofHours(-4));
        assertEquals(now.getYear(), parsedDate.getYear());
        assertEquals(now.getMonth(), parsedDate.getMonth());
        assertEquals(now.getDayOfMonth(), parsedDate.getDayOfMonth());
        assertEquals(now.getHour(), parsedDate.getHour());
        assertEquals(now.getMinute(), parsedDate.getMinute());
    }

}