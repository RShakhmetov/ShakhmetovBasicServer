package net.dunice.BasicServer.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.DTOs.ChangeStatusTodoDto;
import net.dunice.BasicServer.DTOs.ChangeTextTodoDto;
import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.handling.ValidationConstants;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.response.BaseSuccessResponse;
import net.dunice.BasicServer.response.CustomSuccessResponse;
import net.dunice.BasicServer.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class ToDoController {

    private final TodoService toDoService;

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<GetNewsDto<ToDo>>> getPaginated(
            @RequestParam(defaultValue = "1")
            @Positive(message = ValidationConstants.TASKS_PAGE_GREATER_OR_EQUAL_1)
            @Max(value = 100, message = ValidationConstants.TASKS_PER_PAGE_LESS_OR_EQUAL_100)
            Integer page,
            @RequestParam(defaultValue = "10")
            @Positive(message = ValidationConstants.TASKS_PER_PAGE_GREATER_OR_EQUAL_1)
            @Max(value = 100, message = ValidationConstants.TASKS_PER_PAGE_LESS_OR_EQUAL_100)
            Integer perPage,
            @RequestParam(required = false) Boolean status
    ) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(1, toDoService.getAllToDo(page, perPage, status)));
    }

    @PostMapping
    public ResponseEntity<CustomSuccessResponse<ToDo>> create(@Valid @RequestBody CreateTodoDto createTodoDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(toDoService.createToDo(createTodoDto)));
    }

    @DeleteMapping
    public ResponseEntity<BaseSuccessResponse> deleteAllReady() {
        return ResponseEntity.ok(toDoService.deleteAllReady());
    }

    @PatchMapping
    public ResponseEntity<BaseSuccessResponse> patch(@RequestBody
                                                     @Valid
                                                     @NotNull(message =
                                                             ValidationConstants.HTTP_MESSAGE_NOT_READABLE_EXCEPTION)
                                                     ChangeStatusTodoDto statusTodoDto) {
        return ResponseEntity.ok(toDoService.patch(statusTodoDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BaseSuccessResponse> delete(@PathVariable("id")
                                                      @Min(value = 1,
                                                           message =
                                                           ValidationConstants.ID_MUST_BE_POSITIVE) Long id) {
        return ResponseEntity.ok(toDoService.delete(id));
    }

    @PatchMapping(value = "status/{id}")
    public ResponseEntity<BaseSuccessResponse> patchStatus(@PathVariable("id") Long id,
                                                           @RequestBody
                                                           @Valid
                                                           ChangeStatusTodoDto statusTodoDto) {
        return ResponseEntity.ok(toDoService.patchStatus(id, statusTodoDto));
    }

    @PatchMapping(value = "text/{id}")
    public ResponseEntity<BaseSuccessResponse> patchText(@PathVariable("id") Long id,
                                                         @Valid
                                                         @RequestBody
                                                         ChangeTextTodoDto changeTextTodoDto) {
        return ResponseEntity.ok(toDoService.patchText(id, changeTextTodoDto));
    }
}
