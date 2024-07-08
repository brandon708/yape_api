package com.yape.api.tasks;

import com.yape.api.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ActualizarParcialReserva implements Task {
    private final String token;
    private final int id;
    private final String firstname;
    private final String lastname;

    public ActualizarParcialReserva(String token, int id, String firstname, String lastname) {
        this.token = token;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static ActualizarParcialReserva withDetails(String token, int id, String firstname, String lastname) {
        return instrumented(ActualizarParcialReserva.class, token, id, firstname, lastname);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Patch.to(Endpoint.API_BOOKING.getEndpoint() + "/" + id)
                        .with(requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .contentType("application/json")
                                .header("Cookie", "token=" + token)
                                .body(requestBody())
                        )
        );
    }

    private String requestBody() {
        return String.format(
                "{\"firstname\":\"%s\",\"lastname\":\"%s\"}",
                firstname, lastname
        );
    }
}
