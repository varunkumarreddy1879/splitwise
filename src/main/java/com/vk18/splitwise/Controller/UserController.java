package com.vk18.splitwise.Controller;

import com.vk18.splitwise.Dtos.*;
import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }
    public RegisterUserResposeDto RegisterUser(RegisterUserRequestDto request){
        String name=request.getName();
        String phone=request.getPhone();
        String password= request.getPassword();

        User user;
        RegisterUserResposeDto response=new RegisterUserResposeDto();

        try{
            user=userService.RegisterUser(name,phone,password);
            response.setName(user.getName());
            response.setPhone(user.getPhone());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setReponseMessage("Succesfully registered with phone : "+phone+".");
        }
        catch (InvalidUserException e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setReponseMessage(e.getMessage());
        }
        catch(Exception e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setReponseMessage("Something Went Wrong.");
        }

        return response;
    }

    public UpdateProfileResponseDto UpdateProfile(UpdateProfileRequestDto request){
        String phone=request.getPhone();
        String newPassword=request.getNewPassword();

        User user;
        UpdateProfileResponseDto response = new UpdateProfileResponseDto();

        try{
            user=userService.UpdateProfile(phone,newPassword);
            response.setName(user.getName());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setResponseMessage(user.getName()+" succesfully updated profile password.");
        }
        catch (InvalidUserException e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage(e.getMessage());
        }
        catch (Exception e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage("Something went wrong.");
        }

        return response;
    }
}
