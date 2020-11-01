package com.shoes.ecom.repo;

import com.shoes.ecom.po.Rating;
import com.shoes.ecom.po.Service;
import com.shoes.ecom.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer> {



}
