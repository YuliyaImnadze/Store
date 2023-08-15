package com.example.store.repository;

import com.example.store.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewsRepository extends CommonRepository<Reviews> {
}
