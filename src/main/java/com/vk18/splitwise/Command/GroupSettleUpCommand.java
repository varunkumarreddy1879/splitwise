package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.ExpenseController;
import com.vk18.splitwise.Dtos.GroupSettleUpRequestDto;
import com.vk18.splitwise.Dtos.GroupSettleUpResponseDto;
import com.vk18.splitwise.Dtos.UserSettleUpRequestDto;
import com.vk18.splitwise.Dtos.UserSettleUpResponseDto;
import com.vk18.splitwise.Strategies.SettleUp.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupSettleUpCommand implements Command{

    ExpenseController expenseController;
    @Autowired
    public GroupSettleUpCommand(ExpenseController expenseController){
        this.expenseController=expenseController;
    }

    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==3&&arr[1].equalsIgnoreCase(CommandKeywords.SettleUp)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        GroupSettleUpRequestDto request=new GroupSettleUpRequestDto();
        request.setGroupId(Long.parseLong(arr[0]));

        GroupSettleUpResponseDto response=expenseController.GroupSettleUp(request);

        if(response.getResponseStatus().equals(CommandKeywords.FAILED)){
            System.out.println(response.getResponseMessage());
        }
        else{
            System.out.println("Transactions to settleup : ");
            for(Transaction transaction: response.getTransactions()){
                System.out.println(transaction.toString());
            }
        }
    }
}
