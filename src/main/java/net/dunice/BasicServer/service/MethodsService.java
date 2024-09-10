package net.dunice.BasicServer.service;

import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.repositories.ToDoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MethodsService implements ToDoService {
    private final ToDoRepository toDoRepo;

    @Override
    public ToDo addToDo(CreateTodoDto createTodoDto) {
        ToDo toDo = new ToDo();
        toDo.setText(createTodoDto.getText());
        return toDoRepo.save(toDo);
    }

    @Override
    public GetNewsDto<ToDo> getAllToDo(int page, int perPage, boolean status) {
        List<ToDo> spisok = toDoRepo.findAll(PageRequest.of(page, perPage)).toList();
        GetNewsDto<ToDo> getNewsDto = new GetNewsDto<>(spisok, spisok.size(), spisok.stream().filter(ToDo::isStatus).count(),
                spisok.stream().filter(x -> !x.isStatus()).count());
        return getNewsDto;
    }

    @Override
    public ToDo getToDo(Long id) {
        return toDoRepo.findById(id).get();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
