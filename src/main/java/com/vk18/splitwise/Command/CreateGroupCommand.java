package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.GroupController;
import com.vk18.splitwise.Dtos.CreateGroupRequestDto;
import com.vk18.splitwise.Dtos.CreateGroupResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CreateGroupCommand implements Command{

    GroupController groupController;

    public CreateGroupCommand(GroupController groupController){
        this.groupController=groupController;
    }

    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==3&&arr[1].equals(CommandKeywords.AddGroup)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        CreateGroupRequestDto request=new CreateGroupRequestDto();
        request.setPhone(arr[0]);
        request.setGroupName(arr[2]);

        CreateGroupResponseDto response=groupController.CreateGroup(request);
        System.out.println(response.getResponseMessage());
    }
}
