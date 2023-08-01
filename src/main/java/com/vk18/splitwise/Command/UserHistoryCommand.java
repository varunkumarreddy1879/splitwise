package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.UserExpenseController;
import com.vk18.splitwise.Dtos.UserExpenceDto;
import com.vk18.splitwise.Dtos.UserHistoryRequestDto;
import com.vk18.splitwise.Dtos.UserHistoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHistoryCommand implements Command{

    UserExpenseController userExpenseController;



    @Autowired
    UserHistoryCommand(UserExpenseController userExpenseController){
        this.userExpenseController=userExpenseController;
    }

    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==2&&arr[1].equals(CommandKeywords.History)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        UserHistoryRequestDto request=new UserHistoryRequestDto();
        request.setUserId(Long.parseLong(arr[0]));

        UserHistoryResponseDto response=userExpenseController.UserHistory(request);

        if(response.getResponseStatus().equals(CommandKeywords.FAILED)){
            System.out.println(response.getResponseMessage());
        }
        else{
            System.out.println("----HISTORY----");
            for(UserExpenceDto userExpenceDto:response.getUserExpenses()){
                userExpenceDto.toString();
            }
        }

    }
}
