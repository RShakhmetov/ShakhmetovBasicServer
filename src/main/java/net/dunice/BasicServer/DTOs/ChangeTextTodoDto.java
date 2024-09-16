package net.dunice.BasicServer.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dunice.BasicServer.handling.ValidationConstants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTextTodoDto {
    @NotNull(message = ValidationConstants.TODO_TEXT_NOT_NULL)
    @Size(min = 3, max = 160, message = ValidationConstants.TODO_TEXT_SIZE_NOT_VALID)
    String text;
}
