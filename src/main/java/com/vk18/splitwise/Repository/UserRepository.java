package com.vk18.splitwise.Repository;

import com.vk18.splitwise.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByPhone(String phone);

    public User save(User user);
}
