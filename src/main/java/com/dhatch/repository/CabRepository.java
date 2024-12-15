package com.dhatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhatch.entity.Cab;

@Repository
public interface CabRepository extends JpaRepository<Cab, String> {

}
