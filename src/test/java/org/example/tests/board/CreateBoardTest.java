package org.example.tests.board;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.properties.TrelloProperties;
import org.example.url.TrelloUrl;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class CreateBoardTest {
    private final String boardName = "Just a board";
    private String boardId;

    @Test
    void createBoardTest() {

        // CREATE A BOARD
        final Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloProperties.getKey())
                .queryParam("token", TrelloProperties.getToken())
                .queryParam("name", boardName)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        boardId = json.getString("id");

        // DELETE BOARD

        Response deleteResponse = given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloProperties.getKey())
                .queryParam("token", TrelloProperties.getToken())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();

        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);


    }


}
