package com.vk18.splitwise.Service;

import aj.org.objectweb.asm.ConstantDynamic;
import com.vk18.splitwise.Exceptions.InvalidArgumentException;
import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.*;
import com.vk18.splitwise.Repository.ExpenseRepository;
import com.vk18.splitwise.Repository.GroupRepository;
import com.vk18.splitwise.Repository.UserExpenseRepository;
import com.vk18.splitwise.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    UserRepository userRepository;
    GroupRepository groupRepository;
    ExpenseRepository expenseRepository;
    UserExpenseRepository userExpenseRepository;

    public GroupService(UserExpenseRepository userExpenseRepository,ExpenseRepository expenseRepository,GroupRepository groupRepository,UserRepository userRepository){
        this.userRepository=userRepository;
        this.userExpenseRepository=userExpenseRepository;
        this.expenseRepository=expenseRepository;
        this.groupRepository=groupRepository;
    }
    public Group CreateGroup(Long userId, String groupName) throws InvalidUserException {
        /*
        check user exist or not
        not exist throw exception
        exist create group
         */

        Optional<User> optionalUser=userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new InvalidUserException("user with id : "+userId+" does not exist.");
        }
        else{
            User user=optionalUser.get();

            Group group=new Group();
            group.setName(groupName);
            group.setAdmin(user);
            group.setUsers(new ArrayList<User>());
            group.getUsers().add(user);

            return groupRepository.save(group);
        }
    }

    public void AddUser(Long adminId, Long groupId, Long userId) throws InvalidArgumentException,Exception {
        /*
        check user exist
        check group exist
        check admin id is correct
        add user to group
         */

        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("user with id : "+userId+" does not exist.");
        }

        Optional<Group> optionalGroup=groupRepository.findById(groupId);
        if(optionalGroup.isEmpty()){
            throw new InvalidArgumentException("group with id : "+groupId+" does not exist.");
        }

        Group group=optionalGroup.get();
        User user=optionalUser.get();

        if(!group.getAdmin().getId().equals(adminId)){
            throw new Exception("only admin can add users to group.");
        }

        group.getUsers().add(user);

        groupRepository.save(group);

        return;

    }

    public Group getGroup(Long groupId) {
        return groupRepository.findById(groupId).get();
    }

    public List<Group> UserGroups(Long userId) throws InvalidArgumentException {
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("user with id : "+userId+" doesn't exist.");
        }
        User user=optionalUser.get();
        return groupRepository.findAllByUsers(user);
    }

    public void GroupExpenseEqualShare(Long userId, Long groupId, int amount, String description) throws InvalidArgumentException{
        Optional<User> optionalUser=userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User with id: "+userId+" does not exist.");
        }

        Optional<Group> optionalGroup=groupRepository.findById(groupId);

        if(optionalGroup.isEmpty()){
            throw new InvalidArgumentException("Group with id: "+groupId+" does not exist.");
        }

        User user=optionalUser.get();
        Group group=optionalGroup.get();

        Expense expense=new Expense();
        expense.setGroup(group);
        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setCreatedBy(user);
        expense.setExpenceType(ExpenceType.EXPENTION);

        expense=expenseRepository.save(expense);

        int membersCount=group.getUsers().size();
        int share=amount/membersCount;

        UserExpence userExpence=new UserExpence();
        userExpence.setUser(user);
        userExpence.setAmount(amount);
        userExpence.setExpense(expense);
        userExpence.setUserExpenseType(UserExpenseType.PAID);

        userExpenseRepository.save(userExpence);


        for(User member:group.getUsers()){
            userExpence=new UserExpence();
            userExpence.setAmount(share);
            userExpence.setExpense(expense);
            userExpence.setUserExpenseType(UserExpenseType.HAD_TO_PAY);
            userExpence.setUser(member);
            userExpenseRepository.save(userExpence);
        }

        return;
    }
}
