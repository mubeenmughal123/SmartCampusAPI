/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sensorapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {


    public static Map<String, Room> rooms = new HashMap<>();

    @GET
    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    @POST
    public Room createRoom(Room room) {

        if (room == null || room.name == null || room.name.isEmpty()) {
            throw new BadRequestException("Room name is required");
        }

        String id = UUID.randomUUID().toString();
        room.id = id;

        rooms.put(id, room);

        return room;
    }

    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id) {

        Room room = rooms.get(id);

        if (room == null) {
            throw new NotFoundException("Room not found");
        }

        return room;
    }

    @DELETE
    @Path("/{id}")
    public String deleteRoom(@PathParam("id") String id) {

        Room room = rooms.get(id);

        if (room == null) {
            throw new NotFoundException("Room not found");
        }

        if (room.sensors != null && !room.sensors.isEmpty()) {
            throw new RoomNotEmptyException("Room has active sensors");
        }

        rooms.remove(id);

        return "Room deleted successfully";
    }
}