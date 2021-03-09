package com.shoes.ecom.service;

import com.shoes.ecom.dto.BookingAlter;
import com.shoes.ecom.dto.BookingResponse;
import com.shoes.ecom.dto.Requestingdto;
import com.shoes.ecom.dto.ScheduleDto;
import com.shoes.ecom.po.Booking;
import com.shoes.ecom.po.Rating;
import com.shoes.ecom.po.Schedule;
import com.shoes.ecom.po.User;
import com.shoes.ecom.repo.*;
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
    @Autowired
    RatingRepo ratingingRepo;



    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User createUser(User userToCreate) {
        userRepo.save(userToCreate);
        return userToCreate;
    }
  // when LOGIN occurs if u click login
    public User login(User userToCreate) {
        User user = userRepo.findByUserNameAndPassword(userToCreate.getUserName(),userToCreate.getPassword());
        if(user !=null){
            user.setLoggedIn('Y');
            userRepo.save(user);

            return user;
        }
        return null;
    }








    //SIGNUP part when u click signup


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




    //PROVIDERS PART

    //it sends data based on what u have asked from  the front end A ,R or D



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

    //front end acceping page

    //ACCEPT  when we click on accept availabiliy cnahges in the schedule table and R changes to A

    public Boolean acceptdecline(BookingAlter alters) {
        Optional<Booking> booking=bookingRepo.findById(alters.getId());

        Schedule sch=scheduleRepo.findByUidAndHourLAndHourU(booking.get().getSuid(),booking.get().getHourl(),booking.get().getHouru());
        sch.setAvailable(0);
        scheduleRepo.save(sch);

        booking.get().setStatus(alters.getStatus());
        bookingRepo.save(booking.get());

   return true;

    }


    //from the frontend service user login

    // REQUESTING PART if we request using the request button in service users pages

    public Boolean requesting(Requestingdto Reqalters) {


//        Booking booking=new Booking();
//        Optional<Booking> b=bookingRepo.findById( Reqalters.getId());
//        booking=b.get();
//
//        booking.setStatus(Reqalters.getStatus());
//        bookingRepo.save(booking);
//        Schedule s=scheduleRepo.findByUidAndHourLAndHourU(booking.getSuid(),booking.getHourl(),booking.getHouru());
//        s(0);
//


        Optional<Schedule> s=scheduleRepo.findById(Reqalters.getId());
        Schedule schedule=s.get();


        Booking b=new Booking();

        b=bookingRepo.findBySuidAndHourlAndUidAndStatus(schedule.getUid(),schedule.getHourL(),Reqalters.getPid(),'R');
        if(b!=(null))
        {
            return false;
        }



        Booking booking=new Booking();
        booking.setStatus(Reqalters.getStatus());


        booking.setHourl(schedule.getHourL());
        booking.setHouru(schedule.getHourU());
        booking.setSid(schedule.getSid());
        booking.setSuid(schedule.getUid());
        booking.setUid(Reqalters.getPid());


        bookingRepo.save(booking);




        return true;
    }


    //SERVICE USER PART
    // sends data in the front to show the data in the frontend to show which slots and timings are available



    public List<ScheduleDto> getAvailableSlots(String providerType){
        List<Schedule> availableList= new ArrayList<>();
        List<ScheduleDto> availableDtoList=new ArrayList<>();


       if(providerType.equals("BS"))
       {
           availableList=scheduleRepo.findByAvailableAndSid(1,3);



       }
        else if(providerType.equals("DK"))
        {
            availableList=scheduleRepo.findByAvailableAndSid(1,2);
        }
       else {
           availableList=scheduleRepo.findByAvailableAndSid(1,4);
       }
        for(Schedule schedule:availableList)
        {
            ScheduleDto s=new ScheduleDto();
            User u=new User();
            Optional<User> usr=userRepo.findById( schedule.getUid());
            u=usr.get();
            s.setAddress(u.getAddress());
            s.setCost(u.getCost());
            s.setAvailable(schedule.getAvailable());
            s.setHourL(schedule.getHourL());
            s.setHourU(schedule.getHourU());
            s.setName(u.getName());
            s.setPhNo(u.getPhNo());
            s.setUid(u.getId());
            s.setId(schedule.getId());
            s.setSid(u.getServiceId());
            availableDtoList.add(s);


        }
       return availableDtoList;
    }

    // ACCEPTED ONES ARE DISPLAYED

    public List<ScheduleDto> getBookings(Integer id) {



        List<ScheduleDto> bookingDtoList=new ArrayList<>();
        List<Booking> bookingList=new ArrayList<>();
        bookingList=bookingRepo.findByUidAndStatus(id,'A');

        for(Booking booking:bookingList)
        {
            ScheduleDto s=new ScheduleDto();
            User u=new User();
            Optional<User> usr=userRepo.findById( booking.getSuid());
            u=usr.get();
            s.setName(u.getName());
            s.setPhNo(u.getPhNo());
            s.setId(booking.getSuid());
            s.setHourL(booking.getHourl());
            s.setHourU(booking.getHouru());
            s.setCost(u.getCost());
            s.setAddress(u.getAddress());
            bookingDtoList.add(s);


        }
        return bookingDtoList;
    }




      //LOGOUT

    public Boolean getLogs(User log) {
        Optional<User> u=userRepo.findById(log.getId());
        User user=u.get();
        user.setLoggedIn('N');
        userRepo.save(user);

        return true;
    }






    //RATING



    // in order to put ratings in the my rating  table
    public Boolean getRate(Rating rate) {

        Rating r=new Rating();
        r=ratingingRepo.findBySuidAndUid(rate.getSuid(),rate.getUid());
        if(r==null){
             r=new Rating();
            r.setSuid(rate.getSuid());
            r.setUid(rate.getUid());
            r.setStar(rate.getStar());


        }
        else{
            r.setStar(rate.getStar());
        }
        ratingingRepo.save(r);
        return true;

    }
    // in order to deispaly ratings in the my rating  table

    public List<ScheduleDto> getRating(Integer id) {

        List<Rating> ratingList=ratingingRepo.findAllBySuid(id);
        List<ScheduleDto> ratingDtoList=new ArrayList<>();
        for(Rating schedule:ratingList)
        {
            ScheduleDto s=new ScheduleDto();
            User u=new User();
            Optional<User> usr=userRepo.findById( schedule.getUid());
            u=usr.get();
            s.setAddress(u.getAddress());



            s.setName(u.getName());
            s.setPhNo(u.getPhNo());
            s.setUid(u.getId());
            s.setId(schedule.getId());
            s.setSid(u.getServiceId());
            s.setRating(schedule.getStar());
         ratingDtoList.add(s);


        }
        return ratingDtoList;

    }
}
