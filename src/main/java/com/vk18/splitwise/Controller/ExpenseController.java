package com.vk18.splitwise.Controller;

import com.vk18.splitwise.Dtos.*;
import com.vk18.splitwise.Exceptions.InvalidArgumentException;
import com.vk18.splitwise.Service.ExpenseService;
import com.vk18.splitwise.Strategies.SettleUp.Transaction;
import org.hibernate.query.sqm.tree.expression.SqmDurationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {

    ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    public GroupSettleUpResponseDto GroupSettleUp(GroupSettleUpRequestDto request){
        Long groupId=request.getGroupId();

        GroupSettleUpResponseDto response=new GroupSettleUpResponseDto();

        try{
            List<Transaction> transactions=expenseService.GroupSettleUp(groupId);
            response.setTransactions(transactions);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setTransactions(transactions);
        }
        catch (InvalidArgumentException e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage(e.getMessage());
        }
        catch (Exception e){
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage("Something went wrong.");
        }

        return response;
    }
    public UserSettleUpResponseDto UserSettleUp(UserSettleUpRequestDto request){
        Long userId=request.getUserId();

        UserSettleUpResponseDto response=new UserSettleUpResponseDto();

        try{
            List<Transaction> transactions=expenseService.UserSettleUp(userId);
            response.setTransactions(transactions);
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setTransactions(transactions);
        }
        catch (InvalidArgumentException e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage("Something went wrong.");
        }

        return response;

    }
}
