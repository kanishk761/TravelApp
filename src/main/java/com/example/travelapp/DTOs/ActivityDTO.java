package com.example.travelapp.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDTO {
    String name;
    String destination;
    int remainder;
    int cost;
}
