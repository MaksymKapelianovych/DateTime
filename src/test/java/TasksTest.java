import homework.Tasks;
import org.junit.Test;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TasksTest {
    private static final List<ZoneId> ZONE_IDS = ZoneId.getAvailableZoneIds().stream().map(ZoneId::of).collect(Collectors.toList());


    @Test
    public void fridays13Test1(){
        List<String> expected = Arrays.asList("OCTOBER", "APRIL", "JULY");
        List<String> result = Tasks.fridays13().stream().limit(3).collect(Collectors.toList());
        assertEquals(expected, result);
    }

    @Test
    public void endOfSundaysTest1(){
        List<YearMonth> expected = Arrays.asList(YearMonth.of(2000, 4), YearMonth.of(2000, 12), YearMonth.of(2001, 9));
        List<YearMonth> result = Tasks.endOnSundays().stream().limit(3).collect(Collectors.toList());
        assertEquals(expected, result);
    }

    @Test
    public void birthdaysOnSaturdaysTest1(){
        List<Year> expected = Arrays.asList(Year.of(2000), Year.of(2005), Year.of(2011));
        List<Year> result = Tasks.birthdaysOnSaturdays(LocalDate.of(2000, 1, 8));
        assertEquals(expected, result);
    }

    @Test
    public void daysNotWith24HoursTest1(){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kiev"));
        List<MonthDay> expected = Arrays.asList(MonthDay.of(3, 29), MonthDay.of(10, 25));
        List<MonthDay> result = Tasks.daysNotWith24Hours(Year.of(2020));
        assertEquals(expected, result);
    }

    @Test
    public void zonesAlwaysClockShiftTest1(){
        List<ZoneId> zoneIds = Tasks.zonesAlwaysClockShift(ZONE_IDS);
        assertNotNull(zoneIds);
        assertTrue(zoneIds.isEmpty());
    }

    @Test
    public void zonesNeverClockShiftTest1(){
        List<ZoneId> zoneIds = Tasks.zonesNeverClockShift(ZONE_IDS);
        assertNotNull(zoneIds);
        assertFalse(zoneIds.isEmpty());
        assertTrue(zoneIds.stream().anyMatch(zoneId -> zoneId.equals(ZoneId.of("Etc/Greenwich"))));
    }

    @Test
    public void zonesNeverClockShiftTest2(){
        List<ZoneId> zoneIds = Tasks.zonesNeverClockShift(ZONE_IDS);
        assertNotNull(zoneIds);
        assertFalse(zoneIds.isEmpty());
        assertTrue(zoneIds.stream().noneMatch(zoneId -> zoneId.equals(ZoneId.of("Africa/Addis_Ababa"))));
    }

    @Test
    public void zonesChangedClockShiftRulesTest1(){
        List<ZoneId> zoneIds = Tasks.zonesChangedClockShiftRules(ZONE_IDS);
        assertNotNull(zoneIds);
        assertFalse(zoneIds.isEmpty());
        assertTrue(zoneIds.stream().noneMatch(zoneId -> zoneId.equals(ZoneId.of("Etc/Greenwich"))));
    }

    @Test
    public void zonesChangedClockShiftRulesTest2(){
        List<ZoneId> zoneIds = Tasks.zonesChangedClockShiftRules(ZONE_IDS);
        assertNotNull(zoneIds);
        assertTrue(zoneIds.size() > 0);
        assertTrue(zoneIds.stream().anyMatch(zoneId -> zoneId.equals(ZoneId.of("Africa/Addis_Ababa"))));
    }
}
