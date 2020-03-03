package com.verification.controlout.repo.timerable;

import com.verification.controlout.domain.result.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepo extends JpaRepository<Timetable, Long> {

}
