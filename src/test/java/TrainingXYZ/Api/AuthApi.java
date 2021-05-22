package TrainingXYZ.Api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Auth;

import static io.restassured.RestAssured.given;

public class AuthApi {

    private static final String apiUrl = "https://restful-booker.herokuapp.com/auth/";

    public static Response postAuth(Auth payload){
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(apiUrl);
    }
}

