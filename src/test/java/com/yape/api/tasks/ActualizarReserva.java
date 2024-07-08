package com.yape.api.tasks;


import com.yape.api.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ActualizarReserva implements Task {
    private final String token;
    private final int id;
    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final String checkin;
    private final String checkout;
    private final String additionalneeds;

    public ActualizarReserva(String token, int id, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        this.token = token;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
    }


    public static ActualizarReserva withDetails(String token, int id, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        return instrumented(ActualizarReserva.class,token, id, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(Endpoint.API_BOOKING.getEndpoint() + "/" + id)
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
                "{\"firstname\":\"%s\",\"lastname\":\"%s\",\"totalprice\":%d,\"depositpaid\":%b,\"bookingdates\":{\"checkin\":\"%s\",\"checkout\":\"%s\"},\"additionalneeds\":\"%s\"}",
                firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds
        );
    }
}
