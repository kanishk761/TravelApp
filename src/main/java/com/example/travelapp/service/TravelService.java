package com.example.travelapp.service;

import com.example.travelapp.DTOs.ActivityDTO;
import com.example.travelapp.DTOs.PassengerDTO;
import com.example.travelapp.mappers.TravelMapper;
import com.example.travelapp.models.AddPassengerRequest;
import com.example.travelapp.models.RegisterActivityRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional(rollbackOn = {Exception.class})
public class TravelService {
    @Autowired
    private TravelMapper travelMapper;
    public void addPassenger(AddPassengerRequest addPassengerRequest) throws Exception {
        Integer remaining = travelMapper.getPackageRemainder(addPassengerRequest.getPackge());
        if(remaining == null)
            throw new Exception("no such package exists");

        if(remaining > 0) {
            travelMapper.addPassenger(addPassengerRequest);

            travelMapper.updatePackageRemainder(addPassengerRequest.getPackge(), remaining-1);
        }
        else
            throw new Exception("passengers full in the package");
    }

    public void registerActivity(RegisterActivityRequest registerActivityRequest) throws Exception {
        ActivityDTO activityDTO = travelMapper.getActivity(registerActivityRequest.getActivityName());
        if(activityDTO == null) {
            throw new Exception("unknown activity");
        }

        if(activityDTO.getRemainder() == 0) {
            throw new Exception("activity capacity full");
        }
        activityDTO.setRemainder(activityDTO.getRemainder()-1);

        Set<String> packagesWithDestination = travelMapper.getPackagesWithDestination(activityDTO.getDestination());
        PassengerDTO passengerDTO = travelMapper.getPassenger(registerActivityRequest.getPassengerNumber());

        if(passengerDTO.getPackge().equals("STANDARD")) {
            if(passengerDTO.getBalance() < activityDTO.getCost())
                throw new Exception("not enough balance");
            passengerDTO.setBalance(passengerDTO.getBalance() - activityDTO.getCost());
        }
        else if (passengerDTO.getPackge().equals("GOLD")) {
            int price = activityDTO.getCost() - activityDTO.getCost()/10;
            if(passengerDTO.getBalance() < price)
                throw new Exception("not enough balance");
            passengerDTO.setBalance(passengerDTO.getBalance() - price);
        }

        if(packagesWithDestination.contains(passengerDTO.getPackge())){
            travelMapper.updateBalance(passengerDTO.getNumber(), passengerDTO.getBalance());
            travelMapper.updateActivityRemainder(activityDTO.getName(), activityDTO.getRemainder());
            travelMapper.registerActivity(registerActivityRequest);
        }
        else
            throw new Exception("activity is in destination not included in package");

    }
}
