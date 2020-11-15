package com.shoes.ecom.service;

import com.shoes.ecom.dto.BookingAlter;
import com.shoes.ecom.dto.BookingResponse;
import com.shoes.ecom.po.Booking;
import com.shoes.ecom.po.Schedule;
import com.shoes.ecom.po.User;
import com.shoes.ecom.repo.BookingRepo;
import com.shoes.ecom.repo.ScheduleRepo;
import com.shoes.ecom.repo.ServiceRepo;
import com.shoes.ecom.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    BookingRepo bookingRepo;

    
    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User createUser(User userToCreate) {
        userRepo.save(userToCreate);
        return userToCreate;
    }
  //LOGIN
    public Integer login(User userToCreate) {
        User user = userRepo.findByUserNameAndPassword(userToCreate.getUserName(),userToCreate.getPassword());
        if(user !=null){
            user.setLoggedIn('Y');
            userRepo.save(user);

            return user.getId();
        }
        return 0;
    }








    //SIGNUP


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





    //REQUESTS LIST IN SERVICE PROVIDER



    public List<BookingResponse> requests(Integer suid,Character status) {

        List<Booking> bookingList= bookingRepo.findBySuidAndStatus(suid,status);
        List<BookingResponse> bookingResponseList = new ArrayList<>();
        for(Booking booking : bookingList){
            BookingResponse bookingResponse = new BookingResponse();
            Optional<User> usr=userRepo.findById( booking.getUid());
            bookingResponse.setAddress(  usr.get().getAddress());
            bookingResponse.setPhNo(usr.get().getPhNo());
            bookingResponse.setName(usr.get().getName());
            bookingResponse.setHourl(booking.getHourl());
            bookingResponse.setId(booking.getId());
            bookingResponse.setHouru(booking.getHouru());
            bookingResponseList.add(bookingResponse);



        }
        return(bookingResponseList);
    }

    public Boolean acceptdecline(BookingAlter alters) {
        Optional<Booking> booking=bookingRepo.findById(alters.getId());

        booking.get().setStatus(alters.getStatus());
        bookingRepo.save(booking.get());

   return true;

    }
}
