package com.yape.api.stepdefinitions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yape.api.endpoint.BaseUrl;
import com.yape.api.questions.BookingIdsQuestions;
import com.yape.api.questions.StatusQuestions;
import com.yape.api.tasks.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class BookingStepDefinitions {

    @Steps
    BaseUrl base;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }


    @Given("^que el servicio de (.*) está disponible$")
    public void servicioEstaDisponible(String user) {
        theActorCalled(user);
        //Obtengo la base de la url
        theActorInTheSpotlight().whoCan(CallAnApi.at(base.getUrlBase()));
    }

    @When("^realiza una solicitud GET al endpoint de reservas$")
    public void realizaUnaSolicitudGETAlEndpointDeReservas() {
        theActorInTheSpotlight().attemptsTo(new ObtenerReserva());
    }

    @Then("^debería recibir una lista de IDs de reservas$")
    public void deberiaRecibirUnaListaDeIDsDeReservas() {
        theActorInTheSpotlight().should(seeThat("La lista de IDs de reservas no está vacía", BookingIdsQuestions.isNotEmpty()));

        // Imprimir el response de la reserva en un formato más legible
        String response = SerenityRest.lastResponse().asString();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(gson.fromJson(response, Object.class));
        System.out.println("El response de la reserva es: \n" + prettyJson);

    }

    @When("^realiza una solicitud POST al endpoint de reservas con los siguientes detalles:$")
    public void realizaUnaSolicitudPOSTAlEndpointDeReservasConLosSiguientesDetalles(DataTable reservasData) {


        List<Map<String, String>> data = reservasData.asMaps(String.class, String.class);
        Map<String, String> reservaDetalle = data.get(0);
        String firstname = reservaDetalle.get("firstname");
        String lastname = reservaDetalle.get("lastname");
        int totalprice = Integer.parseInt(reservaDetalle.get("totalprice"));
        boolean depositpaid = Boolean.parseBoolean(reservaDetalle.get("depositpaid"));
        String checkin = reservaDetalle.get("checkin");
        String checkout = reservaDetalle.get("checkout");
        String additionalneeds = reservaDetalle.get("additionalneeds");

        theActorInTheSpotlight().attemptsTo(CrearReserva.withDetails(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds));
    }

    @Then("^se debe crear un nuevo registro de la reserva$")
    public void CrearNuevoRegistroReserva() {
        theActorInTheSpotlight().should(seeThat("Verificar que la reserva fue creada", new StatusQuestions(), Matchers.equalTo(200)));


        // Imprimir el response de la reserva
        String response = SerenityRest.lastResponse().asString();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(gson.fromJson(response, Object.class));
        System.out.println("El response de la reserva es: \n" + prettyJson);
    }

    @And("^existe una reserva con ID (.*)$")
    public void existeUnaReservaConID(int id) {
        theActorInTheSpotlight().attemptsTo(ObtenerReservaID.withData(id));
        theActorInTheSpotlight().should(seeThat("Estado OK", new StatusQuestions(), Matchers.equalTo(200)));

    }

    @When("^realiza una solicitud PUT al endpoint de reservas con ID (.*) con los siguientes detalles:$")
    public void realizaUnaSolicitudPUTAlEndpointDeReservasConID(int id, DataTable reservasData) {

        theActorInTheSpotlight().attemptsTo(
                Autenticar.withCredentials("admin", "password123")
        );

        String token = Serenity.sessionVariableCalled("token");

        List<Map<String, String>> data = reservasData.asMaps(String.class, String.class);
        Map<String, String> reservaDetalle = data.get(0);
        String firstname = reservaDetalle.get("firstname");
        String lastname = reservaDetalle.get("lastname");
        int totalprice = Integer.parseInt(reservaDetalle.get("totalprice"));
        boolean depositpaid = Boolean.parseBoolean(reservaDetalle.get("depositpaid"));
        String checkin = reservaDetalle.get("checkin");
        String checkout = reservaDetalle.get("checkout");
        String additionalneeds = reservaDetalle.get("additionalneeds");

        theActorInTheSpotlight().attemptsTo(ActualizarReserva.withDetails(token,id,firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds));
    }

    @Then("^debería recibir una respuesta con los datos actualizados de la reserva$")
    public void deberiaRecibirUnaRespuestaConLosDatosActualizadosDeLaReserva() {
        theActorInTheSpotlight().should(seeThat("Estado Actulizado", new StatusQuestions(), Matchers.equalTo(200)));

        // Imprimir el response de la reserva actualizada
        String response = SerenityRest.lastResponse().asString();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(gson.fromJson(response, Object.class));
        System.out.println("El response de la reserva actualizada es: \n" + prettyJson);
    }

    @When("^realiza una solicitud DELETE al endpoint de reservas con ID (.*)$")
    public void realizaUnaSolicitudDELETEAlEndpointDeReservasConID(int id) {

        theActorInTheSpotlight().attemptsTo(
                Autenticar.withCredentials("admin", "password123")
        );

        String token = Serenity.sessionVariableCalled("token");

        theActorInTheSpotlight().attemptsTo(EliminarReservaID.withData(id,token));
    }

    @Then("^la reserva con ID (.*) debería ser eliminada$")
    public void laReservaConID1DeberiaSerEliminada(int id) {
        theActorInTheSpotlight().attemptsTo(ObtenerReservaID.withData(id));

        theActorInTheSpotlight().should(seeThat("Estado no encontrado", new StatusQuestions(), Matchers.equalTo(404)));
    }


    @When("^realiza una solicitud PATCH al endpoint de reservas con ID (.*) con los siguientes detalles:$")
    public void realizaUnaSolicitudPATCHAlEndpointDeReservasConIDIdConLosSiguientesDetalles(int id, DataTable reservasData) {
        theActorInTheSpotlight().attemptsTo(
                Autenticar.withCredentials("admin", "password123")
        );

        String token = Serenity.sessionVariableCalled("token");

        List<Map<String, String>> data = reservasData.asMaps(String.class, String.class);
        Map<String, String> reservaDetalle = data.get(0);
        String firstname = reservaDetalle.get("firstname");
        String lastname = reservaDetalle.get("lastname");

        theActorInTheSpotlight().attemptsTo(ActualizarParcialReserva.withDetails(token,id,firstname, lastname));

    }
}
