package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.GroupController;
import com.vk18.splitwise.Dtos.UserGroupsRequestDto;
import com.vk18.splitwise.Dtos.UserGroupsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGroupsCommand implements Command {

    GroupController groupController;

    @Autowired
    UserGroupsCommand(GroupController groupController){
        this.groupController=groupController;
    }

    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==2&&arr[1].equalsIgnoreCase(CommandKeywords.Groups)){
            return true;
        }

        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");
        UserGroupsRequestDto request=new UserGroupsRequestDto();
        request.setUserId(Long.parseLong(arr[0]));
        UserGroupsResponseDto response=groupController.UserGroups(request);

        if(response.getResponseStatus().name().equalsIgnoreCase(CommandKeywords.FAILED)){
            System.out.println(response.getResponseMessage());
        }
        else{
            System.out.println("----Groups----");
            System.out.println(response.getGroups().toString());
        }
    }
}
