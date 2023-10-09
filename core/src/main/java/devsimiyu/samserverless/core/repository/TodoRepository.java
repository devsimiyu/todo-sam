package devsimiyu.samserverless.core.repository;

import devsimiyu.samserverless.core.model.dto.TodoItem;
import devsimiyu.samserverless.core.model.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {

    @Query("{_id: '?0'}")
    TodoItem findOneById(String id);

    @Query("{title: {'$regex': '?0', '$options': 'i'}}")
    List<TodoItem> findAllByTitle(String title);
}
