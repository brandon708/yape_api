package com.yape.api.tasks;

import com.yape.api.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class VerificarEstadoServidor implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Endpoint.API_PING.getEndpoint())
        );
    }

    public static VerificarEstadoServidor enEndpointPing() {
        return instrumented(VerificarEstadoServidor.class);
    }
}
