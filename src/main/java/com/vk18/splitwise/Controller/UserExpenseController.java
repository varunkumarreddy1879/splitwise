package com.vk18.splitwise.Controller;

import com.vk18.splitwise.Dtos.ResponseStatus;
import com.vk18.splitwise.Dtos.UserExpenceDto;
import com.vk18.splitwise.Dtos.UserHistoryRequestDto;
import com.vk18.splitwise.Dtos.UserHistoryResponseDto;
import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Model.UserExpence;
import com.vk18.splitwise.Service.UserExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserExpenseController {

    UserExpenseService userExpenseService;

    @Autowired
    UserExpenseController(UserExpenseService userExpenseService){
        this.userExpenseService=userExpenseService;
    }

    public UserHistoryResponseDto UserHistory(UserHistoryRequestDto request){
        Long userId=request.getUserId();

        UserHistoryResponseDto response=new UserHistoryResponseDto();

        try{
            List<UserExpence> userExpences=userExpenseService.UserHistory(userId);
            List<UserExpenceDto> userExpenceDtos=new ArrayList<>();
            for(UserExpence userExpence:userExpences){
                UserExpenceDto userExpenceDto=new UserExpenceDto();
                userExpenceDto.setUserName(userExpence.getUser().getName());
                userExpenceDto.setDescription(userExpence.getExpense().getDescription());
                userExpenceDto.setGroupName(userExpence.getExpense().getGroup().getName());
                userExpenceDto.setCreatedBy(userExpence.getExpense().getCreatedBy().getName());
                userExpenceDto.setExpenseType(userExpence.getExpense().getExpenceType().name());
                userExpenceDto.setTotalAmount(userExpence.getExpense().getAmount());
                userExpenceDto.setUserExpenseType( userExpence.getUserExpenseType().name());
                userExpenceDto.setAmount(userExpence.getAmount());

                userExpenceDtos.add(userExpenceDto);
            }

            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setUserExpenses(userExpenceDtos);
        }
        catch (Exception e){
            e.printStackTrace();
            response.setResponseStatus(ResponseStatus.FAILED);
            response.setResponseMessage("Something went wrong.");
        }


        return response;
    }
}
