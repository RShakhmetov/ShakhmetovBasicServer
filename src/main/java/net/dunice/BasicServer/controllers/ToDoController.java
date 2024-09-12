package net.dunice.BasicServer.controllers;

import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.DTOs.ChangeStatusTodoDto;
import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.response.BaseSuccessResponse;
import net.dunice.BasicServer.response.CustomSuccessResponse;
import net.dunice.BasicServer.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class ToDoController {

    private final TodoService toDoService;

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<GetNewsDto<ToDo>>> getPaginated(@RequestParam(defaultValue = "0") Integer page,
                                                                                @RequestParam(defaultValue = "10") Integer perPage,
                                                                                @RequestParam(required = false) Boolean status) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(1, toDoService.getAllToDo(page, perPage, status)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateTodoDto createTodoDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(HttpStatus.OK.value(), toDoService.createToDo(createTodoDto)));
    }

    @DeleteMapping
    public ResponseEntity deleteAllReady() {
        return ResponseEntity.ok(toDoService.deleteAllReady());
    }

    @PatchMapping
    public ResponseEntity patch (@RequestParam ChangeStatusTodoDto statusTodoDto) {
        return ResponseEntity.ok(toDoService.patch(statusTodoDto));
    }
}
