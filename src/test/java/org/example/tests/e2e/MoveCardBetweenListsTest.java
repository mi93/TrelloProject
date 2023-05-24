package org.example.tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
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
    private static String boardId;
    private static String firstListId;
    private static String secondListId;

    @Test
    @Order(1)
    void createBoardTest() {

        final Response createBoardResponse = CreateBoardRequest.createBoardRequest(boardName);
        Assertions.assertThat(createBoardResponse.statusCode()).isEqualTo(200);

        JsonPath boardJson = createBoardResponse.jsonPath();
        Assertions.assertThat(boardJson.getString("name")).isEqualTo(boardName);
        boardId = boardJson.getString("id");
    }

    @Test
    @Order(2)
    void CreateFirstListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstListName);
        queryParams.put("idBoard", boardId);

        final Response createFirstListResponse = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(createFirstListResponse.statusCode()).isEqualTo(200);

        JsonPath firstListJson = createFirstListResponse.jsonPath();
        Assertions.assertThat(firstListJson.getString("name")).isEqualTo(firstListName);
        firstListId = firstListJson.getString("id");
    }

    @Test
    @Order(3)
    void CreateSecondListTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstListName);
        queryParams.put("idBoard", boardId);

        final Response createSecondListResponse = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(createSecondListResponse.statusCode()).isEqualTo(200);

        JsonPath secondListJson = createSecondListResponse.jsonPath();
        Assertions.assertThat(secondListJson.getString("name")).isEqualTo(firstListName);
        secondListId = secondListJson.getString("id");
    }
}
