package com.vk18.splitwise.Service;

import com.vk18.splitwise.Exceptions.InvalidArgumentException;
import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Model.UserExpence;
import com.vk18.splitwise.Repository.UserExpenseRepository;
import com.vk18.splitwise.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserExpenseService {
    UserRepository userRepository;
    UserExpenseRepository userExpenseRepository;
    @Autowired
    UserExpenseService(UserRepository userRepository,UserExpenseRepository userExpenseRepository){
        this.userRepository=userRepository;
        this.userExpenseRepository=userExpenseRepository;
    }
    public List<UserExpence> UserHistory(Long userId) throws InvalidArgumentException {

        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("user with id : "+userId+" doesn't exist.");
        }
        else{
            User user=optionalUser.get();
            List<UserExpence> userExpences=userExpenseRepository.findAllByUser(user);
            return userExpences;
        }

    }
}
