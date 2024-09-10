package net.dunice.BasicServer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseSuccessResponse {
    private int statusCode;

    private boolean success = true;

    private List<Integer> codes;

    public BaseSuccessResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public BaseSuccessResponse(Integer statusCode, List<Integer> codes) {
        this.statusCode = statusCode;
        this.codes = codes;
    }
}