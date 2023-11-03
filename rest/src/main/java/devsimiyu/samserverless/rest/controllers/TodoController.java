package devsimiyu.samserverless.rest.controllers;

import devsimiyu.samserverless.core.model.entity.Todo;
import devsimiyu.samserverless.core.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todo")
    public String create() {
        Todo todo = todoService.createTodo();
        return todo.id;
    }
}
