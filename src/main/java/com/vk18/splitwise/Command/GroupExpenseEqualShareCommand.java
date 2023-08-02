package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.GroupController;
import com.vk18.splitwise.Dtos.GroupExpenseEqualShareRequestDto;
import com.vk18.splitwise.Dtos.GroupExpenseEqualShareResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupExpenseEqualShareCommand implements Command{

    GroupController groupController;

    @Autowired
    public GroupExpenseEqualShareCommand(GroupController groupController){
        this.groupController=groupController;
    }
    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(
                arr.length>=8&&arr[1].equalsIgnoreCase(CommandKeywords.Expense)&&
                        arr[3].equalsIgnoreCase(CommandKeywords.iPay)&&
                        arr[5].equalsIgnoreCase(CommandKeywords.Equal)&&
                        arr[6].equalsIgnoreCase(CommandKeywords.Desc)
        ){
            //System.out.println("GroupExpenceEqualSharingCommand");
            return true;
        }

        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        GroupExpenseEqualShareRequestDto request=new GroupExpenseEqualShareRequestDto();
        request.setGroupId(Long.parseLong(arr[2]));
        request.setUserId(Long.parseLong(arr[0]));
        request.setAmount(Integer.parseInt(arr[4]));
        StringBuilder description=new StringBuilder();
        for(int i=7;i<arr.length;i++){
            description.append(arr[i]);
        }
        request.setDescription(description.toString());

        GroupExpenseEqualShareResponseDto response=groupController.GroupExpenseEqualShare(request);
        System.out.println(response.getResponseMessage());
    }

}
