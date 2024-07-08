package com.yape.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import io.restassured.response.Response;

public class BookingIdsQuestions implements Question<Boolean> {

    public static BookingIdsQuestions isNotEmpty() {
        return new BookingIdsQuestions();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            Response response = SerenityRest.lastResponse();
            if (response.statusCode() != 200) {
                return false;
            }

            // Verificar que la lista de IDs de reservas no esté vacía
            var idsNotEmpty = !response.jsonPath().getList("bookingid").isEmpty();
            return idsNotEmpty;
        } catch (Exception e) {
            return false;
        }
    }
}
