package com.vk18.splitwise.Service;

import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.Group;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Repository.GroupRepository;
import com.vk18.splitwise.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GroupService {

    UserRepository userRepository;
    GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository,UserRepository userRepository){
        this.userRepository=userRepository;
        this.groupRepository=groupRepository;
    }
    public Group CreateGroup(String phone, String groupName) throws InvalidUserException {
        /*
        check user exist or not
        not exist throw exception
        exist create group
         */

        Optional<User> optionalUser=userRepository.findByPhone(phone);

        if(optionalUser.isEmpty()){
            throw new InvalidUserException("user with phone : "+phone+" does not exist.");
        }
        else{
            User user=optionalUser.get();

            Group group=new Group();
            group.setName(groupName);
            group.setAdmin(user);
            group.setUsers(new ArrayList<User>());
            group.getUsers().add(user);
            group.setExpenses(new ArrayList<Expense>());


            return groupRepository.save(group);
        }
    }
}
