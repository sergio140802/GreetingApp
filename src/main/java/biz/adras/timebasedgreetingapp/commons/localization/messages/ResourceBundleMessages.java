package biz.adras.timebasedgreetingapp.commons.localization.messages;


import biz.adras.timebasedgreetingapp.utils.EncodingControl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ResourceBundleMessages implements Messages
{
    private final Logger logger = Logger.getLogger(getClass());

    private ConcurrentMap<Locale, Map<String, String>> bundles;

    public ResourceBundleMessages() {
        this.bundles = new ConcurrentHashMap<>();
    }

    @Override
    public String getMessage(String messageCode, Locale locale) {
        Map<String, String> bundle = bundles.get(locale);
        if (bundle == null) {
            if (logger.isEnabledFor(Level.INFO)) {
                logger.info("no bundle allocated for code: " + messageCode + " and locale: " + locale);
                logger.info("fetching code from ResourceBundle...");
            }
            bundle = getMessages(locale);
            bundles.putIfAbsent(locale, bundle);
        }
        return bundle.get(messageCode);
    }

    private Map<String, String> getMessages(Locale locale) {
        ResourceBundle bundle = tryFetchResourceBundle(locale);
        if (bundle != null) {
            return transformBundlePropertiesToMap(bundle);
        }
        return Collections.emptyMap();
    }

    private Map<String, String> transformBundlePropertiesToMap(ResourceBundle bundle) {
        Enumeration<String> keys = bundle.getKeys();
        Map<String, String> properties = new HashMap<>();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = bundle.getString(key);
            properties.put(key, value);
        }
        return properties;
    }

    private ResourceBundle tryFetchResourceBundle(Locale locale) {
        try {
            return ResourceBundle.getBundle("localization/LocalizationBundle", locale, new EncodingControl("UTF-8"));
        } catch (MissingResourceException ex) {
            logger.debug("No resource bundle found", ex);
            return null;
        }
    }
}