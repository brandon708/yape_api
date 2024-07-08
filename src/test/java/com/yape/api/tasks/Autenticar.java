package com.yape.api.tasks;


import com.yape.api.endpoint.Endpoint;
import com.yape.api.questions.TokenQuestion;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Autenticar implements Task {
    private final String username;
    private final String password;

    public Autenticar(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Autenticar withCredentials(String username, String password) {
        return instrumented(Autenticar.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Endpoint.API_AUTH.getEndpoint())
                        .with(requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .contentType("application/json")
                                .body(requestBody())
                        )
        );

        Serenity.setSessionVariable("token").to(theActorInTheSpotlight().asksFor(TokenQuestion.value()));
    }
    private String requestBody() {
        return String.format(
                "{\"username\": \"%s\", \"password\": \"%s\"}",
                username, password
        );
    }


}
