package devsimiyu.samserverless.core.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

public class User {

    @NotBlank(message = "Name cannot be unknown")
    public String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    public String email;

    @NotNull(message = "address cannot be null")
    @Valid
    public Address address;
}
