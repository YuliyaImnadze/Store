package com.example.store.repository;

import com.example.store.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface CommonRepository<E extends BaseEntity> extends JpaRepository<E, UUID> {
}