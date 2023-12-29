package com.example.travelapp.mappers;

import com.example.travelapp.DTOs.ActivityDTO;
import com.example.travelapp.DTOs.PassengerDTO;
import com.example.travelapp.models.AddPassengerRequest;
import com.example.travelapp.models.RegisterActivityRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Set;

@Mapper
public interface TravelMapper {


    @Insert("INSERT INTO PASSENGER (NAME, TYPE, NUMBER, BALANCE, PACKAGE) VALUES " +
            "(#{addPassengerRequest.name},#{addPassengerRequest.type}, #{addPassengerRequest.number},#{addPassengerRequest.balance},#{addPassengerRequest.packge})")
    void addPassenger(AddPassengerRequest addPassengerRequest);

    @Select("SELECT REMAINDER FROM PACKAGE WHERE NAME = #{PACKGE}")
    Integer getPackageRemainder(String packge);

    @Update("UPDATE PACKAGE SET REMAINDER = #{i} WHERE NAME = #{packge}")
    void updatePackageRemainder(String packge, int i);

    @Select("SELECT PACKAGE_NAME FROM PACKAGE_DEST_RELATION WHERE DESTINATION = #{destination}")
    Set<String> getPackagesWithDestination(String destination);

    @Insert("INSERT INTO ACTIVITY_REGISTER (PASSENGER_NUMBER, ACTIVITY_NAME) VALUES " +
            "(#{registerActivityRequest.passengerNumber},#{registerActivityRequest.activityName})")
    void registerActivity(RegisterActivityRequest registerActivityRequest);

    @Select("SELECT NAME as name, TYPE as type, BALANCE as balance FROM PASSENGER WHERE NUMBER = #{passengerNumber}")
    PassengerDTO getPassenger(Integer passengerNumber);

    @Select("SELECT NAME as name, DESTINATION as destination, COST as cost, REMAINDER as remainder FROM ACTIVITY WHERE NAME = #{activityName}")
    ActivityDTO getActivity(String activityName);

    @Update("UPDATE ACTIVITY SET REMAINDER = #{remainder} WHERE NAME = #{name}")
    void updateActivityRemainder(String name, int remainder);

    @Update("UPDATE PASSENGER SET BALANCE = #{balance} WHERE NUMBER = #{number}")
    void updateBalance(int number, int balance);
}
