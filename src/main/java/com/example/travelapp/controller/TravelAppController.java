package com.example.travelapp.controller;

import com.example.travelapp.models.AddPassengerRequest;
import com.example.travelapp.models.RegisterActivityRequest;
import com.example.travelapp.models.Response;
import com.example.travelapp.service.TravelService;
import com.example.travelapp.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelAppController {
    @Autowired
    TravelService travelService;


    @PostMapping("/addPassenger")
    Response addPassenger(@Valid @RequestBody AddPassengerRequest addPassengerRequest, Errors errors) {
        if(errors.hasErrors()){
            return ResponseUtils.createErrorResponse(errors);
        }
        try {
            travelService.addPassenger(addPassengerRequest);
        }
        catch (Exception e) {
            return ResponseUtils.createErrorResponse(e.getMessage());
        }

        return ResponseUtils.createSuccessResponse();
    }

    @PostMapping("/registerActivity")
    Response registerActivity(@Valid @RequestBody RegisterActivityRequest registerActivityRequest, Errors errors) {
        if(errors.hasErrors()){
            return ResponseUtils.createErrorResponse(errors);
        }
        try {
            travelService.registerActivity(registerActivityRequest);
        }
        catch (Exception e) {
            return ResponseUtils.createErrorResponse(e.getMessage());
        }

        return ResponseUtils.createSuccessResponse();
    }
}
