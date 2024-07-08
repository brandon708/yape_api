package com.yape.api.endpoint;


import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * Clase que proporciona la URL base para las pruebas de automatización.
 */
public class BaseUrl {

    private static EnvironmentVariables environmentVariables;

    public String getUrlBase() {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("restapi.baseurl");
    }


}

