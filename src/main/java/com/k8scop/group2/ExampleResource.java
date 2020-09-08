package com.k8scop.group2;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/version")
public class ExampleResource {

    @ConfigProperty(name = "version", defaultValue = "2.0")
    private String version;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return version;
    }
}
