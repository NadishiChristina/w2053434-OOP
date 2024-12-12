package org.example;
import java.math.BigDecimal;

//ticket information
public class Ticket {

    //Object of this class will represent a ticket
    private int eventId;
    private String eventName;
    private BigDecimal ticketPrice;


    public Ticket(int eventId, String eventName, BigDecimal ticketPrice){
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketId(){
        return eventId;
    }

    public void setTicketId(int ticketId) {
        this.eventId = ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket Info {" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
