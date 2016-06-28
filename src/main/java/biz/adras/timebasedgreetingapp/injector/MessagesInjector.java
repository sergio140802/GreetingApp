package biz.adras.timebasedgreetingapp.injector;


import biz.adras.timebasedgreetingapp.service.MessagesService;

public interface MessagesInjector {

    MessagesService getMessagesService();

}