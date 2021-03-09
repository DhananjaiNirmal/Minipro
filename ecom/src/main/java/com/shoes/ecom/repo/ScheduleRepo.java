package com.shoes.ecom.repo;
import com.shoes.ecom.po.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {


    List<Schedule> findByAvailableAndSid(int i, int i1);

    Schedule findByUid(Integer suid);



    Schedule findByUidAndHourLAndHourU(Integer suid, Integer hourl, Integer houru);
}

