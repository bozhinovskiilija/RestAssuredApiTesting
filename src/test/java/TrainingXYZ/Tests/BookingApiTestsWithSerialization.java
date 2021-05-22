package TrainingXYZ.Tests;

import TrainingXYZ.Api.AuthApi;
import TrainingXYZ.Api.BookingApi;
import io.restassured.response.Response;
import models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class BookingApiTestsWithSerialization {

    @Test
    public void postBookingReturns200(){

        BookingDates dates = new BookingDates();

        dates.setCheckin(new Date());
        dates.setCheckout(new Date());


        Booking payload = new Booking();

        payload.setFirstname("Ilija");
        payload.setLastname("Bozinovski");
        payload.setTotalprice(200);
        payload.setDepositpaid(true);
        payload.setBookingdates(dates);
        payload.setAdditionalneeds("None");


        Response response = BookingApi.postBooking(payload);

        assertThat(response.getStatusCode()).isEqualTo(200);

    }

    @Test
    public void deleteBookingReturns201() {

        BookingDates dates = new BookingDates();
        dates.setCheckin(new Date());
        dates.setCheckout(new Date());


        Booking payload = new Booking();
        payload.setFirstname("Mary");
        payload.setLastname("White");
        payload.setTotalprice(200);
        payload.setDepositpaid(true);
        payload.setBookingdates(dates);
        payload.setAdditionalneeds("None");


        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");


        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);

        Response deleteResponse = BookingApi.deleteBooking(
                createdBookingResponse.getBookingid(),
                authResponse.getToken());

        assertThat(deleteResponse.getStatusCode()).isEqualTo(201);
    }
}
