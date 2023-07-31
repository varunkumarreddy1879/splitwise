package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.UserController;
import com.vk18.splitwise.Dtos.RegisterUserRequestDto;
import com.vk18.splitwise.Dtos.RegisterUserResposeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
@Component
public class RegisterUserCommand implements Command{

    UserController userController;
    @Autowired
    RegisterUserCommand(UserController userController){
        this.userController=userController;
    }

    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==4&&arr[0].equals(CommandKeywords.Resgister)){
            return true;
        }

        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        RegisterUserRequestDto request=new RegisterUserRequestDto();
        request.setName(arr[1]);
        request.setPhone(arr[2]);
        request.setPassword(arr[3]);

        RegisterUserResposeDto respose=userController.RegisterUser(request);
        System.out.println(respose.getReponseMessage());
    }
}
