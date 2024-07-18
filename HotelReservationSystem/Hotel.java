package HotelReservationSystem;

import java.util.ArrayList;

public class Hotel {
    double balance;
    String name;
    String address;
    String phoneNumber;
    String website;
    String utilities;
    int floors;
    double rating;
    ArrayList<ArrayList<Room>> rooms;

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<ArrayList<Room>> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<ArrayList<Room>> rooms) {
        this.rooms = rooms;
    }
}
