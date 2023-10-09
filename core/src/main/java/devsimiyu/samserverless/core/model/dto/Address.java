package devsimiyu.samserverless.core.model.dto;

import jakarta.validation.constraints.NotBlank;

public class Address {

    @NotBlank(message = "Street cannot be blank")
    public String street;

    @NotBlank(message = "Apartment cannot be empty")
    public String apartment;
}
