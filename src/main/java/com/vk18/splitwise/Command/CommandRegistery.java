package com.vk18.splitwise.Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistery {
    List<Command> commands;

    public CommandRegistery(GroupSettleUpCommand groupSettleUpCommand,UserSettleUpCommand userSettleUpCommand,GroupExpenseEqualShareCommand groupExpenseEqualShareCommand,UserGroupsCommand userGroupsCommand,UserHistoryCommand userHistoryCommand,AddUserCommand addUserCommand,CreateGroupCommand createGroupCommand,RegisterUserCommand registerUserCommand,UpdateProfileCommand updateProfileCommand){
        commands=new ArrayList<>();
        commands.add(userSettleUpCommand);
        commands.add(groupSettleUpCommand);
        commands.add(groupExpenseEqualShareCommand);
        commands.add(userGroupsCommand);
        commands.add(userHistoryCommand);
        commands.add(addUserCommand);
        commands.add(registerUserCommand);
        commands.add(updateProfileCommand);
        commands.add(createGroupCommand);
    }

    public void execute(String input){
        //System.out.println("Inside CommandRegister execute");
        for(Command command:commands){
            if(command.IsMatching(input)){
                command.execute(input);
                break;
            }
        }
    }
}
