
package com.yape.api.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

/**
 * This class is for configure the Cucumber runner.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        glue = {"com.yape.api.stepdefinitions"},
        features = "src/test/resources/features",
        tags = "@Ping")

public class CucumberTestSuite {
        @BeforeClass
        public static void beforeAll() {
            Logger.getLogger(CucumberTestSuite.class.getName()).info("Before all execution >>>");
        }
}