package com.vk18.splitwise.Controller;

import com.vk18.splitwise.Dtos.*;
import com.vk18.splitwise.Exceptions.InvalidArgumentException;
import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.Group;
import com.vk18.splitwise.Service.GroupService;
import com.vk18.splitwise.Service.UserService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {

    GroupService groupService;
    UserService userService;

    public GroupController(UserService userService,GroupService groupService){
        this.userService=userService;
        this.groupService=groupService;
    }
    public CreateGroupResponseDto CreateGroup(CreateGroupRequestDto request){
        Long userId=request.getUserId();
        String groupName= request.getGroupName();

        Group group;
        CreateGroupResponseDto response=new CreateGroupResponseDto();

        try{
            group=groupService.CreateGroup(userId,groupName);
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

    public AddUserResponseDto AddUser(AddUserRequestDto request){
        Long adminId=request.getAdminId();
        Long groupId= request.getGroupId();
        Long userId= request.getUserId();

        AddUserResponseDto response=new AddUserResponseDto();

        try{
            groupService.AddUser(adminId,groupId,userId);
            response.setAdminName(userService.getUser(adminId).getName());
            response.setGroupName(groupService.getGroup(groupId).getName());
            response.setUserName(userService.getUser(userId).getName());
            response.setResponseMessage(response.getUserName()+" added to group : "+response.getGroupName()+" by "+response.getAdminName()+".");
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (InvalidArgumentException e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }

    public UserGroupsResponseDto UserGroups(UserGroupsRequestDto request){
        Long userId=request.getUserId();

        UserGroupsResponseDto response=new UserGroupsResponseDto();

        try{
            List<Group> groups=groupService.UserGroups(userId);
            response.setGroups(new ArrayList<String>());
            for(Group group:groups){
                response.getGroups().add(group.getName());
            }
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(InvalidArgumentException e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage(e.getMessage());
        }
        catch (Exception e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage("Something went wrong");
        }

        return response;
    }
}
