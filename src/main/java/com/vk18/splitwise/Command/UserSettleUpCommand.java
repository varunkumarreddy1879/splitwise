package com.vk18.splitwise.Command;

import com.vk18.splitwise.Controller.ExpenseController;
import com.vk18.splitwise.Dtos.UserSettleUpRequestDto;
import com.vk18.splitwise.Dtos.UserSettleUpResponseDto;
import com.vk18.splitwise.Strategies.SettleUp.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSettleUpCommand implements Command{

    ExpenseController expenseController;
    @Autowired
    public UserSettleUpCommand(ExpenseController expenseController){
        this.expenseController=expenseController;
    }

    @Override
    public boolean IsMatching(String command) {
        String[] arr=command.split(" ");

        if(arr.length==2&&arr[1].equalsIgnoreCase(CommandKeywords.SettleUp)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String command) {
        String[] arr=command.split(" ");

        UserSettleUpRequestDto request=new UserSettleUpRequestDto();
        request.setUserId(Long.parseLong(arr[0]));

        UserSettleUpResponseDto response=expenseController.UserSettleUp(request);

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
