package com.shoes.ecom.repo;

import com.shoes.ecom.po.Rating;
import com.shoes.ecom.po.Service;
import com.shoes.ecom.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer> {


    Rating findBySuidAndUid(Integer suid, Integer uid);

    List<Rating> findAllBySuid(Integer id);
}
