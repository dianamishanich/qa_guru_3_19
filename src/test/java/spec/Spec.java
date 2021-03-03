package spec;

import io.restassured.specification.RequestSpecification;

import static template.ReportTemplate.filters;

import static io.restassured.RestAssured.given;

public class Spec {

    public static RequestSpecification request() {
        return given()
                .baseUri("https://reqres.in/")
                .basePath("/api")
                .filter(filters().customTemplates())
                .log().uri();
    }

}
