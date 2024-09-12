package net.dunice.BasicServer.repositories;

import net.dunice.BasicServer.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>
{
    void deleteAllByStatusTrue();
}
