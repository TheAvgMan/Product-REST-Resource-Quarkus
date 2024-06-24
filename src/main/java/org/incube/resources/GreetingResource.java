package org.incube.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {

        return Response
                .status(Response.Status.ACCEPTED)
                .entity("Hello from Quarkus REST it's me hazemmmmm")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
