package net.dunice.BasicServer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.DTOs.ChangeStatusTodoDto;
import net.dunice.BasicServer.DTOs.ChangeTextTodoDto;
import net.dunice.BasicServer.DTOs.CreateTodoDto;
import net.dunice.BasicServer.DTOs.GetNewsDto;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.repositories.ToDoRepository;
import net.dunice.BasicServer.response.BaseSuccessResponse;
import net.dunice.BasicServer.response.CustomSuccessResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepository toDoRepo;

    public CustomSuccessResponse<ToDo> createToDo(CreateTodoDto createTodoDto) {
        ToDo toDo = new ToDo();
        toDo.setText(createTodoDto.getText());
        return new CustomSuccessResponse<>(HttpStatus.OK.value(), toDoRepo.save(toDo));
    }

    public CustomSuccessResponse<GetNewsDto<ToDo>> getAllToDo(Integer page, Integer perPage, Boolean status) {
        List<ToDo> list = toDoRepo.findAll(PageRequest.of(page, perPage)).toList();
        List<ToDo> listReadyOrNotready;
        if (status != null) {
            listReadyOrNotready = findAllByStatus(status, list);
        } else {
            listReadyOrNotready = list;
        }
        return  new CustomSuccessResponse<>(new GetNewsDto<>(listReadyOrNotready,
                list.size(),  list.stream().filter(ToDo::isStatus).count(),
                list.stream().filter(x-> !x.isStatus()).count()));
    }

    @Transactional
    public BaseSuccessResponse patch(ChangeStatusTodoDto statusTodoDto) {
        List<ToDo> list = toDoRepo.findAll();
        list.forEach(todo -> todo.setStatus(statusTodoDto.getStatus()));
        return new BaseSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse deleteAllReady() {
        toDoRepo.deleteAllByStatusTrue();
        return new BaseSuccessResponse();
    }

    private List<ToDo> findAllByStatus (Boolean status, List<ToDo> list) {
        return list.stream().filter(x-> x.isStatus() == status).toList();
    }

    @Transactional
    public BaseSuccessResponse delete(Long id) {
        toDoRepo.deleteById(id);
        return new BaseSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse patchStatus(Long id, ChangeStatusTodoDto changeStatusTodoDto) {
        toDoRepo.findById(id).ifPresent(todo -> todo.setStatus(changeStatusTodoDto.getStatus()));
        return new BaseSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse patchText(Long id, ChangeTextTodoDto changeTextTodoDto) {
        toDoRepo.findById(id).ifPresent(toDo -> toDo.setText(changeTextTodoDto.getText()));
        return new BaseSuccessResponse();
    }
}
