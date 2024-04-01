package com.example.pnuunivmiryangcampus.librarySeat.controller;

import com.example.pnuunivmiryangcampus.librarySeat.dto.response.LibrarySeatResponse;
import com.example.pnuunivmiryangcampus.support.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "도서관 좌석")
public interface LibrarySeatControllerDocs {

    @Operation(summary = "도서관 좌석 조회", description = "사용가능인 좌석만 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도서관 좌석 조회"),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "ServerError", value = "{\"message\": \"서버에 알 수 없는 문제가 발생했습니다.\"}")
                            }
                    )),
    })
    ResponseEntity<List<LibrarySeatResponse>> librarySeats();
}
