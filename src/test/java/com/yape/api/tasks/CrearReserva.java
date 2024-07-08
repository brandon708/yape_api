package com.yape.api.tasks;

import com.yape.api.endpoint.Endpoint;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CrearReserva implements Task {

    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final String checkin;
    private final String checkout;
    private final String additionalneeds;

    public CrearReserva(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
    }

    public static Performable withDetails(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        return instrumented(CrearReserva.class, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Endpoint.API_BOOKING.getEndpoint())
                        .with(requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .contentType("application/json")
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
