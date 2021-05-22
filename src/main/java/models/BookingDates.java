package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingDates {

    @JsonProperty
    private Date checkin;
    @JsonProperty
    private Date checkout;

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }


    public BookingDates() {

    }

    private BookingDates(Date checkin, Date checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }
}
