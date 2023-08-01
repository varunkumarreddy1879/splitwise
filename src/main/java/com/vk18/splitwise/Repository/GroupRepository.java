package com.vk18.splitwise.Repository;

import com.vk18.splitwise.Model.Group;
import com.vk18.splitwise.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    public Group save(Group group);

    List<Group> findAllByUsers(User user);
}
