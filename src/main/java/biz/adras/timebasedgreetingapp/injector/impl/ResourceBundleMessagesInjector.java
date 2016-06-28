package biz.adras.timebasedgreetingapp.injector.impl;


import biz.adras.timebasedgreetingapp.commons.localization.messages.factory.MessagesFactory;
import biz.adras.timebasedgreetingapp.commons.localization.messages.factory.MessagesType;
import biz.adras.timebasedgreetingapp.injector.MessagesInjector;
import biz.adras.timebasedgreetingapp.service.impl.GreetingMessagesService;
import biz.adras.timebasedgreetingapp.service.MessagesService;

public class ResourceBundleMessagesInjector implements MessagesInjector {

    private MessagesFactory messagesFactory;

    public ResourceBundleMessagesInjector() {
        this.messagesFactory = new MessagesFactory();
    }

    @Override
    public MessagesService getMessagesService() {
        return new GreetingMessagesService(messagesFactory.getMessages(MessagesType.RESOURCE_BUNDLE));
    }
}