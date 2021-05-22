package TrainingXYZ.Api;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;

import static io.restassured.RestAssured.given;

public class BookingApi { //Booking Rest Client


    protected static final String baseUrl = "https://restful-booker.herokuapp.com/";
    private static final String apiUrl = baseUrl + "booking/";

    public static Response getBookings() {
        return given().get(apiUrl);
    }

    public static Response getBooking(int id, String mediaType) {
        return given()
                .header("Accept", mediaType)
                .get(apiUrl + Integer.toString(id));
    }

    public static Response postBooking(Booking payload) {

        return given()
                     .contentType(ContentType.JSON)
                     .body(payload)
                .when()
                      .post(apiUrl);
    }

    public static Response deleteBooking(int id, String tokenValue) {
        return given()
                     .header("Cookie", "token=" + tokenValue)
               .when()
                      .delete(apiUrl + Integer.toString(id));
    }
}
