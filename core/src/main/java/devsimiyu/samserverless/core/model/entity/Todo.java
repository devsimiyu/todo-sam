package devsimiyu.samserverless.core.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("todos")
public class Todo {

    @Id
    public String id;
    public String title;
    public String description;

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
