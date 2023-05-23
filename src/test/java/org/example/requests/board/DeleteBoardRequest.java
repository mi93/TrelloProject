package org.example.requests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.properties.TrelloProperties;
import org.example.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {

    public static Response deleteBoardRequest(String boardId) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloProperties.getKey())
                .queryParam("token", TrelloProperties.getToken())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}
