package edu.miu.waa.minimartecommerce.exceptionHandling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(final HttpStatus status, final String message, final String error) {
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }
}

