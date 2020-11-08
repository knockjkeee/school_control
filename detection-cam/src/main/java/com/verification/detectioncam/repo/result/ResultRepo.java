package com.verification.detectioncam.repo.result;

import com.verification.detectioncam.domain.result.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {
    Result findFirstByOrderByIdDesc();
}
