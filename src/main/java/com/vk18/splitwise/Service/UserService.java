package com.vk18.splitwise.Service;

import com.vk18.splitwise.Exceptions.InvalidUserException;
import com.vk18.splitwise.Model.User;
import com.vk18.splitwise.Model.UserStatus;
import com.vk18.splitwise.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User RegisterUser(String name, String phone, String password) throws InvalidUserException {
        /*
        check user phone alredy exist
        if doest exits creat user
        else
        if userstatus is active throw exception
        else set userstatus to active and return user
         */

        Optional<User> optionalUser=userRepository.findByPhone(phone);

        if(optionalUser.isEmpty()){

            User user=new User();
            user.setName(name);
            user.setUserStatus(UserStatus.ACTIVE);
            user.setPhone(phone);
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(password));

            return userRepository.save(user);
        }
        else{
            User user=optionalUser.get();
            if(user.getUserStatus().equals(UserStatus.ACTIVE)){
                throw new InvalidUserException("User with phone : "+phone+ " alredy exist.");
            }
            else{
                user.setName(name);
                user.setUserStatus(UserStatus.ACTIVE);
                BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(password));

                return userRepository.save(user);
            }
        }
    }

    public User UpdateProfile(String phone, String newPassword) throws InvalidUserException{

        /*
        check user exist or not
        if not exist throw exception
        else update password
         */

        Optional<User> optionalUser=userRepository.findByPhone(phone);

        if(optionalUser.isEmpty()){
            throw new InvalidUserException("No user exist with phone : "+phone);
        }
        else{
            User user=optionalUser.get();
            user.setPassword(newPassword);
            return userRepository.save(user);
        }
    }
}
