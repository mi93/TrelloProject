package org.example.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.board.DeleteBoardRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CreateBoardWithParamsTest {

    @ParameterizedTest(name = "Board name: {0}")
    @DisplayName(" Create board with valid board name")
    @MethodSource("boardNameData")
    void createBoardTest(String boardName) {

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

    private static Stream<Arguments> boardNameData() {
        return Stream.of(
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$")
        );
    }
}
