package com.verification.parseschedule.repo;

import com.verification.parseschedule.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepo extends JpaRepository<Timetable, Long> {

}
