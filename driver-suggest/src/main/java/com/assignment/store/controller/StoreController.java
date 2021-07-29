package com.assignment.store.controller;

import com.assignment.store.Response;
import com.assignment.store.entity.DriversLocation;
import com.assignment.store.entity.StoreDrivers;
import com.assignment.store.entity.StoresLocation;
import com.assignment.store.repository.DriversLocationRepository;
import com.assignment.store.repository.StoreDriversRepository;
import com.assignment.store.repository.StoresLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoresLocationRepository storesLocationRepository;
    @Autowired
    StoreDriversRepository storeDriversRepository;
    @Autowired
    DriversLocationRepository driversLocationRepository;

    @PostMapping("/add")
    public StoresLocation addStore(@RequestBody StoresLocation storesLocation) {
        return storesLocationRepository.save(storesLocation);
    }

    @GetMapping("/all")
    public List<StoresLocation> findAll() {
        return storesLocationRepository.findAll();
    }

    @GetMapping("/{storeId}/mapping")
    public List<StoreDrivers> getMappingForDriver(@PathVariable String storeId) {
        return storeDriversRepository.findByStoreId(storeId);
    }

    @GetMapping("/{storeId}/drivers/{numberOfDrivers}")
    public List<Response> getDriversNearStore(@PathVariable String storeId, @PathVariable int numberOfDrivers) {

        Map<String, Double> driverDistance = new HashMap<>();
        StoresLocation storesLocation = storesLocationRepository.findById(storeId).get();
        List<StoreDrivers> storeDrivers = storeDriversRepository.findByStoreId(storeId);

        storeDrivers.forEach(a -> {
            DriversLocation driversLocation =  driversLocationRepository.findById(a.getDriverID()).get();
            Double y2 = Math.pow(storesLocation.getLongitude() - driversLocation.getLongitude(), 2);
            Double x2 = Math.pow(storesLocation.getLatitude() - driversLocation.getLatitude(), 2);
            Double distance = Math.sqrt(  x2 + y2 );
            driverDistance.put(a.getDriverID(), distance);
        });

        List<Response> result = driverDistance.entrySet().stream()
                .sorted((a, b) -> Double.compare(a.getValue(), b.getValue()))
                .limit(numberOfDrivers)
                .map(a -> Response.builder().distance(a.getValue()).driverId(a.getKey()).build())
                .collect(Collectors.toList());
        return result;
    }


}
