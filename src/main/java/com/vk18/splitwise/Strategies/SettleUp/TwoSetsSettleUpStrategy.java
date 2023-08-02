package com.vk18.splitwise.Strategies.SettleUp;

import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Model.UserExpence;
import com.vk18.splitwise.Model.UserExpenseType;
import com.vk18.splitwise.Repository.UserExpenseRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.*;

@Component
public class TwoSetsSettleUpStrategy implements SettleUpStrategy{

    UserExpenseRepository userExpenseRepository;

    public TwoSetsSettleUpStrategy(@Autowired UserExpenseRepository userExpenseRepository){
        this.userExpenseRepository=userExpenseRepository;
    }


    @Override
    public List<Transaction> Settle(List<Expense> expenses) {
        List<UserExpence> userExpences=userExpenseRepository.findAllByExpenseIn(expenses);

        HashMap<User,Integer> extraPaidAmount=new HashMap<>();
        for(UserExpence userExpence:userExpences){
            if(!extraPaidAmount.containsKey(userExpence.getUser())){
                extraPaidAmount.put(userExpence.getUser(),0);
            }

            if(userExpence.getUserExpenseType().equals(UserExpenseType.PAID)){
                extraPaidAmount.put(userExpence.getUser(),userExpence.getAmount()+extraPaidAmount.get(userExpence.getUser()));
            }
            else{
                extraPaidAmount.put(userExpence.getUser(),extraPaidAmount.get(userExpence.getUser())-userExpence.getAmount());
            }
        }

        Queue<Pair> morePaid=new LinkedList<>();
        Queue<Pair> lessPaid=new LinkedList<>();

        for(User user:extraPaidAmount.keySet()){
            if(extraPaidAmount.get(user)>0){
                morePaid.add(new Pair(user,extraPaidAmount.get(user)));
            }
            else if(extraPaidAmount.get(user)<0){
                lessPaid.add(new Pair(user,Math.abs(extraPaidAmount.get(user))));
            }
        }

        List<Transaction> transactions=new ArrayList<>();

        while(morePaid.size()!=0&&lessPaid.size()!=0){
            Pair paid=morePaid.remove();
            Pair hadToPay=lessPaid.remove();

            Transaction transaction=new Transaction();
            transaction.setFrom(hadToPay.getUser());
            transaction.setTo(paid.getUser());

            if(paid.getAmount()> hadToPay.getAmount()){
                transaction.setAmount(hadToPay.getAmount());

                paid.setAmount(paid.getAmount()- hadToPay.getAmount());
            }
            else if(paid.getAmount() < hadToPay.getAmount()){
                transaction.setAmount(paid.getAmount());
                hadToPay.setAmount(hadToPay.getAmount()- paid.getAmount());
            }
            else{
                transaction.setAmount(0);
            }

            transactions.add(transaction);
        }

        return transactions;
    }

    @Data
    private class Pair{
        private User user;
        private Integer amount;

        public Pair(User user,Integer amount){
            this.user=user;
            this.amount=amount;
        }
    }
}
