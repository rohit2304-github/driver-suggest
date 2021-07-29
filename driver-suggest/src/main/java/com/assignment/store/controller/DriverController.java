package com.assignment.store.controller;

import com.assignment.store.entity.DriversLocation;
import com.assignment.store.entity.StoreDrivers;
import com.assignment.store.repository.DriversLocationRepository;
import com.assignment.store.repository.StoreDriversRepository;
import com.assignment.store.repository.StoresLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriversLocationRepository driversLocationRepository;
    @Autowired
    StoreDriversRepository storeDriversRepository;
    @Autowired
    StoresLocationRepository storesLocationRepository;

    @PostMapping("/add")
    public DriversLocation addDriver(@RequestBody DriversLocation driversLocation) {
        return driversLocationRepository.save(driversLocation);
    }

    @PostMapping("/addMapping")
    public StoreDrivers addDriverForStore(@RequestBody StoreDrivers storeDrivers) {
        return storeDriversRepository.save(
                StoreDrivers.builder()
                        .storeID(storeDrivers.getStoreID())
                        .driverID(storeDrivers.getDriverID())
                        .build());
    }

    @GetMapping("/all")
    public List<DriversLocation> getAllDrivers() {
        return driversLocationRepository.findAll();
    }

    @GetMapping("/mapping")
    public List<StoreDrivers> getStoreAndDriversMapping() {

        return storeDriversRepository.findAll();
    }

    @GetMapping("/{driverId}/mapping")
    public List<StoreDrivers> getMappingForDriver(@PathVariable String driverId) {
        return storeDriversRepository.findStoreDriversByDriverID(driverId);
    }

}
