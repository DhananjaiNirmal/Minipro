package com.shoes.ecom.repo;


import com.shoes.ecom.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserNameAndPassword(String userName, String password);

    User findByUserNameAndName(String userName, String name);

    User findByUserNameOrName(String userName, String name);
}
