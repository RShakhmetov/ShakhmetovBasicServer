package net.dunice.BasicServer.handling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private ErrorCodes errorCodes;

}
