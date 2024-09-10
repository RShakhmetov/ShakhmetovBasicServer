package net.dunice.BasicServer.service;

import net.dunice.BasicServer.models.ToDo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoService {
    ResponseEntity<String> addToDo(ToDo user);

    List<ToDo> getAllToDo();

    ToDo getToDo(Long id);

    boolean delete(Long id);
}
