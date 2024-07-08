package com.yape.api.stepdefinitions;

import com.yape.api.endpoint.BaseUrl;
import com.yape.api.questions.StatusQuestions;
import com.yape.api.questions.TokenQuestion;
import com.yape.api.tasks.Autenticar;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AuthStepDefinitions {

    @Steps
    BaseUrl base;
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^el (.*) tiene las credenciales correctas$")
    public void elUsuarioTieneLasCredencialesCorrectas(String user) {
        theActorCalled(user);
        //Obtengo la base de la url
        theActorInTheSpotlight().whoCan(CallAnApi.at(base.getUrlBase()));

    }

    @When("^realiza una solicitud POST al endpoint de autenticación$")
    public void realizaUnaSolicitudPOSTAlEndpointDeAutenticacion() {
        theActorInTheSpotlight().attemptsTo(
                Autenticar.withCredentials("admin", "password123")
        );
    }

    @Then("^debería recibir un token de autenticación válido$")
    public void deberiaRecibirUnTokenDeAutenticacionValido() {
        // Validar que el código de estado sea 200
        theActorInTheSpotlight().should(
                seeThat("El código de estado de la respuesta", new StatusQuestions(), Matchers.equalTo(200))
        );

        // Validar y obtener el token
        theActorInTheSpotlight().should(
                seeThat("Token", new TokenQuestion(), Matchers.notNullValue())
        );

        // Imprimir el token
        String token = theActorInTheSpotlight().asksFor(new TokenQuestion());
        System.out.println("El token de autenticación es: " + token);
    }

}
