package biz.adras.timebasedgreetingapp.service.impl;


import biz.adras.timebasedgreetingapp.commons.localization.messages.Messages;
import biz.adras.timebasedgreetingapp.service.MessagesService;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GreetingMessagesService implements MessagesService {

    private final Logger logger = Logger.getLogger(getClass());

    private Messages messages;

    public GreetingMessagesService(Messages messages) {
        this.messages = messages;
    }

    public String getByCurrentDateAndSystemLocale() {
        return getByDateAndLocale(new Date(), Locale.getDefault());
    }

    public String getByDateAndLocale(Date date, Locale locale) {
        int hour = getHourByDate(date);
        logger.debug("getByDateAndLocale: hour value -> " + hour);

        if (hourInBetween(hour, 6, 9)) {
            return messages.getMessage("good.morning", locale);
        } else if (hourInBetween(hour, 9, 19)) {
            return messages.getMessage("good.day", locale);
        } else if (hourInBetween(hour, 19, 23)) {
            return messages.getMessage("good.evening", locale);
        } else {
            return messages.getMessage("good.night", locale);
        }
    }

    private boolean hourInBetween(int hour, int hourAfter, int hourBefore) {
        return hour >= hourAfter && hour < hourBefore;
    }

    private int getHourByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}