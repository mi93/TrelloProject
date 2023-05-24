package org.example.url;

public class TrelloUrl {

    private TrelloUrl() {}

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARDS = "boards";
    private static final String LISTS = "lists";
    private static final String CARDS = "cards";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBoardsUrl() {
        return getBaseUrl() + "/" + BOARDS;
    }

    public static String getBoardUrl(String boardId) {
        return getBoardsUrl() + "/" + boardId;
    }

    public static String getListsUrl() {
        return getBaseUrl() + "/" + LISTS;
    }

    public static String getCardsUrl() {
        return getBaseUrl() + "/" + CARDS;
    }
}
