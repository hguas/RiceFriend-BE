package com.hanghae.mini2.riceFriend.handler;

import com.hanghae.mini2.riceFriend.handler.ex.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("U001", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordContainsEmailException.class)
    public ResponseEntity<ErrorResponse> handlePasswordContainsEmailException(PasswordContainsEmailException e) {
        return new ResponseEntity<>(new ErrorResponse("U002", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
        return new ResponseEntity<>(new ErrorResponse("U003", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotCollectException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotCollectException(PasswordNotCollectException e) {
        return new ResponseEntity<>(new ErrorResponse("U004", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginUserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoginUserNotFoundException(LoginUserNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("U005", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MeetingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMeetingNotFoundException(MeetingNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("M001", "해당 모임이 존재하지 않습니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLocationNotFoundException(LocationNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("M002", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("M003", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalMeetingUpdateUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalMeetingUpdateUserException(IllegalMeetingUpdateUserException e) {
        return new ResponseEntity<>(new ErrorResponse("M004", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalMeetingDeleteUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalMeetingDeleteUserException(IllegalMeetingDeleteUserException e) {
        return new ResponseEntity<>(new ErrorResponse("M005", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateMeetingParticipateException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateMeetingParticipateException(DuplicateMeetingParticipateException e) {
        return new ResponseEntity<>(new ErrorResponse("M006", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MeetingParticipationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMeetingParticipationNotFoundException(MeetingParticipationNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("M007", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFoundException(CommentNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("C001", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalCommentUpdateUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalCommentUpdateUserException(IllegalCommentUpdateUserException e) {
        return new ResponseEntity<>(new ErrorResponse("C002", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalCommentDeleteUserException.class)
    public ResponseEntity<ErrorResponse> handleIllegalCommentDeleteUserException(IllegalCommentDeleteUserException e) {
        return new ResponseEntity<>(new ErrorResponse("C003", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
                , HttpStatus.BAD_REQUEST);
    }

}
