package homework;

import sun.util.resources.cldr.lag.LocaleNames_lag;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tasks {
    private static final Integer YEAR_2000 = 2000;
    private static final Integer HOURS = 24;

    public static List<String> fridays13(){
        List<String> months = new ArrayList<>();
        LocalDate date = LocalDate.of(YEAR_2000, 1, 1);
        while (date.isBefore(LocalDate.now())) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.getDayOfMonth() == 13) {
                months.add(date.getMonth().name());
            }
            date = date.plusDays(1);
        }
        return months;
    }

    public static List<YearMonth> endOnSundays(){
        List<YearMonth> yearMonths = new ArrayList<>();
        LocalDate date = LocalDate.of(YEAR_2000, 1, 1);
        while(date.isBefore(LocalDate.now())){
            if(date.lengthOfMonth() == date.getDayOfMonth() && date.getDayOfWeek() == DayOfWeek.SUNDAY){
                yearMonths.add(YearMonth.of(date.getYear(), date.getMonth()));
            }
            date = date.plusDays(1);
        }
        return yearMonths;
    }

    public static List<Year> birthdaysOnSaturdays(LocalDate birthday){
        List<Year> years = new ArrayList<>();
        LocalDate date = LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
        while(date.isBefore(LocalDate.now())){
            if(date.getMonth() == birthday.getMonth() && date.getDayOfMonth() == birthday.getDayOfMonth() && date.getDayOfWeek() == DayOfWeek.SATURDAY){
                years.add(Year.of(date.getYear()));
            }
            date = date.plusYears(1);
        }
        return years;
    }

    public static List<MonthDay> daysNotWith24Hours(Year year){
        List<MonthDay> monthDays = new ArrayList<>();

        LocalDateTime date = LocalDateTime.of(year.getValue(), 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(year.getValue() + 1, 1, 1, 0, 0);

        ZonedDateTime dateTime = ZonedDateTime.of(date, ZoneId.systemDefault());
        ZonedDateTime endTime = ZonedDateTime.of(end, ZoneId.systemDefault());

        while (dateTime.isBefore(endTime)){
            ZonedDateTime temp = dateTime.plusDays(1);
            if(Duration.between(dateTime, temp).toHours() != HOURS){
                monthDays.add(MonthDay.of(dateTime.getMonth(), dateTime.getDayOfMonth()));
            }
            dateTime = temp;
        }
        return monthDays;
    }

    public static List<ZoneId> zonesAlwaysClockShift(List<ZoneId> zones){
        return Collections.emptyList();
    }

    public static List<ZoneId> zonesNeverClockShift(List<ZoneId> zones){
        return zones.stream()
                .filter(zoneId -> zoneId.getRules().isFixedOffset())
                .collect(Collectors.toList());
    }

    public static List<ZoneId> zonesChangedClockShiftRules(List<ZoneId> zones){
        return zones.stream()
                .filter(zoneId -> !zoneId.getRules().isFixedOffset())
                .collect(Collectors.toList());
    }
}
