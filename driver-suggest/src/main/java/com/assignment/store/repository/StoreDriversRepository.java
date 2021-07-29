package com.assignment.store.repository;

import com.assignment.store.entity.StoreDrivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreDriversRepository extends JpaRepository<StoreDrivers, String> {

    @Query("select strDr from StoreDrivers strDr where strDr.storeID = :storeID")
    List<StoreDrivers> findByStoreId(@Param("storeID") String string);

    @Query("select strDr from StoreDrivers strDr where strDr.driverID = :driverID")
    List<StoreDrivers> findStoreDriversByDriverID(@Param("driverID") String driverId);

}
