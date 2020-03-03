package com.verification.parseschedule.repo;

import com.verification.parseschedule.domain.Daysdefs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysdefsRepo extends JpaRepository<Daysdefs, Long> {
}
