package org.example.requests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.properties.TrelloProperties;
import org.example.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {

    public static Response createBoardRequest(String boardName) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloProperties.getKey())
                .queryParam("token", TrelloProperties.getToken())
                .queryParam("name", boardName)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}
