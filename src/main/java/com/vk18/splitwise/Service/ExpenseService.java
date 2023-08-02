package com.vk18.splitwise.Service;

import com.vk18.splitwise.Exceptions.InvalidArgumentException;
import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.Group;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Model.UserExpence;
import com.vk18.splitwise.Repository.ExpenseRepository;
import com.vk18.splitwise.Repository.GroupRepository;
import com.vk18.splitwise.Repository.UserExpenseRepository;
import com.vk18.splitwise.Repository.UserRepository;
import com.vk18.splitwise.Strategies.SettleUp.Transaction;
import com.vk18.splitwise.Strategies.SettleUp.TwoSetsSettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    UserRepository userRepository;

    UserExpenseRepository userExpenseRepository;
    TwoSetsSettleUpStrategy twoSetsSettleUpStrategy;
    GroupRepository groupRepository;
    ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository,GroupRepository groupRepository,TwoSetsSettleUpStrategy twoSetsSettleUpStrategy,UserRepository userRepository,UserExpenseRepository userExpenseRepository){
        this.userRepository=userRepository;
        this.expenseRepository=expenseRepository;
        this.groupRepository=groupRepository;
        this.twoSetsSettleUpStrategy=twoSetsSettleUpStrategy;
        this.userExpenseRepository=userExpenseRepository;
    }
    public List<Transaction> UserSettleUp(Long userId) throws InvalidArgumentException {
        Optional<User> optionalUser=userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User with id : "+userId+" does not exist.");
        }

        User user=optionalUser.get();

        List<UserExpence> userExpences=userExpenseRepository.findAllByUser(user);

        List<Expense> expensesOfUser=new ArrayList<>();
        for(UserExpence userExpence:userExpences){
            expensesOfUser.add(userExpence.getExpense());
        }

        List<Transaction> allTransactions=twoSetsSettleUpStrategy.Settle(expensesOfUser);

        List<Transaction> userTransactions=new ArrayList<>();

        for(Transaction transaction:allTransactions){
            if(transaction.getFrom().equals(user)||transaction.getTo().equals(user)){
                userTransactions.add(transaction);
            }
        }

        return userTransactions;
    }

    public List<Transaction> GroupSettleUp(Long groupId) throws InvalidArgumentException{
        Optional<Group> optionalGroup=groupRepository.findById(groupId);

        if(optionalGroup.isEmpty()){
            throw new InvalidArgumentException("Groud with id : "+groupId+" does not exist.");
        }

        Group group=optionalGroup.get();

        List<Expense> expenses=expenseRepository.findAllByGroup(group);

        return twoSetsSettleUpStrategy.Settle(expenses);
    }
}
