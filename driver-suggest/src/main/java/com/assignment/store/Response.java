package com.assignment.store;

import lombok.*;

@Data
@Builder
@ToString
public class Response {

    private String driverId;
    private double distance;
}
