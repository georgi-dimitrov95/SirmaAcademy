package HotelReservationSystem;

import java.util.ArrayList;

public class User {
    String name;
    String password;
    ArrayList<String> bookingHistory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getBookingHistory() {
        return bookingHistory;
    }

    public void addToBookingHistory(String stay) {
        bookingHistory.add(stay);
    }

    public void removeFromBookingHistory(String stay) {
        bookingHistory.remove(stay);
    }
}
