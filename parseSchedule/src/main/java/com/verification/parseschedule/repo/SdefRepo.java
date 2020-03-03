package com.verification.parseschedule.repo;

import com.verification.parseschedule.domain.Sdef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SdefRepo extends JpaRepository<Sdef, Long> {
}
