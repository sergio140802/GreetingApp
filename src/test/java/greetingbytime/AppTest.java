package greetingbytime;

import biz.adras.timebasedgreetingapp.commons.localization.messages.Messages;
import biz.adras.timebasedgreetingapp.commons.localization.messages.factory.MessagesFactory;
import biz.adras.timebasedgreetingapp.commons.localization.messages.factory.MessagesType;
import biz.adras.timebasedgreetingapp.injector.impl.ResourceBundleMessagesInjector;
import biz.adras.timebasedgreetingapp.service.impl.GreetingMessagesService;
import biz.adras.timebasedgreetingapp.service.MessagesService;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class AppTest
{
    MessagesService messagesService;
    private Locale locale = Locale.US;
    private Date date;

    @Before
    public void initialize() {
        messagesService = new ResourceBundleMessagesInjector().getMessagesService();
        date = new Date();
    }

    @Test
    public void verifyMethodCall() {
        MessagesService messagesService = mock(GreetingMessagesService.class);
        messagesService.getByDateAndLocale(date, locale);
        verify(messagesService).getByDateAndLocale(date, locale);
    }

    @Test
    public void verifyMessageExist() {
        Messages messages = new MessagesFactory().getMessages(MessagesType.RESOURCE_BUNDLE);
        String expectedMessage = messages.getMessage("good.morning", locale);
        assertNotNull(expectedMessage);
    }

    @Test
    public void verifyReturnedValue() {
        Date date = TestUtils.createAndSetHourAndMinutes(8, 30);
        Messages messages = new MessagesFactory().getMessages(MessagesType.RESOURCE_BUNDLE);
        String expectedMessage = messages.getMessage("good.morning", locale);
        String actualMessage = messagesService.getByDateAndLocale(date, locale);
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void verifyUnexpectedReturnedValue() {
        Messages messages = new MessagesFactory().getMessages(MessagesType.RESOURCE_BUNDLE);
        String unexpectedMessage = messages.getMessage("good.morning", locale);
        String actualMessage = messagesService.getByDateAndLocale(date, locale);
        assertNotEquals(actualMessage, unexpectedMessage);
    }
}