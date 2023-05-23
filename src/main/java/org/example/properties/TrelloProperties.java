package org.example.properties;

import java.util.ResourceBundle;

public class TrelloProperties {

    private static final String TOKEN = "trello.token";
    private static final String KEY = "trello.key";

    private static String getProperty(String key) {
        return ResourceBundle.getBundle("trello").getString(key);
    }

    public static String getToken() {
        return getProperty(TOKEN);
    }

    public static String getKey() {
        return getProperty(KEY);
    }
}
