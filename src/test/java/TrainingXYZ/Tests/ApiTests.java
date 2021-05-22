package TrainingXYZ.Tests;

import TrainingXYZ.Api.AuthApi;
import io.restassured.http.ContentType;
import lombok.var;
import models.Auth;
import models.AuthResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Test
    public void getUsers(){
        String endpoint = "http://dummy.restapiexample.com/api/v1/employees/1";
        var response =
                given().
                when().
                      get(endpoint)
                .then();
        response.log().body();
    }


    @Test
    public void createBooking(){
        String endpoint = "https://restful-booker.herokuapp.com/booking";
        String body = "" +
                "{\n" +
                "    \"firstname\" : \"testime\",\n" +
                "    \"lastname\" : \"prezime\",\n" +
                "    \"totalprice\" : 500,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        var response =
                given().contentType(ContentType.JSON)
                .body(body)
                .when().post(endpoint)
                .then().statusCode(200)
                .body("booking.firstname", equalTo("testime"));

        response.log().body();

    }

    @Test
    public void getBookingByID(){
        String id="11";
        String endpoint = "https://restful-booker.herokuapp.com/booking/"+id;


        var response =
                        given().
                              //queryParam("id", 2)
                        when().
                               get(endpoint).
                        then()
                             .assertThat()
                             .statusCode(200)
                             .contentType("application/json")
                             .body("totalprice", equalTo(111))
                             .body("firstname", equalTo("Sally"))
                             .body("size()", greaterThanOrEqualTo(6));

        response.log().body();

    }


    @Test
    public void updateBooking(){

        String endpoint = "https://restful-booker.herokuapp.com/booking/19";
        String body = "{\n" +
                "    \"firstname\" : \"Updated\",\n" +
                "    \"lastname\" : \"Bezanoski\",\n" +
                "    \"totalprice\" : 1000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        Auth auth = new Auth();

        auth.setUsername("admin");
        auth.setPassword("password123");


        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);


        var response = given()
                                             .contentType(ContentType.JSON)
                                             .header("Cookie", "token=" + authResponse.getToken())
                                             .body(body)

                                      .when()
                                             .put(endpoint)
                                      .then()
                                             .statusCode(200)
                                             .contentType("application/json")
                                             .body("firstname", equalTo("Updated"));   ;
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint = "https://restful-booker.herokuapp.com/booking/";

       String idForDeleting="19";

        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");


        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);


        var response = given()
                                             .contentType(ContentType.JSON)
                                             .header("Cookie", "token=" + authResponse.getToken())
                                      .when()
                                             .delete(endpoint+idForDeleting)
                                      .then()
                                             .statusCode(201);
//           given()
//
//           .when()
//                .get(endpoint+idForDeleting)
//                .then()
//                .statusCode(404);

        response.log().body();
    }



}
