package biz.adras.timebasedgreetingapp.commons.localization.messages.factory;


import biz.adras.timebasedgreetingapp.commons.localization.messages.Messages;
import biz.adras.timebasedgreetingapp.commons.localization.messages.ResourceBundleMessages;

public class MessagesFactory
{
    public Messages getMessages(MessagesType type) {
        if (MessagesType.RESOURCE_BUNDLE.equals(type)) {
            return new ResourceBundleMessages();
        }
        return null;
    }
}