package org.example.requests.card;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {

    public static Response updateCardRequest(String listId, String cardId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .queryParam("idList", listId)
                .when()
                .put(TrelloUrl.getCardUrl(cardId))
                .then()
                .extract()
                .response();
    }
}
