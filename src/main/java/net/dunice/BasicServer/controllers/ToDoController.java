package net.dunice.BasicServer.controllers;

import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.response.CustomSuccessResponse;
import net.dunice.BasicServer.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<GetNewsDto<ToDo>>> getPaginated(@RequestParam int page,
                                                                                @RequestParam int perPage,
                                                                                @RequestParam boolean status) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(1, toDoService.getAllToDo(page, perPage, status)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateTodoDto createTodoDto) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(HttpStatus.OK.value(), toDoService.addToDo(createTodoDto)));
    }
}
