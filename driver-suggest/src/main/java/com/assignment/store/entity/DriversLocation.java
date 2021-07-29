package com.assignment.store.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="drivers_location")
public class DriversLocation {

    @Id
    @Column
    private String driverID;

    @Column
    private double longitude;

    @Column
    private double latitude;
}

