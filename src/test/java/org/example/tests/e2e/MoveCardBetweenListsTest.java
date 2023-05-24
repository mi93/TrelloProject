package org.example.tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.board.DeleteBoardRequest;
import org.example.requests.card.CreateCardRequest;
import org.example.requests.card.UpdateCardRequest;
import org.example.requests.list.CreateListRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MoveCardBetweenListsTest {

    private final String boardName = "Just a board";
    private final String firstListName = "First list";
    private final String secondListName = "Second list";
    private static String boardId;
    private static String firstListId;
    private static String secondListId;
    private static String cardId;

    @Test
    @Order(1)
    void createBoardTest() {

        final Response updateBoardResponse = CreateBoardRequest.createBoardRequest(boardName);
        Assertions.assertThat(updateBoardResponse.statusCode()).isEqualTo(200);

        JsonPath boardJson = updateBoardResponse.jsonPath();
        Assertions.assertThat(boardJson.getString("name")).isEqualTo(boardName);
        boardId = boardJson.getString("id");
    }

    @Test
    @Order(2)
    void createFirstListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstListName);
        queryParams.put("idBoard", boardId);

        final Response firstListResponse = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(firstListResponse.statusCode()).isEqualTo(200);

        JsonPath firstListJson = firstListResponse.jsonPath();
        Assertions.assertThat(firstListJson.getString("name")).isEqualTo(firstListName);
        firstListId = firstListJson.getString("id");
    }

    @Test
    @Order(3)
    void createSecondListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", secondListName);
        queryParams.put("idBoard", boardId);

        final Response secondListResponse = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(secondListResponse.statusCode()).isEqualTo(200);

        JsonPath secondListJson = secondListResponse.jsonPath();
        Assertions.assertThat(secondListJson.getString("name")).isEqualTo(secondListName);
        secondListId = secondListJson.getString("id");
    }

    @Test
    @Order(4)
    void createCardOnFirstListTest() {

        Map <String, String> queryParams = new HashMap<>();
        final String cardName = "Quality is not an act, it is a habit.";
        queryParams.put("idList", firstListId);
        queryParams.put("name", cardName);

        final Response cardResponse = CreateCardRequest.createCardRequest(queryParams);
        Assertions.assertThat(cardResponse.statusCode()).isEqualTo(200);

        JsonPath cardJson = cardResponse.jsonPath();
        Assertions.assertThat(cardJson.getString("name")).isEqualTo(cardName);
        cardId = cardJson.getString("id");
    }

    @Test
    @Order(5)
    void moveCardToSecondList() {

        final Response updateCardResponse = UpdateCardRequest.updateCardRequest(secondListId, cardId);
        Assertions.assertThat(updateCardResponse.statusCode()).isEqualTo(200);

        JsonPath updateCardRJson = updateCardResponse.jsonPath();
        Assertions.assertThat(updateCardRJson.getString("idList")).isEqualTo(secondListId);

    }

    @Test
    @Order(6)
    void deleteBoardTest() {

        final Response deleteBoardResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(deleteBoardResponse.statusCode()).isEqualTo(200);
    }
}
