package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.UserController;
import com.vk18.splitwise.Dtos.UpdateProfileRequestDto;
import com.vk18.splitwise.Dtos.UpdateProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProfileCommand implements Command{

    UserController userController;
    @Autowired
    UpdateProfileCommand(UserController userController){
        this.userController=userController;
    }

    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==3&&arr[1].equals(CommandKeywords.UpdateProfile)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        UpdateProfileRequestDto request=new UpdateProfileRequestDto();
        request.setUserId(Long.parseLong(arr[0]));
        request.setNewPassword(arr[2]);

        UpdateProfileResponseDto response=userController.UpdateProfile(request);
        System.out.println(response.getResponseMessage());
    }
}
