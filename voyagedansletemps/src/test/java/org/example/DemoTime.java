package org.example;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DemoTime {

    @Test
    void demoDate() {
        // java.util.Data, Java 1
        // with Year, Month, Day, Hour, Minute, Second, TZ
        var today = new Date();
        System.out.println(today);
        // Fri Jul 28 16:06:32 CEST 2023
    }

    @Test
    void demoCalendar() {
        // Java 1.1
        Calendar today = Calendar.getInstance();
        System.out.println(today);
        // java.util.GregorianCalendar[time=1690553446753,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Europe/Paris",offset=3600000,dstSavings=3600000,useDaylight=true,transitions=184,lastRule=java.util.SimpleTimeZone[id=Europe/Paris,offset=3600000,dstSavings=3600000,useDaylight=true,startYear=0,startMode=2,startMonth=2,startDay=-1,startDayOfWeek=1,startTime=3600000,startTimeMode=2,endMode=2,endMonth=9,endDay=-1,endDayOfWeek=1,endTime=3600000,endTimeMode=2]],firstDayOfWeek=2,minimalDaysInFirstWeek=4,ERA=1,YEAR=2023,MONTH=6,WEEK_OF_YEAR=30,WEEK_OF_MONTH=4,DAY_OF_MONTH=28,DAY_OF_YEAR=209,DAY_OF_WEEK=6,DAY_OF_WEEK_IN_MONTH=4,AM_PM=1,HOUR=4,HOUR_OF_DAY=16,MINUTE=10,SECOND=46,MILLISECOND=753,ZONE_OFFSET=3600000,DST_OFFSET=3600000]
    }

    @Test
    void demoJavaTime(){
        // Java 8: package java.time
        // - LocalDate: year month day
        // - LocalTime: hour minute second, fraction
        // - LocalDateTime: year month day hour minute second, fraction
        // - ZonedDateTime: idem LocalDateTime with ZoneId

        var date = LocalDate.now();
        var time = LocalTime.now();
        var datetime = LocalDateTime.now();
        System.out.println(date);
        System.out.println(time);
        System.out.println(datetime);

        var zonedDatetime = ZonedDateTime.now();
        System.out.println(zonedDatetime);

        var timezoneCanadaToronto = ZoneId.of("America/Toronto");
        var zonedDatetime2 = ZonedDateTime.now(timezoneCanadaToronto);
        System.out.println(zonedDatetime2);

        var timezoneJapanTokyo = ZoneId.of("Asia/Tokyo");
        var zonedDatetime3 = ZonedDateTime.now(timezoneJapanTokyo);
        System.out.println(zonedDatetime3);

        var date2 = LocalDate.of(2023, 7, 31);
        var datetime3 = LocalDateTime.of(2023, 7, 31, 8, 0);
        var zonedDatetime4 = ZonedDateTime.of(datetime3, ZoneId.of("Europe/Paris"));
        System.out.println(date2);
        System.out.println(datetime3);
        System.out.println(zonedDatetime4);

        var format = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
        System.out.println(zonedDatetime4.format(format));

        String isoDateTimeStr = "2020-02-29T08:30:00";
        String frenchDateTimeStr = "31/07/2023 08:01:02";
        var datetime5 = LocalDateTime.parse(isoDateTimeStr);
        var datetime6 = LocalDateTime.parse(frenchDateTimeStr, format);
        System.out.println(datetime5);
        System.out.println(datetime6);
    }

    @Test
    void demoTimezone() {
        ZoneId.getAvailableZoneIds()
                .stream()
                .forEach(System.out::println);
    }

}
