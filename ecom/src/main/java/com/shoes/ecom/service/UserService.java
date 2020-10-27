package com.shoes.ecom.service;

import com.shoes.ecom.po.User;
import com.shoes.ecom.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;
    
    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User createUser(User userToCreate) {
        userRepo.save(userToCreate);
        return userToCreate;
    }

    public Boolean login(User userToCreate) {
        User user = userRepo.findByUserNameAndPassword(userToCreate.getUserName(),userToCreate.getPassword());
        if(user !=null){
            user.setLoggedIn('Y');
            userRepo.save(user);

            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean signup(User userToCreate) {
        User user=userRepo.findByUserNameOrName(userToCreate.getUserName(),userToCreate.getName());

        if(user != null)
        {
            return(Boolean.FALSE);
        }
        else{
            userRepo.save(userToCreate);
            return Boolean.TRUE;
        }
    }

}
