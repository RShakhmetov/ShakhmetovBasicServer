package net.dunice.BasicServer.controllers;

import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.DTOs.ChangeStatusTodoDto;
import net.dunice.BasicServer.DTOs.ChangeTextTodoDto;
import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class ToDoController {

    private final TodoService toDoService;

    @GetMapping
    public ResponseEntity<GetNewsDto<ToDo>> getPaginated(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer perPage,
                                                         @RequestParam(required = false) Boolean status) {
        return ResponseEntity.ok(toDoService.getAllToDo(page, perPage, status).getData());
    }

    @PostMapping
    public ResponseEntity<ToDo> create(@RequestBody CreateTodoDto createTodoDto) {
        return ResponseEntity.ok(toDoService.createToDo(createTodoDto).getData());
    }

    @DeleteMapping
    public ResponseEntity deleteAllReady() {
        return ResponseEntity.ok(toDoService.deleteAllReady());
    }

    @PatchMapping
    public ResponseEntity patch (@RequestBody ChangeStatusTodoDto statusTodoDto) {
        return ResponseEntity.ok(toDoService.patch(statusTodoDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(toDoService.delete(id));
    }

    @PatchMapping(value = "status/{id}")
    public ResponseEntity patchStatus (@PathVariable("id") Long id,
                                       @RequestBody ChangeStatusTodoDto statusTodoDto) {
                return ResponseEntity.ok(toDoService.patchStatus(id, statusTodoDto));
    }

    @PatchMapping(value = "text/{id}")
    public ResponseEntity patchText (@PathVariable("id") Long id,
                                     @RequestBody ChangeTextTodoDto changeTextTodoDto) {
        return ResponseEntity.ok(toDoService.patchText(id,changeTextTodoDto));
    }
}
