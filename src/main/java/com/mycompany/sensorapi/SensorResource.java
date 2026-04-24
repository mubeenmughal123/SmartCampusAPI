/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sensorapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {
        if (type == null || type.isEmpty()) {
            return SensorStore.sensors.values();
        }

        List<Sensor> filtered = new ArrayList<>();
        for (Sensor s : SensorStore.sensors.values()) {
            if (s.type != null && s.type.equalsIgnoreCase(type)) {
                filtered.add(s);
            }
        }
        return filtered;
    }

    @POST
    public Sensor createSensor(Sensor sensor) {

        if (sensor == null || sensor.roomId == null) {
            throw new BadRequestException("roomId is required");
        }

        Room room = RoomResource.rooms.get(sensor.roomId);
        if (room == null) {
            throw new BadRequestException("Room does not exist");
        }

        if (room.sensors == null) {
            room.sensors = new ArrayList<>();
        }

        String id = UUID.randomUUID().toString();
        sensor.id = id;

        if (sensor.readings == null) {
            sensor.readings = new ArrayList<>();
        }

        SensorStore.sensors.put(id, sensor);
        room.sensors.add(id);

        return sensor;
    }

    @GET
    @Path("/{id}")
    public Sensor getSensor(@PathParam("id") String id) {

        Sensor sensor = SensorStore.sensors.get(id);

        if (sensor == null) {
            throw new NotFoundException("Sensor not found");
        }

        return sensor;
    }

    @DELETE
    @Path("/{id}")
    public String deleteSensor(@PathParam("id") String id) {

        Sensor sensor = SensorStore.sensors.remove(id);

        if (sensor == null) {
            throw new NotFoundException("Sensor not found");
        }

        Room room = RoomResource.rooms.get(sensor.roomId);
        if (room != null && room.sensors != null) {
            room.sensors.remove(id);
        }

        return "Sensor deleted successfully";
    }

    @Path("/{id}/readings")
    public SensorReadingResource getReadingsResource(@PathParam("id") String id) {

        Sensor sensor = SensorStore.sensors.get(id);

        if (sensor == null) {
            throw new NotFoundException("Sensor not found");
        }

        return new SensorReadingResource(sensor);
    }
}
