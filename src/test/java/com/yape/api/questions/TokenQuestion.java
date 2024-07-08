package com.yape.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import io.restassured.response.Response;

public class TokenQuestion implements Question<String> {

    public static TokenQuestion value() {
        return new TokenQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            Response response = SerenityRest.lastResponse();
            String token = response.body().jsonPath().getString("token");
            return token != null ? token : "";
        } catch (Exception e) {
            return "";
        }
    }
}
