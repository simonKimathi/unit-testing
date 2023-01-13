package classicmodels.it.rest;

import java.net.URL;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import io.jotech.classicmodels.entity.Office;
import classicmodels.util.ArquillianWarUtils;
import io.jotech.classicmodels.vm.FormResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;


@ExtendWith(ArquillianExtension.class)
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OfficesTest {
    @Deployment
    public static Archive<?> createDeployment() {

        return ArquillianWarUtils.getBasicWebArchive("OfficesTest.war")
                .addPackages(true, Filters.exclude(
                        OfficesTest.class
                ), "io.jotech");
    }

    @ArquillianResource
    private URL base;

    @Test
    @DisplayName("testAllOffices")
    @Order(1)
    void testAllOffices() {
        given()//prerequisites
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .log()
                .all()
                .when()//describes the action to take
                .get(base + "api/offices/")
                .then()//Describe the expected result
                .log().all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .header("Content-Type", "application/json")
                .body("rows.size()", greaterThan(0))//dataset has 4
                .body("rows.officeCode", everyItem(notNullValue()));

    }

    @Test
    @DisplayName("testGetOneOfficeByOfficeCode")
    @Order(2)
    void testGetOneOfficeByOfficeCode() {
        given()//prerequisites
                .log().all()

                .pathParam("officeCode", "1")
                .when()//describes the action to take
                .get(base + "api/offices/{officeCode}")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("data.officeCode", equalTo("1"))
                .body("data.city", startsWith("San"))
                .body("data.state", Matchers.endsWith("CA"));

    }

    @Test
    @DisplayName("testGetOneOfficeByOfficeCodeDeserialized")
    @Order(3)
    void testGetOneOfficeByOfficeCodeDeserialized() {
        FormResponse userFormData = given()//prerequisites
                .log().all()
                .pathParam("officeCode", "1")
                .when()//describes the action to take
                .get(base + "api/offices/{officeCode}")
                .as(FormResponse.class);
        Assertions.assertThat(userFormData.getData()).isNotNull();


    }

    @Test
    @DisplayName("testCreatingOffice")
    @Order(4)
    void testCreatingOffice() {
        var payload = Office.builder()
                .officeCode("100")
                .city("Nairobi")
                .phone("+1 650 219 4782")
                .addressLine1("Tom Mboya Street")
                .addressLine2("Mayfair Suite")
                .state("NAIROBI")
                .country("KENYA")
                .postalCode("10100")
                .territory("NA")
                .build();


        given()//prerequisites
                .body(payload)
                .header("Content-Type", "application/json")
                .accept("application/json")
                .log().all()
                .when()//describes the action to take
                .post(base + "api/offices/")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.CREATED.getStatusCode());

    }

    @Test
    @DisplayName("testUpdatingOffice")
    @Order(5)
    void testUpdatingOffice() {
        var payload = Office.builder()
                .officeCode("10")
                .city("Nairobi")
                .phone("+254700596314")
                .addressLine1("Tom Mboya Street")
                .addressLine2("Mayfair Suite")
                .state("KISII")
                .country("UGANDA")
                .postalCode("101001")
                .territory("NA")
                .build();



        given()//prerequisites
                .body(payload)
                .header("Content-Type", "application/json")
                .log().all()
                .when()//describes the action to take
                .put(base + "api/offices/")
                .then()
                .log()
                .all()
                .assertThat()
                .header("Content-Type", "application/json")
                .statusCode(Response.Status.OK.getStatusCode())
        ;

    }

    @Test
    @DisplayName("deleteOffice")
    @Order(6)
    void deleteOffice() {
        given()//prerequisites
                .log().all()

                .pathParam("officeCode", "10")
                .when()//describes the action to take
                .delete(base + "api/offices/{officeCode}")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
        ;
    }


}
