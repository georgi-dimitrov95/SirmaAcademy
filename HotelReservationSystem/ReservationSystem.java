package HotelReservationSystem;

import java.util.ArrayList;

public class ReservationSystem {
    ArrayList<Hotel> hotels;
    ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void deleteHotel(Hotel hotel) {
        hotels.remove(hotel);
    }
}
