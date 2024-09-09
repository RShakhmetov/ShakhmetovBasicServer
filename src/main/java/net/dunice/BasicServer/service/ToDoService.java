package net.dunice.BasicServer.service;

import net.dunice.BasicServer.models.ToDo;

import java.util.List;

public interface ToDoService {
    ToDo create (ToDo user);

    List<ToDo> readAll();

    ToDo read(Long id);

    boolean delete(Long id);
}
