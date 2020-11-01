package com.shoes.ecom.service;

import com.shoes.ecom.po.Schedule;
import com.shoes.ecom.po.User;
import com.shoes.ecom.repo.ScheduleRepo;
import com.shoes.ecom.repo.ServiceRepo;
import com.shoes.ecom.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;
    @Autowired
    ServiceRepo serviceRepo;
    @Autowired
    ScheduleRepo scheduleRepo;

    
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
            Integer k=userToCreate.getServiceId();

            Optional<com.shoes.ecom.po.Service> serviceOptional = serviceRepo.findById(k);
            if(serviceOptional != null){
                com.shoes.ecom.po.Service serviceObj = serviceOptional.get();
                for(int i=0;i<serviceObj.getHours();i++)
                {
                    Schedule schedule=new Schedule();
                    schedule.setAvailable(1);
                    schedule.setSid(serviceObj.getId());
                    schedule.setUid(userToCreate.getId());
                    schedule.setHourL(i+serviceObj.getTimeL());
                    schedule.setHourU(i+serviceObj.getTimeL()+1);
                    scheduleRepo.save(schedule);

                }

            }


            return Boolean.TRUE;
        }
    }

}
