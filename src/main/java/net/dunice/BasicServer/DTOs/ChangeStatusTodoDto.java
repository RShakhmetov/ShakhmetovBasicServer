package net.dunice.BasicServer.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import net.dunice.BasicServer.handling.ValidationConstants;

@Getter
@Setter
public class ChangeStatusTodoDto {
    @NotNull(message = ValidationConstants.TODO_STATUS_NOT_NULL)
    Boolean status;
}
