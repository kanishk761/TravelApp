package com.example.travelapp;

import com.example.travelapp.DTOs.ActivityDTO;
import com.example.travelapp.DTOs.PassengerDTO;
import com.example.travelapp.mappers.TravelMapper;
import com.example.travelapp.models.AddPassengerRequest;
import com.example.travelapp.models.RegisterActivityRequest;
import com.example.travelapp.service.TravelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TravelServiceTest {
    @Mock
    TravelMapper travelMapper;
    @InjectMocks
    TravelService travelService;
    @Test
    public void testForSuccessAddPassenger() throws Exception {
        AddPassengerRequest addPassengerRequest = new AddPassengerRequest();
        addPassengerRequest.setBalance(0);
        addPassengerRequest.setName("kanishk");
        addPassengerRequest.setType("GOLD");
        addPassengerRequest.setPackge("PK1");
        addPassengerRequest.setNumber(1);
        addPassengerRequest.setBalance(1000);

        doReturn(5).when(travelMapper).getPackageRemainder(Mockito.any());

        doNothing().when(travelMapper).addPassenger(Mockito.any());
        doNothing().when(travelMapper).updatePackageRemainder(Mockito.any(), Mockito.anyInt());

        travelService.addPassenger(addPassengerRequest);


    }

    @Test
    public void testForSuccessRegisterFOrActivity() throws Exception {

        RegisterActivityRequest registerActivityRequest = new RegisterActivityRequest();
        registerActivityRequest.setActivityName("shooting");
        registerActivityRequest.setPassengerNumber(1);

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setRemainder(1);
        activityDTO.setName("shooting");
        activityDTO.setCost(10);
        activityDTO.setDestination("bihar");

        doReturn(activityDTO).when(travelMapper).getActivity(Mockito.anyString());

        Set<String> packages = new HashSet<>();
        packages.add("PK1");
        packages.add("PK2");

        doReturn(packages).when(travelMapper).getPackagesWithDestination(Mockito.anyString());

        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setBalance(20);
        passengerDTO.setNumber(1);
        passengerDTO.setPackge("PK2");

        doReturn(passengerDTO).when(travelMapper).getPassenger(Mockito.any());

        doNothing().when(travelMapper).updateBalance(Mockito.anyInt(), Mockito.anyInt());
        doNothing().when(travelMapper).updateActivityRemainder(Mockito.any(), Mockito.anyInt());
        doNothing().when(travelMapper).registerActivity(Mockito.any());

        travelService.registerActivity(registerActivityRequest);


    }
}
