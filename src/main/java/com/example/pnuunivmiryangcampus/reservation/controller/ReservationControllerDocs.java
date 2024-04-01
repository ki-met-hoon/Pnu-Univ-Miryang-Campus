package com.example.pnuunivmiryangcampus.reservation.controller;

import com.example.pnuunivmiryangcampus.config.security.CustomUserDetails;
import com.example.pnuunivmiryangcampus.reservation.dto.response.ReservationRenewalResponse;
import com.example.pnuunivmiryangcampus.reservation.dto.response.ReservationResponse;
import com.example.pnuunivmiryangcampus.support.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.AUTHORIZATION;

@Tag(name = "도서관 예약")
public interface ReservationControllerDocs {

    @Operation(summary = "도서관 예약 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "도서관 예약 생성", headers = @Header(name = AUTHORIZATION, description = "Id Token")),
            @ApiResponse(responseCode = "500", description = "Url Path 내 seatId 유효성 오류 또는 서버 오류",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "ServerError", value = "{\"message\": \"서버에 알 수 없는 문제가 발생했습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "400", description = "예약된 좌석 존재",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "BadRequest", value = "{\"message\": \"이미 예약된 좌석이 존재합니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 좌석",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "NotFound", value = "{\"message\": \"해당 좌석은 존재하지 않습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "403", description = "인증되지 않은 사용자", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<Void> reservation(@PathVariable Long seatId, @AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "도서관 예약 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도서관 예약 조회", headers = @Header(name = AUTHORIZATION, description = "Id Token")
                    ),
            @ApiResponse(responseCode = "204", description = "도서관 예약이 존재하지 않음", headers = @Header(name = AUTHORIZATION, description = "Id Token"), content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 좌석",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "NotFound", value = "{\"message\": \"해당 좌석은 존재하지 않습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "403", description = "인증되지 않은 사용자", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<ReservationResponse> getReservation(@AuthenticationPrincipal CustomUserDetails customUserDetails);

    @Operation(summary = "도서관 예약 연장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도서관 예약 연장", headers = @Header(name = AUTHORIZATION, description = "Id Token"),
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                            @ExampleObject(name = "Ok", value = "{\"endAt\": \"2024-04-01T04:43:13.992Z\", \"renewalCount\": 0}")
                    }
            )),
            @ApiResponse(responseCode = "500", description = "Url Path 내 reservationId 유효성 오류 또는 서버 오류",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "ServerError", value = "{\"message\": \"서버에 알 수 없는 문제가 발생했습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 예약",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "NotFound", value = "{\"message\": \"예약된 좌석을 찾을 수 없습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "400", description = "연장 가능 시간은 종료 시간의 30분 전",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "BadRequest", value = "{\"message\": \"연장 가능 시간은 종료 시간의 30분 전입니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "404", description = "연장 가능 횟수는 최대 4회",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                            @ExampleObject(name = "NotFound", value = "{\"message\": \"연장 가능 횟수를 초과했습니다.\"}")
                    }
            )),
            @ApiResponse(responseCode = "403", description = "인증되지 않은 사용자", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<ReservationRenewalResponse> reservationRenewal(@PathVariable Long reservationId);

    @Operation(summary = "도서관 예약 퇴실")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "도서관 예약 퇴실", headers = @Header(name = AUTHORIZATION, description = "Id Token"), content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Url Path 내 reservationId 유효성 오류 또는 서버 오류",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "ServerError", value = "{\"message\": \"서버에 알 수 없는 문제가 발생했습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 좌석",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "NotFound", value = "{\"message\": \"해당 좌석은 존재하지 않습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 예약",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(name = "NotFound", value = "{\"message\": \"예약된 좌석을 찾을 수 없습니다.\"}")
                            }
                    )),
            @ApiResponse(responseCode = "403", description = "인증되지 않은 사용자", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<Void> reservation(@PathVariable Long reservationId);
}
