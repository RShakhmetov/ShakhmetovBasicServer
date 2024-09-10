package net.dunice.BasicServer.controllers;

import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/v1/todo")
    public ResponseEntity read() {
        return new ResponseEntity(toDoService.getAllToDo(), HttpStatus.OK);
    }

    @PostMapping("/v1/todo")
    public @ResponseBody ResponseEntity<String> addToDo(ToDo toDo) {
        return toDoService.addToDo(toDo);
    }
}
