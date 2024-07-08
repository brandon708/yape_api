package com.yape.api.tasks;


import com.yape.api.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerReservaID implements Task {

private final int id;

    public ObtenerReservaID(int id) {
        this.id = id;
    }

    public static Performable withData(int id) {
        return instrumented(ObtenerReservaID.class, id);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        // Realizamos la solicitud POST
        actor.attemptsTo(Get.resource(Endpoint.API_BOOKING.getEndpoint() + "/" + id));

    }


}
