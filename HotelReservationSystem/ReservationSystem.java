package HotelReservationSystem;

import java.util.ArrayList;
import java.util.Objects;

public class ReservationSystem {
    ArrayList<Hotel> hotels;
    ArrayList<User> users;

    public ReservationSystem() {
        this.hotels = new ArrayList<>();
        this.users = new ArrayList<>();
    }

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

//    checks if a string matches a username from the Users list
    public boolean userNameExists(String userName) {
        for (User user : users) {
            if (Objects.equals(userName, user.getName())) {
                return true;
            }
        }
        return false;
    }
}
