package com.yape.api.tasks;

import com.yape.api.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EliminarReservaID implements Task {

    private final int id;
    private final String token;

    public EliminarReservaID(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public static EliminarReservaID withData(int id, String token) {
        return instrumented(EliminarReservaID.class, id, token);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(Endpoint.API_BOOKING.getEndpoint() + "/" + id)
                        .with(request -> request.header("Cookie", "token=" + token))
        );
    }
}
