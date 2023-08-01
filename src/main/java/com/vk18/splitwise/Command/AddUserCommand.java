package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.GroupController;
import com.vk18.splitwise.Dtos.AddUserRequestDto;
import com.vk18.splitwise.Dtos.AddUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserCommand implements Command{

    GroupController groupController;

    @Autowired
    public AddUserCommand(GroupController groupController){
        this.groupController=groupController;
    }
    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==4&&arr[1].equals(CommandKeywords.AddMember)){
            return true;
        }

        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        AddUserRequestDto request=new AddUserRequestDto();
        request.setUserId(Long.parseLong(arr[3]));
        request.setAdminId(Long.parseLong(arr[0]));
        request.setGroupId(Long.parseLong(arr[2]));

        AddUserResponseDto response=groupController.AddUser(request);
        System.out.println(response.getResponseMessage());

    }
}
