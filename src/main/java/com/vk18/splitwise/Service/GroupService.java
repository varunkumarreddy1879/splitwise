package com.vk18.splitwise.Service;

import aj.org.objectweb.asm.ConstantDynamic;
import com.vk18.splitwise.Exceptions.InvalidArgumentException;
import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.Expense;
import com.vk18.splitwise.Model.Group;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Repository.GroupRepository;
import com.vk18.splitwise.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    UserRepository userRepository;
    GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository,UserRepository userRepository){
        this.userRepository=userRepository;
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

        user.getGroups().add(group);

        userRepository.save(user);

        return;

    }

    public Group getGroup(Long groupId) {
        return groupRepository.findById(groupId).get();
    }
}
