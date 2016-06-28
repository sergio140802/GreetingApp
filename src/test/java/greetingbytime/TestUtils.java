package greetingbytime;


import java.util.Calendar;
import java.util.Date;

public class TestUtils {

    public static Date createAndSetHourAndMinutes(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
}
