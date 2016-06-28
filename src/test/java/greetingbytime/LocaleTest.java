package greetingbytime;


import biz.adras.timebasedgreetingapp.injector.impl.ResourceBundleMessagesInjector;
import biz.adras.timebasedgreetingapp.service.MessagesService;
import org.junit.*;

import java.util.Date;
import java.util.Locale;

public class LocaleTest {

    private Locale defaultLocale;
    private MessagesService messagesService;

    @Before
    public void setUp() {
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ITALY);
        messagesService = new ResourceBundleMessagesInjector().getMessagesService();
    }

    @Test
    public void testDefaultLocaleChange() {
        Date date = new Date();
        String message = messagesService.getByDateAndLocale(date, Locale.getDefault());
        //means that default messages will be picked up
        String defaultMessage = messagesService.getByDateAndLocale(date, Locale.ROOT);
        Assert.assertEquals(message, defaultMessage);
    }

    @Test
    public void testExistingAndDefaultLocale() {
        Locale ru = new Locale("ru", "RU");
        Date date = new Date();
        String ruMessage = messagesService.getByDateAndLocale(date, ru);
        String defaultMessage = messagesService.getByDateAndLocale(date, Locale.getDefault());
        Assert.assertNotEquals(ruMessage, defaultMessage);
    }

    @After
    public void after() {
        Locale.setDefault(defaultLocale);
    }
}