package com.example.travelapp.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class AddPassengerRequest {
    @NotBlank(message = "name field cannot be blank")
    String name;
    @NotBlank(message = "name field cannot be blank")
    @Pattern(regexp = "STANDARD | GOLD | PREMIUM")
    String type;
    @NotNull
    @Range(max = 100, message = "passenger number should be bw 0 and 100")
    Integer number;
    @Range(max = Integer.MAX_VALUE, message = "passenger number should be bw 0 and 100000000")
    int balance;
    @NotBlank(message = "packge field cannot be blank")
    String packge;

}
