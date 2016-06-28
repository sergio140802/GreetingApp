package biz.adras.timebasedgreetingapp.service;


import java.util.Date;
import java.util.Locale;

public interface MessagesService {

    String getByCurrentDateAndSystemLocale();

    String getByDateAndLocale(Date date, Locale locale);

}
