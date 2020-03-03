package com.verification.viewcontrolout.repo;

import com.verification.viewcontrolout.domain.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {

    Page<Result> findAll(Pageable pageable);
}
