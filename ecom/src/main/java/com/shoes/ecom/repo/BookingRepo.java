package com.shoes.ecom.repo;

import com.shoes.ecom.po.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {


    List<Booking> findBySuidAndStatus(Integer suid, char status);

    List<Booking> findByUidAndStatus(Integer id, char a);


    Booking findBySuidAndHourl(Integer uid, Integer hourL);

    Booking findBySuidAndHourlAndUid(Integer uid, Integer hourL, Integer id);

    Booking findBySuidAndHourlAndUidAndStatus(Integer uid, Integer hourL, Integer pid, char r);
}
