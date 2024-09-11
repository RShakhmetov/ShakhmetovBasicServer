package net.dunice.BasicServer.service;

import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.repositories.ToDoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepository toDoRepo;

    public ToDo createToDo(CreateTodoDto createTodoDto) {
        ToDo toDo = new ToDo();
        toDo.setText(createTodoDto.getText());
        return toDoRepo.save(toDo);
    }

    public GetNewsDto<ToDo> getAllToDo(int page, int perPage, boolean status) {
        Page<ToDo> list = toDoRepo.findAll(PageRequest.of(page, perPage));
        GetNewsDto<ToDo> getNewsDto;
        getNewsDto = new GetNewsDto<>(list.stream().toList(), list.getTotalElements(), list.stream().filter(ToDo::isStatus).count(),
                list.stream().filter(x -> !x.isStatus()).count());
        return getNewsDto;
    }

    public ToDo getToDo(Long id) {
        return toDoRepo.findById(id).get();
    }

    public boolean delete(Long id) {
        return false;
    }
}
