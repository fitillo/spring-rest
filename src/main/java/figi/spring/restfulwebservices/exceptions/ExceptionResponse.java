package figi.spring.restfulwebservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;
}
