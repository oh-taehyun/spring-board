package org.example.springboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ControllerAdvice는 Spring에서 제공하는 어노테이션
 * @Controller나 @RestController에서 발생하는 예외를 한 곳에서 관리하고 처리할 수 있게 하는 어노테이션
 * 설정을 통해 범위 지정이 가능하며, Default 값으로 모든 Controller에 대해 예외 처리를 관리함
 * 예외 발생 시 json형태로 결과를 반환하기 위해서는 @RestControllerAdvice 를 사용하면 됨
 * @ExceptionHandler는 예외 처리 상황이 발생하면 해당 Handler로 처리하겠다고 명시하는 어노테이션
 * 어노테이션 뒤에 괄호를 붙여 어떤 ExceptionClass를 처리할지 설정할 수 있음
 * Exception.class는 최상위 클래스로 하위 세부 예외 처리 클래스로 설정한 핸들러가 존재하면,
 * 그 핸들러가 우선처리하게 되며, 처리 되지 못하는 예외 처리에 대해 ExceptionClass에서 핸들링함
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleUnauthorrizedException(UnauthorizedException ex, Model model){
        model.addAttribute("errorMessage",ex.getMessage());
        return "error/403";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNoFoundException(ResourceNotFoundException ex, Model model){
        model.addAttribute("errorMessage",ex.getMessage());
        return "error/404";
    }
}
