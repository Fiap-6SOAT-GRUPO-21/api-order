package br.com.api_order.useCases.util;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeUtilsTest {

    @Test
    public void testGenerateExpirationDatePayment() {
        String expirationDate = DateTimeUtils.generateExpirationDatePayment();

        // Parse the returned date string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        ZonedDateTime parsedDate = ZonedDateTime.parse(expirationDate, formatter);

        // Get the current time in UTC and add 30 minutes
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime expectedDate = now.plus(30, ChronoUnit.MINUTES).withZoneSameInstant(ZoneOffset.ofHours(-4));

        // Allow a small margin of error for the test
        long differenceInSeconds = Math.abs(parsedDate.toEpochSecond() - expectedDate.toEpochSecond());
        assertTrue(differenceInSeconds < 5, "The expiration date is not within the expected range.");
    }
}