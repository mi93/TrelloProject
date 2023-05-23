package org.example;

public class TrelloUrl {

    private TrelloUrl() {}

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARDS = "boards";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBoardsUrl() {
        return getBaseUrl() + "/" + BOARDS;
    }

    public static String getBoardUrl(String boardId) {
        return getBoardsUrl() + "/" + boardId;
    }
}
