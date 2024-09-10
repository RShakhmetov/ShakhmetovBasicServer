package net.dunice.BasicServer.service;

import lombok.RequiredArgsConstructor;
import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.repositories.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MethodsService implements ToDoService {
    private final ToDoRepository toDoRepo;

    @Override
    public ResponseEntity<String> addToDo(ToDo todo) {
        try {
            toDoRepo.save(todo);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public List<ToDo> getAllToDo() {
        return List.of((ToDo) toDoRepo.findAll());
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
