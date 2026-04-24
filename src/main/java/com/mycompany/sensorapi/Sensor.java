/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sensorapi;

import java.util.ArrayList;
import java.util.List;

public class Sensor {

    public String id;
    public String type;      
    public double value;
    public String roomId;   

    public String status = "ACTIVE";

    public List<SensorReading> readings = new ArrayList<>();

    public Sensor() {
    }
}