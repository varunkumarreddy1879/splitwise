package com.vk18.splitwise.Controller;

import com.vk18.splitwise.Dtos.CreateGroupRequestDto;
import com.vk18.splitwise.Dtos.CreateGroupResponseDto;
import com.vk18.splitwise.Dtos.ResponseStatus;
import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.Group;
import com.vk18.splitwise.Service.GroupService;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    GroupService groupService;

    public GroupController(GroupService groupService){
        this.groupService=groupService;
    }
    public CreateGroupResponseDto CreateGroup(CreateGroupRequestDto request){
        String phone=request.getPhone();
        String groupName= request.getGroupName();

        Group group;
        CreateGroupResponseDto response=new CreateGroupResponseDto();

        try{
            group=groupService.CreateGroup(phone,groupName);
            response.setGroupName(groupName);
            response.setUserName(group.getAdmin().getName());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setResponseMessage(response.getUserName()+" successfully created group "+groupName);
        }
        catch (InvalidUserException e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage("Something went wrong");
        }
        return response;
    }
}
