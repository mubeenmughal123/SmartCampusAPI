/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sensorapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private Sensor sensor;

    public SensorReadingResource(Sensor sensor) {
        this.sensor = sensor;
    }

    @GET
    public List<SensorReading> getReadings() {

        if (sensor.readings == null) {
            sensor.readings = new ArrayList<>();
        }

        return sensor.readings;
    }

    @POST
    public SensorReading addReading(SensorReading reading) {

        if (reading == null || reading.value == null) {
            throw new BadRequestException("Value is required");
        }

        if (sensor.readings == null) {
            sensor.readings = new ArrayList<>();
        }

        reading.id = UUID.randomUUID().toString();
        reading.timestamp = Instant.now().toString();

        sensor.readings.add(reading);

        sensor.value = reading.value;

        return reading;
    }

    @GET
    @Path("/latest")
    public SensorReading getLatest() {

        if (sensor.readings == null || sensor.readings.isEmpty()) {
            throw new NotFoundException("No readings found");
        }

        return sensor.readings.get(sensor.readings.size() - 1);
    }

    @GET
    @Path("/average")
    public double getAverage() {

        if (sensor.readings == null || sensor.readings.isEmpty()) {
            throw new NotFoundException("No readings");
        }

        double sum = 0;

        for (SensorReading r : sensor.readings) {
            sum += r.value;
        }

        return sum / sensor.readings.size();
    }
}