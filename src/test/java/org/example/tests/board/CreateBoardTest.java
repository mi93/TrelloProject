package org.example.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.board.DeleteBoardRequest;
import org.junit.jupiter.api.Test;

class CreateBoardTest {
    private final String boardName = "Just a board";

    @Test
    void createBoardTest() {

        // CREATE A BOARD
        final Response response = CreateBoardRequest.createBoardRequest(boardName);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        String boardId = json.getString("id");

        // DELETE A BOARD
        Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);

    }
}
