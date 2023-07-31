package com.vk18.splitwise.Repository;

import com.vk18.splitwise.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    public Group save(Group group);
}
