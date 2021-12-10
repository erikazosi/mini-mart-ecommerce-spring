package edu.miu.waa.minimartecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseMessage {
    private String message;
    private HttpStatus httpStatus;

    public ResponseMessage(String message){this.message = message;}
}
