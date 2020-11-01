package com.shoes.ecom.repo;

import com.shoes.ecom.po.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
}
