package devsimiyu.samserverless.core.services;

import devsimiyu.samserverless.core.interceptors.Auth;
import devsimiyu.samserverless.core.model.dto.TodoItem;
import devsimiyu.samserverless.core.model.entity.Todo;
import devsimiyu.samserverless.core.repository.TodoRepository;
import devsimiyu.samserverless.core.security.Role;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.List;

@Dependent
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    @Auth(Role.ADMIN)
    public TodoItem getTodo(String id) {
        return todoRepository.findOneById(id);
    }

    public List<TodoItem> searchTodos(String title) {
        return todoRepository.findAllByTitle(title);
    }

    @Auth(Role.ADMIN)
    public Todo createTodo() {
        Todo todo = new Todo("CRUD", "Create CRUD operations for Todo");
        todoRepository.save(todo);
        return todo;
    }
}
