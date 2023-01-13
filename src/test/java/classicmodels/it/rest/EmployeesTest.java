package classicmodels.it.rest;

import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import classicmodels.util.ArquillianWarUtils;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(ArquillianExtension.class)
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeesTest {

    @Deployment
    public static Archive<?> createDeployment() {

        WebArchive webArchive = ArquillianWarUtils
                .getBasicWebArchive("OfficesTest.war")
                .addPackages(
                        true,
                        Filters.exclude(
                                OfficesTest.class,
                                EmployeesTest.class
                        ),
                        "io.jotech"
                );
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

    @ArquillianResource
    private URL base;

    @Test
    @DisplayName("testAllEmployees")
    @Order(1)
    void testAllEmployees() {
        given()//prerequisites
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .log()
                .all()
                .when()//describes the action to take
                .get(base + "api/employees/")
                .then()//Describe the expected result
                .log().all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .header("Content-Type", "application/json")
                .body("rows.size()", greaterThan(0))

        ;

    }

}