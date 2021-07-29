package com.assignment.store.repository;

import com.assignment.store.entity.DriversLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversLocationRepository extends JpaRepository<DriversLocation, String> {

}
