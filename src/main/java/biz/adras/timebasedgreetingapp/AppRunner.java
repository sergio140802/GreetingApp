package biz.adras.timebasedgreetingapp;

import biz.adras.timebasedgreetingapp.injector.impl.ResourceBundleMessagesInjector;
import biz.adras.timebasedgreetingapp.service.MessagesService;


public class AppRunner
{
    public static void main( String[] args )
    {
        MessagesService service = new ResourceBundleMessagesInjector().getMessagesService();
        System.out.println(service.getByCurrentDateAndSystemLocale());
    }
}