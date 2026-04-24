/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sensorapi;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.WebApplicationException;
import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {

        if (ex instanceof WebApplicationException) {
            Response originalResponse = ((WebApplicationException) ex).getResponse();

            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());

            return Response.status(originalResponse.getStatus())
                    .entity(error)
                    .build();
        }

        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal server error");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}
