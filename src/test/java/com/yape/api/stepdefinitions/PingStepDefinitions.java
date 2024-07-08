package com.yape.api.stepdefinitions;

import com.yape.api.endpoint.BaseUrl;
import com.yape.api.questions.StatusQuestions;
import com.yape.api.questions.TokenQuestion;
import com.yape.api.tasks.Autenticar;
import com.yape.api.tasks.VerificarEstadoServidor;
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

public class PingStepDefinitions {

    @Steps
    BaseUrl base;
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que el servicio de (.*) se encuentra disponible$")
    public void elUsuarioTieneLasCredencialesCorrectas(String user) {
        theActorCalled(user);
        //Obtengo la base de la url
        theActorInTheSpotlight().whoCan(CallAnApi.at(base.getUrlBase()));

    }

    @When("^realiza una solicitud GET al endpoint de ping$")
    public void realizaUnaSolicitudGETAlEndpointDePing() {
        theActorInTheSpotlight().attemptsTo(VerificarEstadoServidor.enEndpointPing());
    }

    @Then("^debería recibir una respuesta indicando que el servidor está en funcionamiento$")
    public void deberíaRecibirUnaRespuestaIndicandoQueElServidorEstáEnFuncionamiento() {
        theActorInTheSpotlight().should(seeThat("Servidor en Funcionamiento", new StatusQuestions(), Matchers.equalTo(201)));

    }
}
