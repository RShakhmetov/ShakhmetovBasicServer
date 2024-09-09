package net.dunice.BasicServer.service;

import net.dunice.BasicServer.models.ToDo;
import net.dunice.BasicServer.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MethodsService implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepo;


    @Override
    public ToDo create(ToDo user) {
        return null;
    }

    @Override
    public List<ToDo> readAll() {
        return List.of((ToDo) toDoRepo.findAll());
    }

    @Override
    public ToDo read(Long id) {
        return toDoRepo.findById(id).get();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
