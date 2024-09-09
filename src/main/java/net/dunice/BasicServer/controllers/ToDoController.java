package net.dunice.BasicServer.controllers;

import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class ToDoController {
    ToDoService service;
    @GetMapping(value = "/v1/todo")
    public ResponseEntity<List<ToDo>> read() {
        final List<ToDo> clients = service.readAll();
        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
