package com.verification.viewverification.repo;

import com.verification.viewverification.domain.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {
    Result findFirstByOrderByIdDesc();

    Page<Result> findAll(Pageable pageable);

}
