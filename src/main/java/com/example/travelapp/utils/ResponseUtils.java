package com.example.travelapp.utils;

import com.example.travelapp.models.Response;
import org.springframework.validation.Errors;

public class ResponseUtils {
    public static Response createErrorResponse(Errors errors) {
        Response response = new Response();
        response.setResponseCode("F00");
        response.setPayload(errors.toString());
        return response;
    }

    public static Response createErrorResponse(String errorMsg) {
        Response response = new Response();
        response.setResponseCode("F01");
        response.setPayload(errorMsg);
        return response;
    }

    public static Response createSuccessResponse() {
        Response response = new Response();
        response.setResponseCode("F00");
        response.setPayload("success");
        return response;
    }
}
