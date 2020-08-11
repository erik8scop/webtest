/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2020
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.k8scop.group2.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;

/**
 * @author windhong
 */
@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

    private long created = new Date().getTime();

    @ConfigProperty(name = "liveness.wait", defaultValue = "0")
    long wait;
    @ConfigProperty(name = "liveness.check", defaultValue = "true")
    boolean check;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("liveness check");
        if (check) {
            if ((new Date().getTime() - created) > wait) {
                responseBuilder.up();
            } else {
                responseBuilder.down();
            }
        } else {
            responseBuilder.down();
        }
        return responseBuilder.build();
    }
}
