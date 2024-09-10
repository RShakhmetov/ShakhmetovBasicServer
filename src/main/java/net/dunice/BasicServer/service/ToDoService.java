package net.dunice.BasicServer.service;

import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.models.ToDo;

public interface ToDoService {
    ToDo addToDo(CreateTodoDto createTodoDto);

    GetNewsDto<ToDo> getAllToDo(int page, int perPage, boolean status);

    ToDo getToDo(Long id);

    boolean delete(Long id);
}
