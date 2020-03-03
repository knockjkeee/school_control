package com.verification.wscontrolout.repo;

import com.verification.wscontrolout.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {

    Result findFirstByOrderById();

    Result findFirstByResultOrderByIdDesc(String result);

}
