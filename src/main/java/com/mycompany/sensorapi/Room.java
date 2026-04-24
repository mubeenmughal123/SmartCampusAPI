/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sensorapi;

import java.util.ArrayList;
import java.util.List;

public class Room {

    public String id;

    public String name;

    public List<String> sensors = new ArrayList<>();

    public Room() {
    }

    public Room(String id, String name) {
        this.id = id;
        this.name = name;
    }
}