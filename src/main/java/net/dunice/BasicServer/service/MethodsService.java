package net.dunice.BasicServer.service;

import net.dunice.BasicServer.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service implements ToDoService{
    @Override
    public void create(UserEntity user) {

    }

    @Override
    public List<UserEntity> readAll() {
        return List.of();
    }

    @Override
    public UserEntity read(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
