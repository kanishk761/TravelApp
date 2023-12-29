package com.example.travelapp.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class RegisterActivityRequest {
    @NotNull
    @Range(max = 100, message = "passengerNumber should be bw 0 and 100")
    Integer passengerNumber;

    @NotBlank(message = "activityName field cannot be blank")
    String activityName;
}
