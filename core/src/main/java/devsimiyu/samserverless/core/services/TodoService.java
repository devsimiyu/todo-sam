package devsimiyu.samserverless.core.services;

import devsimiyu.samserverless.core.model.dto.TodoItem;
import devsimiyu.samserverless.core.model.entity.Todo;
import devsimiyu.samserverless.core.repository.TodoRepository;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

import java.util.List;

@Default
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    public TodoItem getTodo(String id) {
        return todoRepository.findOneById(id);
    }

    public List<TodoItem> searchTodos(String title) {
        return todoRepository.findAllByTitle(title);
    }

    public Todo createTodo() {
        Todo todo = new Todo("CRUD", "Create CRUD operations for Todo");
        todoRepository.save(todo);
        return todo;
    }
}
